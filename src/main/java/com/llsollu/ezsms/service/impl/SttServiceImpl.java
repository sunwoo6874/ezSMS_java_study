package com.llsollu.ezsms.service.impl;

import org.springframework.stereotype.Service;

import com.llsollu.ezsms.data.dao.SttDao;
import com.llsollu.ezsms.data.dto.SttDto;
import com.llsollu.ezsms.data.entity.Stt;
import com.llsollu.ezsms.service.SttService;

@Service
public class SttServiceImpl implements SttService {

    private final SttDao sttDao;

    public SttServiceImpl(SttDao sttDao) {
        this.sttDao = sttDao;
    }

    @Override
    public SttDto saveSTT(Long id, String fileName, String etime, String sttResult, String wavURL) {

        Stt stt = new Stt(id, fileName, etime, sttResult, wavURL);
        sttDao.saveSTT(stt);

        SttDto sttDto = new SttDto(stt.getId(),
                stt.getName(), stt.getETime(), stt.getSttResult(), stt.getWavURL());

        return sttDto;
    }

}
