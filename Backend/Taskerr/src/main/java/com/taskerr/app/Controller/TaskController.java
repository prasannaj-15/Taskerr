package com.taskerr.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taskerr.app.DTO.SprintDTO;
import com.taskerr.app.DTO.TaskDTO;
import com.taskerr.app.Model.Sprint;
import com.taskerr.app.Model.Task;
import com.taskerr.app.Service.TaskService;

@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping("/tasks")
	public ResponseEntity<Task> createTaskHandler(@RequestBody TaskDTO taskDto){
			
		Task task = taskService.createTask(taskDto);
		
		return new ResponseEntity<>(task,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/sprints")
	public ResponseEntity<List<Task>> getAllTasksHandler(){
		
		List<Task> tasks = taskService.getAllTasks();
		
		return new ResponseEntity<>(tasks,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/assignTask/{taskId}/{assigneeId}")
	public ResponseEntity<Task> addAssigneeToTaskHandler(@PathVariable("taskId") Integer taskId,@PathVariable("assigneeId") Integer assigneeId){
			
		Task task = taskService.assignTaskToAssignee(taskId, assigneeId); 
		
		return new ResponseEntity<>(task,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/changeTaskStatus/{taskId}/{status}")
	public ResponseEntity<String> changeTaskStatusHandler(@PathVariable("taskId") Integer taskId,@PathVariable("status") String status){
			
		String message = taskService.changeTaskStatus(taskId, status); 
		
		return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
		
	}
	
}
