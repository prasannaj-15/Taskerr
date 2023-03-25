package com.taskerr.app.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taskerr.app.Model.AppUser;
import com.taskerr.app.Respository.AppUserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private AppUserRepository appUserRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Optional<AppUser> opt= appUserRepository.findByEmail(username);

		if(opt.isPresent()) {
			
			AppUser appUser= opt.get();
			
			List<GrantedAuthority> authorities= new ArrayList<>();
			//authorities.add(new SimpleGrantedAuthority(customer.getRole()));
			
			return new User(appUser.getEmail(), appUser.getPassword(), authorities);
			
		}else
			throw new BadCredentialsException("User Details not found with this username: "+username);
		
	}

}
