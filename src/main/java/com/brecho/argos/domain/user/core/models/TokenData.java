package com.brecho.argos.domain.user.core.models;

import com.brecho.argos.domain.user.core.enums.Role;
import lombok.Builder;

@Builder
public class TokenData {
    private String userId;
    private Role role;
}
