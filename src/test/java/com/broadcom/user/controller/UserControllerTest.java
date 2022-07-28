package com.broadcom.user.controller;

import com.broadcom.user.model.User;
import com.broadcom.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getAllUsers() throws Exception {
        List<User> user1List = new ArrayList<>();
        user1List.add(new User("maruthi", 30));
        user1List.add(new User("srinivas", 28));
        user1List.add(new User("reddy dheeraj", 26));
        user1List.add(new User("vamsi", 29));
        when(userService.findAll()).thenReturn(user1List);

        mockMvc.perform(MockMvcRequestBuilders.get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4)))
                .andDo(print());
    }
}
