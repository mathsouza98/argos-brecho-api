package com.brecho.argos.domain.user.adapters.persistence.mapper;

import com.brecho.argos.domain.user.adapters.persistence.entity.UserEntity;
import com.brecho.argos.domain.user.core.models.User;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);

    default Optional<User> toDomain(Optional<UserEntity> optionalEntity) {
        UserEntity entity = optionalEntity.orElse(null);
        return entity == null ? Optional.empty() : Optional.of(toDomain(entity));
    }
}
