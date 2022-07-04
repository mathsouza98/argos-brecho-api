package com.brecho.argos.domain.user.adapters.persistence.mapper;

import com.brecho.argos.domain.user.adapters.persistence.entity.PhoneEntity;
import com.brecho.argos.domain.user.core.models.Phone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface PhoneMapper {

    PhoneEntity toEntity(Phone phone);

    Phone toDomain(PhoneEntity phoneEntity);
}
