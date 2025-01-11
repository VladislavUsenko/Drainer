package com.vladislavuss.nginxadmin.service;

import com.vladislavuss.nginxadmin.dto.Peer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PeerServiceImpl implements PeerService {


    @Value("${app.servers}")
    List<String> serverNames;

    Logger logger = LoggerFactory.getLogger(PeerServiceImpl.class);


    @Override
    public void downPeer(Peer peer, String upstreamName, boolean down) {

    }

    @Override
    public void drainPeer(Peer peer, String upstreamName) {

        logger.info("Call drain Peer");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> map = new HashMap<>();
        map.put("drain", true);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        for (String url : getUrls()) {
            String endPoint = url + upstreamName + "/servers/" + peer.getId();
            ResponseEntity<String> response = restTemplate.exchange(
                    endPoint, HttpMethod.PATCH, entity, String.class
            );

            logger.info(response.getBody());
        }

    }

    @Override
    public void upPeer(Peer peer, String upstreamName) {

    }

    private List<String> getUrls() {
        List<String> urls = new ArrayList<>();
        for (String serverName : serverNames) {
            urls.add("http://" + serverName + "/api/6/http/upstreams/");
        }
        return urls;
    }
}
