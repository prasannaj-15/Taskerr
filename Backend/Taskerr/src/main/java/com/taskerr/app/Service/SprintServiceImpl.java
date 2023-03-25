package com.taskerr.app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskerr.app.DTO.SprintDTO;
import com.taskerr.app.Exception.AppUserException;
import com.taskerr.app.Exception.SprintException;
import com.taskerr.app.Exception.TaskException;
import com.taskerr.app.Model.AppUser;
import com.taskerr.app.Model.Sprint;
import com.taskerr.app.Model.StatusType;
import com.taskerr.app.Model.Task;
import com.taskerr.app.Respository.AppUserRepository;
import com.taskerr.app.Respository.SprintRepository;
import com.taskerr.app.Respository.TaskRepository;

@Service
public class SprintServiceImpl implements SprintService {

	@Autowired
	private SprintRepository sprintRepository;
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public Sprint createSprint(SprintDTO sprintDetails) {
		
		Sprint sprint = new Sprint();
		sprint.setSprintName(sprintDetails.getSprintName());
		sprint.setStartDate(sprintDetails.getStartDate());
		sprint.setEndDate(sprintDetails.getEndDate());
		
		//here we set authorized logged in user to owner of this sprint 
		//sprint.setSprintOwner(null);
		
		return sprintRepository.save(sprint);
		
	}

	@Override
	public List<AppUser> addUsersToSprint(Integer sprintId, List<AppUser> userList) throws SprintException {
		
		Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(() -> new SprintException("Sprint not found with sprintId : "+sprintId));
		
		List<AppUser> sprintUsers = sprint.getSprintUsers();
		
		for(AppUser user : userList) {
			sprintUsers.add(user);
			user.setSprint(sprint);
			appUserRepository.save(user);
		}
		
		 sprint.setSprintUsers(sprintUsers);
		 sprintRepository.save(sprint);
		
		return sprintUsers;
	}

	@Override
	public Sprint getSprintDetails(Integer sprintId) throws SprintException {
		
		Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(() -> new SprintException("Sprint not found with sprintId : "+sprintId));		
		return sprint;
	}

	@Override
	public List<AppUser> removeUserFromSprint(Integer appUserId, Integer sprintId) throws AppUserException {
		
		//first we check authorized user is sprint owner or not
		
		AppUser appUser = appUserRepository.findById(appUserId).orElseThrow(() -> new AppUserException("User is not exist with userId : "+appUserId));
		Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(() -> new SprintException("Sprint not found with sprintId : "+sprintId));
		
		List<AppUser> sprintUsers = sprint.getSprintUsers();
		
		boolean isUserpresent = sprintUsers.contains(appUser);
		
		if(isUserpresent) {
			sprintUsers.remove(appUser);
			sprint.setSprintUsers(sprintUsers);
			sprintRepository.save(sprint);
		}
		else {
			throw new SprintException("No such user is present in this sprint");
		}
		
		return sprintUsers;
	}

	@Override
	public List<Task> addTasksIntoSprint(Integer sprintId, List<Task> taskList) throws SprintException, TaskException {
		
		Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(() -> new SprintException("Sprint not found with sprintId : "+sprintId));
		
		List<Task> sprintTaskList = sprint.getTasks();
		
		for(Task task : taskList) {
			sprintTaskList.add(task);
			task.setSprint(sprint);
			taskRepository.save(task);
		}
		
		 sprint.setTasks(sprintTaskList);
		 sprintRepository.save(sprint);
		
		return sprintTaskList;
	}

	@Override
	public void changeSprintStatus(Integer sprintId, String status) throws SprintException {

		Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(() -> new SprintException("Sprint not found with sprintId : "+sprintId));
		
		status = status.toUpperCase();
		
		if(status.equals("COMPLETED")) {
			sprint.setStatus(StatusType.COMPLETED);
		}
		else if(status.equals("INPROGRESS")) {
			sprint.setStatus(StatusType.INPROGRESS);
		}
		else if(status.equals("TODO")) {
			sprint.setStatus(StatusType.TODO);
		}
		else {
			throw new SprintException("Invalid Status");
		}
		
		sprintRepository.save(sprint);
	}

	@Override
	public List<Sprint> getAllSprints() {
		
		List<Sprint> sprintList = sprintRepository.findAll();
		return sprintList;
	}

}
