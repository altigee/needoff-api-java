package com.altigee.needoff.graphqlapi.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GqlAccount {

    private Long id;

    private LocalDateTime createdTime;
    private String email;

    private List<String> roles;

}
