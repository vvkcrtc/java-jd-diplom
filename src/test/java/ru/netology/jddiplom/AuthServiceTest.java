package ru.netology.jddiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.jddiplom.controller.AuthToken;
import ru.netology.jddiplom.service.AuthService;

import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;


public class AuthServiceTest {
    private AuthService authService;
    String username = "user1";
    String password = "12345";

    String headToken = "Bearer ".concat(username).concat(",").concat(password);
    AuthToken actualToken;
    @BeforeEach
    void setup() {
        authService = new AuthService();
        authService.addActiveToken(new AuthToken(username,password));
        actualToken = new AuthToken(username,password);
    }

    @Test
    void findActiveAuthTokenSuccessfully() {
        AuthToken token = authService.findActiveAuthToken(headToken);
        Assertions.assertEquals(token.getLogin(),actualToken.getLogin());
    }

    @Test
    void isActiveTokenSuccessfully() {
        Assertions.assertEquals(authService.isActiveToken(headToken),true);
    }

    @Test
    void addActiveTokenFalse() {
        Assertions.assertEquals(authService.addActiveToken(actualToken), false);
    }

    @Test
    void delActiveAuthTokenSuccessfully() {
        authService.delActiveAuthToken(headToken);
        Assertions.assertEquals(authService.findActiveAuthToken(headToken), null);
    }


}
