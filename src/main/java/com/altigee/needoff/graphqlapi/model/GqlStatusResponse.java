package com.altigee.needoff.graphqlapi.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GqlStatusResponse {
    private Boolean ok;
}
