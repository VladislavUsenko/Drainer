package com.vladislavuss.nginxadmin.service.upstream;

import com.vladislavuss.nginxadmin.dto.Upstream;

public interface UpstreamService {

    Upstream getUpstream(String name);
}
