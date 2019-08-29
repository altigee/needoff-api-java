package com.altigee.needoff.users.model;

import com.altigee.needoff.auth.model.Account;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


// TODO: might be needed
@Entity
@Data
public class WorkspaceAccountRole implements Serializable {

    @EmbeddedId
    private WorkspaceAccountRoleId workspaceAccountRoleId;

    @MapsId("accountId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId", insertable = false, updatable = false, nullable = false)
    private Account account;

    @MapsId("workspaceId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspaceId", insertable = false, updatable = false, nullable = false)
    private Workspace workspace;


    public enum Role {
        MEMBER,
        APPROVER,
        ADMIN,
        OWNER
    }

}
