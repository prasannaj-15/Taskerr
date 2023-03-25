package com.taskerr.app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskerr.app.Exception.AppUserException;
import com.taskerr.app.Model.AppUser;
import com.taskerr.app.Respository.AppUserRepository;

@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private AppUserRepository appUserRepository;
	
	@Override
	public AppUser registerUser(AppUser appUser) {
		
		return appUserRepository.save(appUser);
	}

	@Override
	public List<AppUser> viewAllUsers() {
		
		List<AppUser> usersList = appUserRepository.findAll();
		
		if(usersList.isEmpty())
			throw new AppUserException("No users are present");
			
		return usersList;
	}

}
