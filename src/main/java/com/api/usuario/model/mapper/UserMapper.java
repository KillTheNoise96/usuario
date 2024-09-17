package com.api.usuario.model.mapper;


import com.api.usuario.model.dto.UserDto;
import com.api.usuario.model.dto.UserNewDto;
import com.api.usuario.model.entity.User;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(
        componentModel = "spring",
        uses = {PhoneMapper.class},
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name", ignore = true)
    @Mapping(target = "email", source = "email", ignore = true)
    @Mapping(target = "password", source = "password", ignore = true)
    @Mapping(target = "phones", source = "phones", ignore = true)
    @Mapping(target = "created", source = "created")
    @Mapping(target = "modified", source = "modified")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "token", source = "token")
    UserDto userToUserDto(User usuario);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "created", target = "created")
    @Mapping(source = "modified", target = "modified")
    @Mapping(source = "active", target = "active")
    @Mapping(source = "phones", target = "phones")
    User userDtoToUser(UserDto usuario);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name", ignore = true)
    @Mapping(target = "email", source = "email", ignore = true)
    @Mapping(target = "password", source = "password", ignore = true)
    @Mapping(target = "phones", source = "phones", ignore = true)
    @Mapping(target = "created", source = "created")
    @Mapping(target = "modified", source = "modified")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "token", source = "token")
    UserNewDto userToUserNewDto(User usuario);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "created", target = "created")
    @Mapping(source = "modified", target = "modified")
    @Mapping(source = "active", target = "active")
    @Mapping(source = "phones", target = "phones")
    User userNewDtoToUser(UserNewDto usuario);


    @Named(value = "users")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password", ignore = true)
    @Mapping(target = "created", source = "created")
    @Mapping(target = "modified", source = "modified")
    @Mapping(target = "lastLogin", source = "lastLogin")
    @Mapping(target = "token", source = "token", ignore = true)
    @Mapping(target = "active", source = "active")
    @Mapping(target = "phones", source = "phones")
    UserDto convertTo(User user);

    @IterableMapping(qualifiedByName = "users")
    List<UserDto> map(List<User> userList);
}
