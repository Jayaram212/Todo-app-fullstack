package com.jayaram.full_stack_backend.repository;

import com.jayaram.full_stack_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {

}
