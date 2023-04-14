package com.llsollu.ezsms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.llsollu.ezsms.data.dto.SttDto;
import com.llsollu.ezsms.service.SttService;

@RequestMapping(value = "/ezsms/stt")
@RestController
public class SttController {

    private final SttService sttService;

    public SttController(SttService sttService) {
        this.sttService = sttService;
    }

    @RequestMapping(value = "/batch", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<SttDto> insertBatch() {
        SttDto responseDTO = sttService.saveSTT(10L, "recFileName", "2023-04-04 16:37:00", "batch",
                "localhost");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @RequestMapping(value = "/real", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<SttDto> insertRealtime() {
        SttDto responseDTO = sttService.saveSTT(632L, "recFileName", "2023-04-04 16:37:00", "realtime",
                "localhost");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
