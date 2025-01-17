package com.vladislavuss.nginxadmin.service.peer;

import com.vladislavuss.nginxadmin.dto.Peer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PeerServiceImpl implements PeerService {


    @Value("${app.servers}")
    List<String> serverNames;

    Logger logger = LoggerFactory.getLogger(PeerServiceImpl.class);


    @Override
    public void downPeer(Peer peer, String upstreamName) {

        logger.info(
                "Call DOWN Peer[id:{}, name:{}], upstreamName:{}",
                peer.getId(), peer.getName(), upstreamName
        );

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("down", true);

        requestEndpoints(parameters, upstreamName, peer.getId());
    }

    @Override
    public void drainPeer(Peer peer, String upstreamName) {

        logger.info(
                "Call DRAIN Peer[id:{}, name:{}], upstreamName:{}",
                peer.getId(), peer.getName(), upstreamName
        );

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("drain", true);

        requestEndpoints(parameters, upstreamName, peer.getId());

    }

    @Override
    public void upPeer(Peer peer, String upstreamName) {

        logger.info(
                "Call UP Peer[id:{}, name:{}], upstreamName:{}",
                peer.getId(), peer.getName(), upstreamName
        );

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("down", false);

        requestEndpoints(parameters, upstreamName, peer.getId());
    }

    private List<String> getUrls() {
        List<String> urls = new ArrayList<>();
        for (String serverName : serverNames) {
            urls.add("https://" + serverName + "/api/9/http/upstreams/");
        }
        return urls;
    }

    private HttpHeaders getHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return headers;
    }

    private void requestEndpoints(
            Map<String, Object> parameters,
            String upstreamName,
            long peerId) {

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setReadTimeout(500);
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(parameters, getHeaders());

        for (String url : getUrls()) {
            String endPoint = url + upstreamName + "/servers/" + peerId;
            ResponseEntity<String> response = restTemplate.exchange(
                    endPoint, HttpMethod.PATCH, entity, String.class
            );

            logger.info(response.getBody());
        }
    }
}
