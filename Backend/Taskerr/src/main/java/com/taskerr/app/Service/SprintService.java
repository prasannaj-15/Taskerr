package com.taskerr.app.Service;

import java.util.List;

import com.taskerr.app.DTO.SprintDTO;
import com.taskerr.app.Exception.AppUserException;
import com.taskerr.app.Exception.SprintException;
import com.taskerr.app.Exception.TaskException;
import com.taskerr.app.Model.AppUser;
import com.taskerr.app.Model.Sprint;
import com.taskerr.app.Model.Task;

public interface SprintService {

	public Sprint createSprint(SprintDTO sprintDetails);
	
	public List<Sprint> getAllSprints();
	
	public List<AppUser> addUsersToSprint(Integer sprintId, List<AppUser> userList) throws SprintException;
	
	public Sprint getSprintDetails(Integer sprintId) throws SprintException;
	
	public List<AppUser> removeUserFromSprint(Integer appUserId,Integer sprintId) throws AppUserException;
	
	public List<Task> addTasksIntoSprint(Integer sprintId,List<Task> taskList) throws SprintException,TaskException;
	
	public void changeSprintStatus(Integer sprintId, String status) throws SprintException;
}
