package com.brecho.argos.domain.user.adapters.persistence.mapper;

import com.brecho.argos.domain.user.adapters.persistence.entity.AddressEntity;
import com.brecho.argos.domain.user.core.models.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface AddressMapper {

    AddressEntity toEntity(Address address);

    Address toDomain(AddressEntity addressEntity);
}
