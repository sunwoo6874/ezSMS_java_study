package com.llsollu.ezsms.service.impl;

import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.llsollu.ezsms.data.dao.SttDao;
import com.llsollu.ezsms.data.dto.SttDto;
import com.llsollu.ezsms.data.entity.Stt;
import com.llsollu.ezsms.service.SttService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    @Override
    public String requestSTT(Long id, String ip, String port, String filePath, String pCode, String transID,
            String language) {
        // ASR Request 로직 구현.
        StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append(ip);
        sb.append(":");
        sb.append(port);
        sb.append("/filemode/?");
        sb.append("productcode=");
        sb.append(pCode);
        sb.append("&transactionid=");
        sb.append(transID);
        sb.append("&language=");
        sb.append(language);
        String uri = sb.toString();

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("audio/wav", new FileSystemResource(new File(filePath)));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, requestEntity,
                String.class);

        return responseEntity.getBody();
    }

    private static ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 5000;
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        clientHttpRequestFactory.setReadTimeout(timeout);
        return clientHttpRequestFactory;
    }
}

// Diff btw requestParam and PathParam ? ?