package com.webapp.usermanagementservice.mapper;

import com.webapp.usermanagementservice.dto.UserRequest;
import com.webapp.usermanagementservice.dto.UserResponse;
import com.webapp.usermanagementservice.model.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toDto(User user);
    User toEntityFromRequest(UserRequest userRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(UserRequest userRequest,@MappingTarget User user);
}
