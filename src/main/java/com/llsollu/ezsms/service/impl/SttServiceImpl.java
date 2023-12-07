package com.llsollu.ezsms.service.impl;

import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.llsollu.eznlp.postproc.PostProcessor2;
import com.llsollu.ezsms.common.utils.TextUtil;
import com.llsollu.ezsms.data.dao.SttDao;
import com.llsollu.ezsms.data.dto.SttDto;
import com.llsollu.ezsms.data.entity.Stt;
import com.llsollu.ezsms.service.SttService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SttServiceImpl implements SttService {

    private final SttDao sttDao;
    private PostProcessor2 mPostProc;

    public SttServiceImpl(SttDao sttDao) {
        this.sttDao = sttDao;
        String rulesPath = "/Users/sunwoobaek/work/self_learning/spring_boot/ezSMS/rules";
        mPostProc = new PostProcessor2(rulesPath);
        mPostProc.command_mode.setCommandMode("MODE_DENORM");
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
    public String requestBatch(Long id, String ip, String port, String filePath, String pCode, String transID,
            String language, String spkd, String align) {
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
        sb.append("&spkd=");
        sb.append(spkd);
        sb.append("&align=");
        sb.append(align);
        String uri = sb.toString();

        CompletableFuture<String> cFuture = CompletableFuture.supplyAsync(() -> {
            try {
                MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
                body.add("wav", new FileSystemResource(Paths.get(filePath)));
                log.debug("####################### " + filePath);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.MULTIPART_FORM_DATA);

                HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

                RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());

                ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, requestEntity,
                        String.class);
                return responseEntity.getBody();

            } catch (Exception e) {
                log.error("Error occurred during HTTP request: {}", e.getMessage());
                return ""; // Return a default value or handle the error accordingly
            }
        });

        cFuture.thenAcceptAsync(result -> {
            if (TextUtil.isNullOrEmpty(result)) {
                // if STT is empty, proceed another action
            } else {
                // if STT is not empty, post process STT.
                // log.info("#################################### " + mPostProc.apply(result));
            }
        });

        try {
            // Wait for the CompletableFuture result without blocking the thread
            return mPostProc.apply(cFuture.get(20, TimeUnit.SECONDS));
        } catch (Exception e) {
            log.error("Error occurred while waiting for CompletableFuture: {}", e.getMessage());
            return ""; // Return a default value or handle the error accordingly
        }
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(20);

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();

        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                httpClient);

        int timeout = 20000;
        clientHttpRequestFactory.setConnectTimeout(timeout);
        clientHttpRequestFactory.setReadTimeout(timeout);
        return clientHttpRequestFactory;
    }
}
