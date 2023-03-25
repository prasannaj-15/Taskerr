package com.taskerr.app.Service;

import java.util.List;

import com.taskerr.app.Model.AppUser;

public interface AppUserService {

	public AppUser registerUser(AppUser appUser);
	
	public List<AppUser> viewAllUsers(); 
	
}
