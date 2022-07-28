package com.broadcom.user.service;

import com.broadcom.user.model.User;
import com.broadcom.user.model.User1;
import com.broadcom.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void getAllUsers() {

        List<User1> users = new ArrayList<>();
        users.add(new User1(123L, "maruthi", 30, "20 river court", "apt 1903"));
        users.add(new User1(234L, "srinivas", 28, "20 river court", "apt 1903"));
        users.add(new User1(345L, "reddy dheeraj", 26, "avalon co", "apt 101"));
        users.add(new User1(567L, "vamsi", 29, "221 st.paul ave", "apt 506"));

        userRepository.saveAll(users);
        UserService userService = new UserService(userRepository);

        List<User> user1List = userService.findAll();

        Assertions.assertEquals(users.size(), user1List.size());
    }
}
