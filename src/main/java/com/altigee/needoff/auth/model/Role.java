package com.altigee.needoff.auth.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode
public class Role{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private Type name;

    public enum Type{
        MEMBER,
        APPROVER,
        ADMIN,
        OWNER
    }
}
