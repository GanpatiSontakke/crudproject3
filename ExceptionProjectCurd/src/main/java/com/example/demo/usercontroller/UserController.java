package com.example.demo.usercontroller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.usermodel.User;
import com.example.demo.userservice.UserService;
@RestController
public class UserController {
	@Autowired
	UserService us;
	
	@PostMapping("/create_user")
	public ResponseEntity<User> saveUserDetails(
			@RequestPart(name="userJson",required = true) String userJ,
			@RequestPart(name="prof",required = true) MultipartFile profImg,
			@RequestPart(name="aadhar",required = true) MultipartFile aadharDoc,
			@RequestPart(name="pan",required = true) MultipartFile panDoc 
			)throws IOException
	{
		User udb=us.saveUserDetails(userJ,profImg,aadharDoc,panDoc);
		return new ResponseEntity<User>(udb,HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/expose_user/{userId}")
	public ResponseEntity<User> exposeUser(@PathVariable int userId){
		User user=us.getSingleUser(userId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<Iterable<User>> getAllUser(){
		Iterable<User> userlist=us.getAllUser();
		return new ResponseEntity<Iterable<User>>(userlist,HttpStatus.OK);
		
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<User> updateUser(
			@RequestPart(name="userJson",required = true) String userJ,
			@RequestPart(name="prof",required = true) MultipartFile profImg,
			@RequestPart(name="aadhar",required = true) MultipartFile aadharDoc,
			@RequestPart(name="pan",required = true) MultipartFile panDoc)
	{
		User u=us.saveUserDetails(userJ, profImg, aadharDoc, panDoc);
		return new ResponseEntity<User>(u,HttpStatus.CREATED);
		
	}
	
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId)
	{
		String msg=us.deleteUser(userId);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	
	
	

}
