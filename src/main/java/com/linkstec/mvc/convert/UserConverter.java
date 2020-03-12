package com.linkstec.mvc.convert;


import com.linkstec.mvc.domain.User;
import com.linkstec.mvc.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface UserConverter {

    UserConverter instance = Mappers.getMapper(UserConverter.class);

    @Mappings({
            @Mapping(source = "name",target = "loginName"),
            @Mapping(source = "password",target = "pwd")
    })
    UserDto domain2dto(User user);

    @Mappings({
            @Mapping(target = "name",source = "loginName"),
            @Mapping(target = "password",source = "pwd")
    })
    User dto2Domain(UserDto ud);
}
