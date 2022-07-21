package com.brecho.argos.domain.user.adapters.persistence;

import com.brecho.argos.domain.user.adapters.persistence.mapper.UserMapper;
import com.brecho.argos.domain.user.adapters.persistence.repository.UserRepository;
import com.brecho.argos.domain.user.core.models.User;
import com.brecho.argos.domain.user.core.ports.GetUserPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserPersistenceAdapter implements GetUserPort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public boolean userExists(User user) {
        return userRepository.existsById(user.getId());
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userMapper.toDomain(userRepository.findByEmail(email));
    }
}
