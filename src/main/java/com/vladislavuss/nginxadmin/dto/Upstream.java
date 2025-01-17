package com.vladislavuss.nginxadmin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
}
