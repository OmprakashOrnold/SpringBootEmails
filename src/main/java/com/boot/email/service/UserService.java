package com.boot.email.service;

import com.boot.email.domains.User;

public interface UserService {
    User saveUser(User user);

    Boolean verifyToken(String token);
}
