package com.vladislavuss.nginxadmin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Peer {

    private long id;
    private String server;
    private String name;
    private String state;
    private int active;
}
