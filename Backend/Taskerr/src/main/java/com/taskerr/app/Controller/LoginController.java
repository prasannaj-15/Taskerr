package com.taskerr.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskerr.app.Model.AppUser;
import com.taskerr.app.Respository.AppUserRepository;

@RestController
public class LoginController {

	@Autowired
	private AppUserRepository appUserRepository;
	
	@GetMapping("/signIn")
	public ResponseEntity<AppUser> getLoggedInCustomerDetailsHandler(Authentication auth){
		
		
		AppUser user = appUserRepository.findByEmail(auth.getName()).orElseThrow(() -> new BadCredentialsException("Invalid Username or password"));
		
		 //to get the token in body, pass HttpServletResponse inside this method parameter 
		// System.out.println(response.getHeaders(SecurityConstants.JWT_HEADER)
			System.out.println("SingIn");
		
		 return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
		
		
	}
	
}
