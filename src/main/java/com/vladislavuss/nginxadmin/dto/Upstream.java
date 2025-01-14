package com.vladislavuss.nginxadmin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Upstream {

    private List<Peer> peers;
}
