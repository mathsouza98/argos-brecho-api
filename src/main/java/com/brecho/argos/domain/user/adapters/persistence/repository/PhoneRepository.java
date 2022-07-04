package com.brecho.argos.domain.user.adapters.persistence.repository;

import com.brecho.argos.domain.user.adapters.persistence.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, String> {
}
