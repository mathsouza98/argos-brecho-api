package com.brecho.argos.domain.user.adapters.persistence;

import com.brecho.argos.domain.user.adapters.persistence.repository.UserRepository;
import com.brecho.argos.domain.user.core.models.User;
import com.brecho.argos.domain.user.core.ports.GetUserPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserPersistenceAdapter implements GetUserPort {
    private final UserRepository userRepository;

    @Override
    public boolean userExists(User user) {
        return userRepository.existsById(user.getId());
    }
}
