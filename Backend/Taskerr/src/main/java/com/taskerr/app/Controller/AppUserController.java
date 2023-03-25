package com.taskerr.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taskerr.app.Model.AppUser;
import com.taskerr.app.Service.AppUserService;

import jakarta.websocket.server.PathParam;

@RestController
public class AppUserController {

	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/hello")
	public String testHandler() {
		return "Welcome to Taskerr";
	}
	
	@PostMapping("/users")
	public ResponseEntity<AppUser> saveCustomerHandler(@RequestBody AppUser appUser){

		
		appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
		
		AppUser registeredUser= appUserService.registerUser(appUser);
		
		return new ResponseEntity<>(registeredUser,HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/users")
	public ResponseEntity<List<AppUser>> getAllCustomerHandler(){
		
		
		List<AppUser> users = appUserService.viewAllUsers();
		
		return new ResponseEntity<>(users,HttpStatus.ACCEPTED);
		
	}
	
}
