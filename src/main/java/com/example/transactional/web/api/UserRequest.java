package com.example.transactional.web.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@RequiredArgsConstructor(onConstructor = @__({@JsonCreator}))
public class UserRequest {

    @NotNull
    String username;

    @NotNull
    String password;
}
