package com.brecho.argos.domain.user.adapters.persistance.mapper;

import com.brecho.argos.domain.user.adapters.persistance.entity.PhoneEntity;
import com.brecho.argos.domain.user.core.models.Phone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface PhoneMapper {

    PhoneEntity toEntity(Phone phone);

    Phone toDomain(PhoneEntity phoneEntity);
}
