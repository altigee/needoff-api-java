package com.altigee.needoff.graphqlapi.query;

import com.altigee.needoff.auth.service.AccountService;
import com.altigee.needoff.graphqlapi.error.GqlError;
import com.altigee.needoff.graphqlapi.mapper.GqlAccountMapper;
import com.altigee.needoff.graphqlapi.model.GqlAccount;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountQueryResolver implements GraphQLQueryResolver {

    @Autowired private AccountService accountService;

    public GqlAccount getAccount(Long accountId) {
        return accountService
            .find(accountId)
            .map(GqlAccountMapper.INSTANCE::toGraphQL)
            .orElseThrow(() -> new GqlError("cannot find an account"));
    }
}
