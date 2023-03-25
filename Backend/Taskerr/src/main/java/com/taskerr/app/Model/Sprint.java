package com.taskerr.app.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.core.status.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sprint {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sprintId;
	private String sprintName;
	private LocalDate startDate;
	private LocalDate endDate;
	
	@OneToOne
	private AppUser sprintOwner;
	
	@Enumerated
	private StatusType status = StatusType.TODO;
	
	@OneToMany(mappedBy = "sprint",cascade = CascadeType.ALL)
	List<AppUser> sprintUsers = new ArrayList<>();

	@OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL)
	List<Task> tasks = new ArrayList<>();

	public Sprint(String sprintName, LocalDate startDate, LocalDate endDate) {
		super();
		this.sprintName = sprintName;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
}
