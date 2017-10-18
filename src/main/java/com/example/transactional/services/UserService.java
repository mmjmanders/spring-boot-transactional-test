package com.example.transactional.services;

import com.example.transactional.domain.User;

public interface UserService {

    User create(String username, String password);
}
