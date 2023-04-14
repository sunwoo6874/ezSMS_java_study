package com.llsollu.ezsms.data.dao.impl;

import org.springframework.stereotype.Service;

import com.llsollu.ezsms.data.dao.SttDao;
import com.llsollu.ezsms.data.entity.Stt;
import com.llsollu.ezsms.data.repository.SttRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SttDaoImpl implements SttDao {

    private final SttRepository sttRepository;

    public SttDaoImpl(SttRepository sttRepository) {
        this.sttRepository = sttRepository;
    }

    public Stt saveSTT(Stt stt) {
        Stt stt1 = sttRepository.save(stt);
        return stt1;
    }

}
