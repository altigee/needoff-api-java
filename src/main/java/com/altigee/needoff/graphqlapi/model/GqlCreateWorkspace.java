package com.altigee.needoff.graphqlapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GqlCreateWorkspace{
  private Boolean ok;
  private GqlWorkspace ws;

}
