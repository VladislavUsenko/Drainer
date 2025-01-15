package com.vladislavuss.nginxadmin.controller;


import com.vladislavuss.nginxadmin.dto.Peer;
import com.vladislavuss.nginxadmin.service.peer.PeerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/peer")
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

    @PatchMapping("/down/{upstreamName}")
    public void down(@RequestBody Peer peer,
                      @PathVariable String upstreamName) {

        peerService.downPeer(peer, upstreamName);
    }

    @PatchMapping("/up/{upstreamName}")
    public void up(@RequestBody Peer peer,
                     @PathVariable String upstreamName) {

        peerService.upPeer(peer, upstreamName);
    }
}
