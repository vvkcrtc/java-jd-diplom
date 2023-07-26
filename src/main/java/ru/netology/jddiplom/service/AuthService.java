package ru.netology.jddiplom.service;

import org.springframework.stereotype.Service;
import ru.netology.jddiplom.controller.AuthToken;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    private final List<AuthToken> activeTokens = new ArrayList<>();

    public AuthToken getAuthToken(String value) {
        value = value.replace("Bearer ","");
        String token[] = value.split(",");
        if(token.length > 1) {
            return new AuthToken(token[0],token[1]);
        }
        return null;
    }

    public boolean addActiveToken(AuthToken authToken) {
        if(!activeTokens.contains(authToken)) {
            activeTokens.add(authToken);
            return true;
        }
        return false;
    }

    public AuthToken findActiveAuthToken(String headToken) {
        AuthToken at = getAuthToken(headToken);
        for(AuthToken token : activeTokens) {
            if (token.getLogin().equals(at.getLogin()) && token.getPassword().equals(at.getPassword())) {
                return token;
            }
        }
        return null;
    }

    public boolean isActiveToken(String headToken) {
        if(findActiveAuthToken(headToken) != null) {
            return true;
        }
        return false;
    }

    public void delActiveAuthToken(String headToken) {
        AuthToken at = findActiveAuthToken(headToken);
        activeTokens.remove(at);
    }


}
