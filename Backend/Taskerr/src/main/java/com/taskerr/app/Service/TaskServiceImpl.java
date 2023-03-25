package com.taskerr.app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.taskerr.app.DTO.TaskDTO;
import com.taskerr.app.Exception.AppUserException;
import com.taskerr.app.Exception.SprintException;
import com.taskerr.app.Exception.TaskException;
import com.taskerr.app.Model.AppUser;
import com.taskerr.app.Model.Sprint;
import com.taskerr.app.Model.StatusType;
import com.taskerr.app.Model.Task;
import com.taskerr.app.Respository.AppUserRepository;
import com.taskerr.app.Respository.TaskRepository;

public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Override
	public Task createTask(TaskDTO taskDetails) {
		Task task = new Task();
		task.setTaskName(taskDetails.getTaskName());
		task.setDescription(taskDetails.getDescription());
		
		//here we set authorized logged in user to owner of this sprint 
		//sprint.setSprintOwner(null);
		
		return taskRepository.save(task);
	}

	@Override
	public List<Task> getAllTasks() {
		List<Task> taskList = taskRepository.findAll();
		return taskList;
	}

	@Override
	public Task assignTaskToAssignee(Integer taskId, Integer assigneeId) throws TaskException, AppUserException {

		AppUser appUser = appUserRepository.findById(assigneeId).orElseThrow(() -> new AppUserException("User is not exist with userId : "+assigneeId));
		Task task = taskRepository.findById(taskId).orElseThrow(() -> new AppUserException("Task is not exist with taskID : "+taskId));
		
		task.setAssignee(appUser);
		appUser.getTasks().add(task);
		
		appUserRepository.save(appUser);
		
		taskRepository.save(task);
		
		return task;
	}

	@Override
	public String changeTaskStatus(Integer taskId, String status) throws TaskException {
		
		Task task = taskRepository.findById(taskId).orElseThrow(() -> new AppUserException("Task is not exist with taskID : "+taskId));
		
		status = status.toUpperCase();
		
		if(status.equals("COMPLETED")) {
			task.setStatus(StatusType.COMPLETED);
		}
		else if(status.equals("INPROGRESS")) {
			task.setStatus(StatusType.INPROGRESS);
		}
		else if(status.equals("TODO")) {
			task.setStatus(StatusType.TODO);
		}
		else {
			throw new TaskException("Invalid Status");
		}
		
		taskRepository.save(task);
		return "Status updated successfully..";
	}

}
