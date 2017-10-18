package com.example.transactional.services;

import com.example.transactional.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserServiceImpl implements UserService {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(String username, String password) {
        return jdbcTemplate.queryForObject(
            "INSERT INTO \"user\" (username, password) VALUES (?, ?) RETURNING *",
            new Object[]{username, passwordEncoder.encode(password)},
            (rs, rowNum) -> User.builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .build()
        );
    }
}
