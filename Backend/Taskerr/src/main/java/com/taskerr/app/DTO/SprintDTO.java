package com.taskerr.app.DTO;

import java.time.LocalDate;
import lombok.Data;

@Data
public class SprintDTO {

	private String sprintName;
	private LocalDate startDate;
	private LocalDate endDate;

}
