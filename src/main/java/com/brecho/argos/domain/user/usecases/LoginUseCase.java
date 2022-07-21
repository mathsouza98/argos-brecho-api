package com.brecho.argos.domain.user.usecases;

import com.brecho.argos.domain.user.core.InvalidCredentialsExceptions;
import com.brecho.argos.domain.user.core.UserNotFoundException;
import com.brecho.argos.domain.user.core.models.User;
import com.brecho.argos.domain.user.core.ports.GetUserPort;
import com.brecho.argos.domain.user.core.ports.TokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginUseCase {
    private final TokenPort tokenPort;
    private final PasswordEncoder passwordEncoder;
    private final GetUserPort getUserPort;

    String login(User userToLogin) {
        User user = getUserPort.getUserByEmail(userToLogin.getEmail()).orElseThrow(UserNotFoundException::new);
        boolean passwordDoesNotMatch = !passwordEncoder.matches(userToLogin.getPassword(), user.getPassword());
        if (passwordDoesNotMatch) {
            throw new InvalidCredentialsExceptions();
        }
        return tokenPort.generateToken(user);
    }
}
