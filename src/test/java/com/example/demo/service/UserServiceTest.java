package com.example.demo.service;

import com.example.demo.Service.UserService;
import com.example.demo.SpidApplication;
import com.example.demo.Entities.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = {SpidApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

    @Mock
    private UserService userService;

    @BeforeAll
    public void setup() {
        User user = new User();
        user.setId(1);

        when(userService.getUserById(1)).thenReturn(Optional.of(user));
        when(userService.getUserById(anyInt())).thenThrow(new RuntimeException("Exception"));
    }

    @Test
    public void testDatabaseRetrievalForUsers() {
        assertInstanceOf(User.class, userService.getUserById(1).get());
        assertThrows(Exception.class, () -> userService.getUserById(2).get());
    }

}