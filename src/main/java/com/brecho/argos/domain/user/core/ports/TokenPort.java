package com.brecho.argos.domain.user.core.ports;

import com.brecho.argos.domain.user.core.models.TokenData;
import com.brecho.argos.domain.user.core.models.User;

public interface TokenPort {
    String generateToken(User user);
    TokenData getTokenData(String token);
    String refreshToken(String token);
}
