package com.broadcom.user.controller;

import com.broadcom.user.model.User;
import com.broadcom.user.model.User1;
import com.broadcom.user.model.UserRequest;
import com.broadcom.user.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private static final String RESILIENCE4J_INSTANCE_NAME = "findUsers";
    private static final String FALLBACK_METHOD = "fallback";

    @Autowired
    private UserService userService;

    @ApiOperation(value = "User Service ", response = Iterable.class, tags = "getAllUsers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @CircuitBreaker(name = RESILIENCE4J_INSTANCE_NAME, fallbackMethod = FALLBACK_METHOD)
    @GetMapping(value = "/users", produces = "application/json")
    ResponseEntity<List<User>> getAllUsers(HttpServletRequest request, HttpServletResponse response) {
        log.info("Started find users");
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<String> addUser(@RequestBody @Valid UserRequest request) {
        log.info("started processing addVehicles() with= {}", request);
        return ResponseEntity.ok(userService.createUser(request.getUsers()));
    }

    @PutMapping(value = "/modify")
    public ResponseEntity<String> updateUser(@RequestBody User1 user) {
        log.info("Starting updating the User");

        String response = userService.updateUserDetails(user);

        log.info("Updated User  {}", response);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<String> deleteUser(@RequestBody User1 user1) {
        log.info("Starting deleting the User");

        String response = userService.deleteUser(user1);

        log.info("deleted User  {}", response);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<List<User>> fallback(Exception ex) {
        log.warn("fallback executed");
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}