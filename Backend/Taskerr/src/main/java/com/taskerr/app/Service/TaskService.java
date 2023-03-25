package com.taskerr.app.Service;

import java.util.List;

import com.taskerr.app.DTO.TaskDTO;
import com.taskerr.app.Exception.AppUserException;
import com.taskerr.app.Exception.SprintException;
import com.taskerr.app.Exception.TaskException;
import com.taskerr.app.Model.Task;


public interface TaskService {
	
	public Task createTask(TaskDTO taskDetails);
	
	public List<Task> getAllTasks();
	
	public Task assignTaskToAssignee(Integer taskId, Integer assigneeId) throws TaskException,AppUserException;
	
	public String changeTaskStatus(Integer taskId, String status) throws TaskException;
}
