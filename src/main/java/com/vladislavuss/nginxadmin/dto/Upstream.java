package com.vladislavuss.nginxadmin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Upstream {

    private String name;
    private List<Peer> peers;
    private int keepalive;
    private int zombies;
    private String zone;

    public Upstream(String name, Upstream upstream) {
        this.name = name;
        this.peers = upstream.getPeers();
        this.keepalive = upstream.getKeepalive();
        this.zombies = upstream.getZombies();
        this.zone = upstream.getZone();
    }

    @Override
    public String toString() {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "";

        try {
            json = ow.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }
}
