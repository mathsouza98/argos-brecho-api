package com.brecho.argos.domain.user.adapters.persistence.mapper;

import com.brecho.argos.domain.user.adapters.persistence.entity.UserEntity;
import com.brecho.argos.domain.user.core.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);
}
