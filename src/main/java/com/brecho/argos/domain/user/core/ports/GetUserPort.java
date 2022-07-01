package com.brecho.argos.domain.user.core.ports;

import com.brecho.argos.domain.user.core.models.User;

public interface GetUserPort {
    boolean userExists(User user);
}
