package com.example.demo.userservice;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.usermodel.User;



public interface UserService {

	User saveUserDetails(String userJ, MultipartFile profImg, MultipartFile aadharDoc, MultipartFile panDoc);

	User getSingleUser(int userId);

	Iterable<User> getAllUser();

	 String deleteUser(int userId);

}
