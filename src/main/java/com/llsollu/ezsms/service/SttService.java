package com.llsollu.ezsms.service;

import org.springframework.stereotype.Service;

import com.llsollu.ezsms.data.dto.SttDto;
import com.llsollu.ezsms.data.entity.Stt;

@Service
public interface SttService {

  SttDto saveSTT(Long id, String fileName, String etime, String sttResult, String wavURL);

  // SttDto requestSTT(Long id, String fileName, String filePath);

}
