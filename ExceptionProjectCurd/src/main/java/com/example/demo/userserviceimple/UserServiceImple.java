package com.example.demo.userserviceimple;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.usermodel.User;
import com.example.demo.userrepository.UserRepository;
import com.example.demo.userservice.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiceImple implements UserService {
	@Autowired
	UserRepository ur;

	@Override
	public User saveUserDetails(String userJ, MultipartFile profImg, MultipartFile aadharDoc, MultipartFile panDoc) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			User userv = mapper.readValue(userJ,User.class);
			System.out.println(userv);
			userv.setProfileImg(profImg.getBytes());
			userv.setAadharCard(aadharDoc.getBytes());
			if(panDoc!=null)
			{
				userv.setPanCard(panDoc.getBytes());
			}
			return ur.save(userv);
		}
		catch(JsonMappingException e) {
			e.printStackTrace();
		}catch(JsonProcessingException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public User getSingleUser(int userId) {
		
		Optional<User> opUser = ur.findById(userId);
		if(opUser.isPresent()) {
			return opUser.get();
		}else {
			// throw new UserNotFoundException();
		}
		
		return new User();
	}

	@Override
	public Iterable<User> getAllUser() {
		
		return ur.findAll();
	}

	@Override
	public String deleteUser(int userId) {
		ur.deleteById(userId);
		return "User is Deleted";
	}

}
