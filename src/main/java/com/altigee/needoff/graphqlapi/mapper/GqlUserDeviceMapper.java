package com.altigee.needoff.graphqlapi.mapper;

import com.altigee.needoff.graphqlapi.model.GqlUserDevice;
import com.altigee.needoff.users.model.UserDevice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GqlUserDeviceMapper {

    GqlUserDeviceMapper INSTANCE = Mappers.getMapper(GqlUserDeviceMapper.class);

    @Mapping(source = "account.id", target = "userId")
    GqlUserDevice userDeviceToGraphQL(UserDevice model);
}
