package com.taskerr.app.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer taskId;
	private String taskName;
	private String description;
	
	@Enumerated(EnumType.STRING)
	private StatusType status = StatusType.TODO;
	
	@OneToOne
	private AppUser taskOwner; 
	
	
	@ManyToOne
	private Sprint sprint;
	
	@ManyToOne
	private AppUser assignee;

	public Task(String taskName, String description) {
		super();
		this.taskName = taskName;
		this.description = description;
	}
	
	
	

}
