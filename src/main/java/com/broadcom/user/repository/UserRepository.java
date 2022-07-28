package com.broadcom.user.repository;

import com.broadcom.user.model.User1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User1, Long> {
}
