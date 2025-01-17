package com.vladislavuss.nginxadmin.service.upstream;

import com.vladislavuss.nginxadmin.dto.Upstream;

import java.util.List;

public interface UpstreamService {

    Upstream getUpstream(String name);
    List<Upstream> getAllUpstreams();
}
