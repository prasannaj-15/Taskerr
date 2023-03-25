package com.taskerr.app.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taskerr.app.DTO.SprintDTO;
import com.taskerr.app.Model.AppUser;
import com.taskerr.app.Model.Sprint;
import com.taskerr.app.Model.Task;
import com.taskerr.app.Service.SprintService;

@RestController
public class SprintController {

	private SprintService sprintService;
	
	/**
	 * 
	 * @param sprintDto - provide sprintDto object through request body to create new sprint
	 * @return new created sprint object
	 */
	@PostMapping("/sprint")
	public ResponseEntity<Sprint> createSprintHandler(@RequestBody SprintDTO sprintDto){
			
		Sprint sprint = sprintService.createSprint(sprintDto);
		
		return new ResponseEntity<>(sprint,HttpStatus.ACCEPTED);
		
	}
	
	/**
	 * 
	 * @param sprintId - It provide through path variable
	 * @param userList - It provide through request body
	 * @return list of all users in the particular sprint
	 */
	@PutMapping("/addUserIntoSprint/{sprintId}")
	public ResponseEntity<List<AppUser>> addUserIntoSprintHandler(@PathVariable("sprintId") Integer sprintId,@RequestBody List<AppUser> userList){
			
		List<AppUser> sprintUsersList = sprintService.addUsersToSprint(sprintId, userList);
		
		return new ResponseEntity<>(sprintUsersList,HttpStatus.ACCEPTED);
		
	}
	
	/**
	 * 
	 * @param sprintId - provide sprintId as path variable to get sprint details
	 * @return sprint object
	 */
	@GetMapping("/sprint/{sprintId}")
	public ResponseEntity<Sprint> getSprintHandler(@PathVariable("sprintId") Integer sprintId){
			
		Sprint sprint = sprintService.getSprintDetails(sprintId);
		
		return new ResponseEntity<>(sprint,HttpStatus.ACCEPTED);
		
	}
	
	/**
	 * 
	 * @param appUserId
	 * @param sprintId
	 * @return
	 */
	@PutMapping("/removeUserFromSprint/{appUserId}/{sprintId}")
	public ResponseEntity<List<AppUser>> removeUserFromSprintHandler(@PathVariable("appUserId") Integer appUserId,@PathVariable("sprintId") Integer sprintId){
			
		List<AppUser> sprintUsersList = sprintService.removeUserFromSprint(appUserId, sprintId);
		
		return new ResponseEntity<>(sprintUsersList,HttpStatus.ACCEPTED);
		
	}
	
	
	@PutMapping("/addTaskIntoSprint/{sprintId}")
	public ResponseEntity<List<Task>> addTaskIntoSprintHandler(@PathVariable("sprintId") Integer sprintId,@RequestBody List<Task> taskList){
			
		List<Task> sprintTaskList = sprintService.addTasksIntoSprint(sprintId, taskList);
		
		return new ResponseEntity<>(sprintTaskList,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/changeSprintStatus/{sprintId}/{status}")
	public ResponseEntity<String> changeSprintStatusHandler(@PathVariable("sprintId") Integer sprintId,@PathVariable("status") String status){
			
		sprintService.changeSprintStatus(sprintId, status);
		
		String message = "Status change succefully";
		
		return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/sprints")
	public ResponseEntity<List<Sprint>> getAllSprintsHandler(){
		
		
		List<Sprint> sprints = sprintService.getAllSprints();
		
		return new ResponseEntity<>(sprints,HttpStatus.ACCEPTED);
		
	}
}
