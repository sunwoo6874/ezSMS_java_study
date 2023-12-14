package com.llsollu.ezsms.controller;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.llsollu.ezsms.common.utils.TextUtil;
import com.llsollu.ezsms.data.dto.SttDto;
import com.llsollu.ezsms.service.SttService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/ezsms")
@RestController
public class SttController {

        private final SttService sttService;

        public SttController(SttService sttService) {
                this.sttService = sttService;
        }

        @RequestMapping(value = "/batch", method = { RequestMethod.GET, RequestMethod.POST })
        public ResponseEntity<SttDto> insertBatch() {
                SttDto responseDTO = sttService.saveSTT(10L, "recFileName", getCurrentDateTime(), "batch",
                                "localhost");
                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        @RequestMapping(value = "/real", method = { RequestMethod.GET, RequestMethod.POST })
        public ResponseEntity<SttDto> insertRealtime() {
                SttDto responseDTO = sttService.saveSTT(632L, "recFileName", getCurrentDateTime(), "realtime",
                                "localhost");
                return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }

        @RequestMapping("/requestBatch")
        public ResponseEntity<String> requestASR(HttpServletRequest request) {
                log.info("[Received] Content Length:" + request.getContentLength());

                String filePath = "";
                File tempUploadedFile = null;
                try {
                        Part filePart = request.getPart("audio");
                        InputStream fileContent = filePart.getInputStream();

                        String rootDir = "/Users/sunwoobaek/work/self_learning/spring_boot/ezSMS/ezsms/tempUploader/";
                        File tempDir = new File(rootDir);

                        if (!tempDir.exists() || !tempDir.isDirectory()) {
                                log.warn(tempDir + " does not exist, created just now and will be automatically deleted.");
                                if (tempDir.exists() && !tempDir.isDirectory()) {
                                        tempDir.delete();
                                }
                                tempDir.mkdir();
                        }

                        StringBuilder tempFileSB = new StringBuilder();
                        tempFileSB.append(rootDir);
                        tempFileSB.append(Long.toString(System.currentTimeMillis()));
                        tempFileSB.append(".wav");

                        Path destination = Paths.get(tempFileSB.toString());
                        Files.copy(fileContent, destination);

                        tempUploadedFile = destination.toFile();
                        filePath = tempUploadedFile.getAbsolutePath();

                } catch (Exception e) {
                        log.error("[Exception] Reason:" + e);
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
                }

                if (TextUtil.isNullOrEmpty(filePath)) { // 녹취 파일 경로 없으면 말짱꽝
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wav file path required");
                }

                String ipAddr = (!TextUtil.isNullOrEmpty(request.getParameter("ip")))
                                ? request.getParameter("ip")
                                : "211.236.232.199";
                String port = (!TextUtil.isNullOrEmpty(request.getParameter("port")))
                                ? request.getParameter("port")
                                : "7777";
                String productCode = (!TextUtil.isNullOrEmpty(request.getParameter("productcode")))
                                ? request.getParameter("productcode")
                                : "PRODUCT_CODE";
                String transactionID = (!TextUtil.isNullOrEmpty(request.getParameter("transactionid")))
                                ? request.getParameter("transactionid")
                                : "0";
                String language = (!TextUtil.isNullOrEmpty(request.getParameter("language")))
                                ? request.getParameter("language")
                                : "kor";
                String spkd = (!TextUtil.isNullOrEmpty(request.getParameter("spkd")))
                                ? request.getParameter("spkd")
                                : "no";
                String align = (!TextUtil.isNullOrEmpty(request.getParameter("align")))
                                ? request.getParameter("align")
                                : "no";
                boolean enablePP = (!TextUtil.isNullOrEmpty(request.getHeader("Enable-Post-Process")))
                                ? Boolean.parseBoolean(request.getHeader("Enable-Post-Process"))
                                : false;

                Long dbID = System.currentTimeMillis();

                String resBody = sttService.requestBatch(dbID, ipAddr, port, filePath, productCode,
                                transactionID, language, spkd, align, enablePP);
                log.info("[RECEIVED] STT: " + resBody);

                if (tempUploadedFile.exists()) {
                        tempUploadedFile.delete();
                }
                return ResponseEntity.status(HttpStatus.OK).body(resBody);
        }

        private String getCurrentDateTime() {
                LocalDateTime now = LocalDateTime.now();
                String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                return formattedDate;
        }
}