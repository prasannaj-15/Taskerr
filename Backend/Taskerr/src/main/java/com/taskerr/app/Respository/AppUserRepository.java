package com.taskerr.app.Respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskerr.app.DTO.AppUserDTO;
import com.taskerr.app.Model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
	public Optional<AppUser> findByEmail(String email);
}
