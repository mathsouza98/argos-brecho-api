package com.brecho.argos.domain.user.core.ports;

import com.brecho.argos.domain.user.core.models.User;

import java.util.Optional;

public interface GetUserPort {
    boolean userExists(User user);
    Optional<User> getUserByEmail(String email);
}
