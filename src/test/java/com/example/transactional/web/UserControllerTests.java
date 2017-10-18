package com.example.transactional.web;

import com.example.transactional.domain.User;
import com.example.transactional.web.api.UserRequest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Transactional
@Rollback
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTests {

    private static final String ENDPOINT = "/v1/users";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test_user_create() {
        HttpEntity<UserRequest> request = new HttpEntity<>(new UserRequest("admin", "admin"));
        ResponseEntity<User> response = restTemplate.exchange(ENDPOINT, HttpMethod.POST, request, User.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void test_user_create_second_time_should_pass() {
        HttpEntity<UserRequest> request = new HttpEntity<>(new UserRequest("admin", "admin"));
        ResponseEntity<User> response = restTemplate.exchange(ENDPOINT, HttpMethod.POST, request, User.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
