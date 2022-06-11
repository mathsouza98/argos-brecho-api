package com.brecho.argos.domain.user.adapters.persistance.mapper;

import com.brecho.argos.domain.user.adapters.persistance.entity.UserEntity;
import com.brecho.argos.domain.user.core.models.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);
}
