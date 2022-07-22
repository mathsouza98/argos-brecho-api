package com.brecho.argos.usecases.user;

import com.brecho.argos.domain.user.core.InvalidCredentialsExceptions;
import com.brecho.argos.domain.user.core.UserNotFoundException;
import com.brecho.argos.domain.user.core.models.User;
import com.brecho.argos.domain.user.core.ports.GetUserPort;
import com.brecho.argos.domain.user.core.ports.TokenPort;
import com.brecho.argos.domain.user.usecases.LoginUseCase;
import com.brecho.argos.factory.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LoginUseCaseTest {
    @InjectMocks
    private LoginUseCase loginUseCase;

    @Mock
    private TokenPort tokenPort;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private GetUserPort getUserPort;

    @BeforeEach
    private void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("It should login user when credentials are valid")
    void shouldLoginUser() {
        User user = UserFactory.createValidBuyer();
        User userToLogin = UserFactory.createValidBuyer();

        when(getUserPort.getUserByEmail(any(String.class))).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(any(String.class), any(String.class))).thenReturn(true);
        when(tokenPort.generateToken(any(User.class))).thenReturn("d3c42b7e-3ece-4c3b-a3b7-b18c5944a097");

        String token = loginUseCase.login(userToLogin);
        verify(tokenPort).generateToken(user);
        assertNotNull(token);
    }

    @Test
    @DisplayName("It should not login user when user is not found")
    void shouldNotLoginUserWhenUserIsNotFound() {
        User userToLogin = UserFactory.createValidBuyer();

        when(getUserPort.getUserByEmail(any(String.class))).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> loginUseCase.login(userToLogin));
    }

    @Test
    @DisplayName("It should not login user when invalid credentials are given")
    void shouldNotLoginUserWhenInvalidCredentialsAreGiven() {
        User user = UserFactory.createValidBuyer();
        User userToLogin = UserFactory.createValidBuyer();

        when(getUserPort.getUserByEmail(any(String.class))).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(any(String.class), any(String.class))).thenReturn(false);

        assertThrows(InvalidCredentialsExceptions.class, () -> loginUseCase.login(userToLogin));
    }
}
