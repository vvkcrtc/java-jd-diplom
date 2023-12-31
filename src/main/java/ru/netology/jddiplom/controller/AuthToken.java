package ru.netology.jddiplom.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthToken {
    private String login;
    private String password;

    public AuthToken(){}

    public AuthToken(String login, String password) {
        this.login = login;
        this.password = password;
    }
    //@JsonProperty("auth-token")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return getLogin()+","+getPassword();
    }
}
