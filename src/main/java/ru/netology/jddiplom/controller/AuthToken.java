package ru.netology.jddiplom.controller;

public class AuthToken {
    private String login;
    private String password;

    public AuthToken(){}

    public AuthToken(String login, String password) {
        this.login = login;
        this.password = password;
    }

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
        return "{\"auth-token\""+":"+"\""+getLogin()+","+getPassword()+"\""+"}";
    }
}
