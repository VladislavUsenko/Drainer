package com.vladislavuss.nginxadmin.service.upstream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladislavuss.nginxadmin.dto.Upstream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UpstreamServiceImpl implements UpstreamService {

    @Value("${app.servers}")
    List<String> serverNames;

    Logger logger = LoggerFactory.getLogger(UpstreamServiceImpl.class);

    @Override
    public Upstream getUpstream(String name) {

        Upstream result = null;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Upstream> response = restTemplate.getForEntity(getUrl() + name, Upstream.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            logger.info(
                    Objects.requireNonNull(response.getBody()).toString()
            );
            result = response.getBody();
        }
        return result;
    }

    @Override
    public List<Upstream> getAllUpstreams() {

        List<Upstream> result = null;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(getUrl(), String.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            String body = response.getBody();
            logger.info(body);

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Upstream> upstreams = null;
            try {
                upstreams = mapper.readValue(body, new TypeReference<>() {
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            result = upstreams.entrySet()
                    .stream()
                    .map(entry -> new Upstream(entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());
        }
        return result;
    }

    private String getUrl() {
        return "https://" + serverNames.getFirst() + "/api/9/http/upstreams/";
    }
}
