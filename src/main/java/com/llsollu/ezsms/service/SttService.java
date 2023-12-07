package com.llsollu.ezsms.service;

import org.springframework.stereotype.Service;

import com.llsollu.ezsms.data.dto.SttDto;

@Service
public interface SttService {

  SttDto saveSTT(Long id, String fileName, String etime, String sttResult, String wavURL);

  String requestBatch(Long id, String ip, String port, String filePath, String pCode, String transID, String language,
      String spkd, String align, boolean enablePostProc);
}
