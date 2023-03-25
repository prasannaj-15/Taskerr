package com.taskerr.app.Exception;

public class TaskException extends RuntimeException{

	public TaskException() {}
	public TaskException(String message) {
		super(message);
	}
}
