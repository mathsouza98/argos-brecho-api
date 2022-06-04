package com.brecho.argos.domain.user.adapters.persistance.repository;

import com.brecho.argos.domain.user.adapters.persistance.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, String> {
}