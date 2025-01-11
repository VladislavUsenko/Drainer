package com.vladislavuss.nginxadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Peer {

    private long id;
    private String server;
    private String name;
    private String state;
    private int active;
}
