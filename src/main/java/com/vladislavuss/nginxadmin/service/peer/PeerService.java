package com.vladislavuss.nginxadmin.service.peer;

import com.vladislavuss.nginxadmin.dto.Peer;

public interface PeerService {

    void downPeer(Peer peer, String upstreamName);

    void drainPeer(Peer peer, String upstreamName);

    void upPeer(Peer peer, String upstreamName);
}
