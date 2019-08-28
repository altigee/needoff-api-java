package com.altigee.needoff.users.model;

import com.altigee.needoff.auth.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDevice {

    @Id
    @Size(min = 12, max = 32)
    private String token;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;
}
