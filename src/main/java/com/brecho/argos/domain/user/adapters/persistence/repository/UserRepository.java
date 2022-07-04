package com.brecho.argos.domain.user.adapters.persistence.repository;

import com.brecho.argos.domain.user.adapters.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
