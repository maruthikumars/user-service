package com.broadcom.user.service;

import com.broadcom.user.model.User;
import com.broadcom.user.model.User1;
import com.broadcom.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        List<User1> users = userRepository.findAll();

        //Todo: we can do Sorting in Parallel in Java with Executors
        return users.parallelStream().map(user1 -> new User(user1.getName(), user1.getAge())).sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
    }

    public String createUser(List<User1> users) {
        userRepository.saveAll(users);
        return "Users created";
    }

    public String updateUserDetails(User1 user) {
        Optional<User1> user1 = userRepository.findById(user.getId());
        if (user1.isPresent()) {
            userRepository.save(new User1(user.getId(), user.getName(), user.getAge(), user.getAddress1(), user.getAddress2()));
            log.info("User details updated");
            return "User details updated";
        } else {
            log.info("User not found");
            return "User not found";
        }
    }

    public String deleteUser(User1 user) {
        Optional<User1> user1 = userRepository.findById(user.getId());
        if (user1.isPresent()) {
            userRepository.deleteById(user.getId());
            log.info("User deleted");
            return "User deleted";
        } else {
            log.info("User not found");
            return "User not found";
        }
    }
}
