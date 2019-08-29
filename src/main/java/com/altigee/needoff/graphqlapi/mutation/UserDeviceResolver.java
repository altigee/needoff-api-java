package com.altigee.needoff.graphqlapi.mutation;

import com.altigee.needoff.auth.service.AuthenticationFacade;
import com.altigee.needoff.graphqlapi.error.GqlError;
import com.altigee.needoff.graphqlapi.mapper.GqlUserDeviceMapper;
import com.altigee.needoff.graphqlapi.model.GqlUserDevice;
import com.altigee.needoff.users.model.UserDevice;
import com.altigee.needoff.users.service.UserDeviceService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class UserDeviceResolver implements GraphQLMutationResolver{

    @Autowired
    private AuthenticationFacade authenticationFacade;
    @Autowired
    private UserDeviceService userDeviceService;

    public GqlUserDevice saveUserDevice(String token) {

        return authenticationFacade
            .getAuthenticatedAccount()
            .map(a -> UserDevice.builder().account(a).token(token).build())
            .map(userDeviceService::save)
            .map(GqlUserDeviceMapper.INSTANCE::userDeviceToGraphQL)
            .orElseThrow(() -> new GqlError("cannot associate account with a token"));
    }

    public GqlUserDevice removeUserDevice(String token) {

        return userDeviceService
            .delete(token)
            .map(GqlUserDeviceMapper.INSTANCE::userDeviceToGraphQL)
            .orElseThrow(() -> new GqlError("cannot delete a device"));
    }
}
