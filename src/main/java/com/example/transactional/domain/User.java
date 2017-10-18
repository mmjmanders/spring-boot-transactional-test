package com.example.transactional.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {
    Long id;
    String username;
}
