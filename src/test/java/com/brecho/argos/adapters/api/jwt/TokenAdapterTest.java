package com.brecho.argos.adapters.api.jwt;

import com.brecho.argos.domain.user.adapters.jwt.TokenAdapter;
import com.brecho.argos.domain.user.core.models.TokenData;
import com.brecho.argos.domain.user.core.models.User;
import com.brecho.argos.factory.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class TokenAdapterTest {
    @Spy
    private TokenAdapter tokenAdapter = new TokenAdapter("bifwmwDP4oFH+bifwmwDP4oFH+TYjtHzFv7HrHamPjtsZVAI61rZu5M8R29PZEbtdt76abAM7Qgk3Wvi3Kj+KzD/CmA+or28TyuJOF4VfUukzCM2AiylpcaMJU/WlUWKxek6qYjf7i70PAaFq2g==", 6000);

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("It should generate access token")
    void shouldGenerateToken() {
        //given
        User buyer = UserFactory.createValidBuyer();

        //when
        String jwt = tokenAdapter.generateToken(buyer);
        TokenData tokenData = tokenAdapter.getTokenData(jwt);

        //then
        assertNotNull(jwt);
        assertEquals(buyer.getId(), tokenData.getUserId());
        assertIterableEquals(buyer.getRoles(), tokenData.getRoles());
    }

    @Test
    @DisplayName("It should refresh access token expiration")
    void shouldRefreshToken() throws InterruptedException {
        //given
        User buyer = UserFactory.createValidBuyer();

        //when
        String jwt = tokenAdapter.generateToken(buyer);
        TokenData generatedTokenData = tokenAdapter.getTokenData(jwt);
        TimeUnit.SECONDS.sleep(1);
        String refreshedJwt = tokenAdapter.refreshToken(jwt);
        TokenData refreshedTokenData = tokenAdapter.getTokenData(refreshedJwt);

        //then
        assertNotNull(refreshedJwt);
        assertEquals(generatedTokenData.getUserId(), refreshedTokenData.getUserId());
        assertEquals(generatedTokenData.getRoles(), refreshedTokenData.getRoles());
        assertTrue(refreshedTokenData.getExpiration().after(generatedTokenData.getExpiration()));
    }
}
