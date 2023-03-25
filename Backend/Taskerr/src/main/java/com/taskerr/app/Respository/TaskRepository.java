package com.taskerr.app.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskerr.app.Model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
