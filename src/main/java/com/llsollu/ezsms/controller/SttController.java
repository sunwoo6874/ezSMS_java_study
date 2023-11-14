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
        String filePath = request.getParameter("filepath");
        String ipAddr = request.getParameter("ip");
        String port = request.getParameter("port");
        String productCode = request.getParameter("productcode");
        String transactionID = request.getParameter("transactionid");
        String language = request.getParameter("language");
        Long dbID = System.currentTimeMillis();

        String resBody = sttService.requestSTT(dbID, ipAddr, port, filePath, productCode, transactionID, language);
        log.info("Received => " + resBody);
        return ResponseEntity.status(HttpStatus.OK).body(resBody);
    }

    private String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return formattedDate;
    }
}
