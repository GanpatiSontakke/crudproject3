package com.example.demo.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.usermodel.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
