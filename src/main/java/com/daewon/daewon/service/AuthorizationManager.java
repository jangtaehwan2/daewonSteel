package com.daewon.daewon.service;

public interface AuthorizationManager {
    boolean isUser(String token);
    boolean isAdmin(String token);
}
