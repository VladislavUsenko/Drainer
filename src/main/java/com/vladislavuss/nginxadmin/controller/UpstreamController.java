package com.vladislavuss.nginxadmin.controller;

import com.vladislavuss.nginxadmin.dto.Upstream;
import com.vladislavuss.nginxadmin.service.upstream.UpstreamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController("/upstream")
public class UpstreamController {

    private final UpstreamService upstreamService;

    public UpstreamController(UpstreamService upstreamService) {
        this.upstreamService = upstreamService;
    }

    @GetMapping
    public Upstream GetUpstream(@PathVariable String name) {
         return upstreamService.getUpstream(name);
    }
}
