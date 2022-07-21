package com.brecho.argos.domain.user.core.models;

import com.brecho.argos.domain.user.core.enums.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Builder
@Getter
public class TokenData {
    private String userId;
    private List<Role> roles;
    Date expiration;
}
