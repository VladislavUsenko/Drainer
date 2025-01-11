package com.vladislavuss.nginxadmin.controller;


import com.vladislavuss.nginxadmin.dto.Peer;
import com.vladislavuss.nginxadmin.service.PeerService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeerController {

    private final PeerService peerService;

    public PeerController(PeerService peerService) {
        this.peerService = peerService;
    }

    @PatchMapping("/drain/{upstreamName}")
    public void drain(@RequestBody Peer peer,
                      @PathVariable String upstreamName) {

        peerService.drainPeer(peer, upstreamName);
    }
}
