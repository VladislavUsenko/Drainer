package com.vladislavuss.nginxadmin.service.upstream;

import com.vladislavuss.nginxadmin.dto.Upstream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

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

    private String getUrl() {
        return "http://" + serverNames.getFirst() + "/api/6/http/upstreams/";
    }
}
