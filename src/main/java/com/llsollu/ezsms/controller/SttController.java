package com.llsollu.ezsms.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.llsollu.ezsms.data.dto.SttDto;
import com.llsollu.ezsms.service.SttService;
import com.llsollu.ezsms.common.utils.TextUtil;
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

        @GetMapping("/requestBatch")
        public ResponseEntity<String> requestASR(HttpServletRequest request) {
                String filePath = (!TextUtil.isNullOrEmpty(request.getParameter("filepath")))
                                ? request.getParameter("filepath")
                                : "";
                if (TextUtil.isNullOrEmpty(filePath)) { // 녹취 파일 경로 없으면 말짱꽝
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wav file path required");
                }

                String ipAddr = (!TextUtil.isNullOrEmpty(request.getParameter("ip")))
                                ? request.getParameter("ip")
                                : "192.168.10.30";
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
                Long dbID = System.currentTimeMillis();

                String resBody = sttService.requestBatch(dbID, ipAddr, port, filePath, productCode, transactionID,
                                language, spkd, align);
                log.info("Received => " + resBody);
                return ResponseEntity.status(HttpStatus.OK).body(resBody);
        }

        private String getCurrentDateTime() {
                LocalDateTime now = LocalDateTime.now();
                String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                return formattedDate;
        }
}
