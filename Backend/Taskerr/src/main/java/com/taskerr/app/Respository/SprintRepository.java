package com.taskerr.app.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskerr.app.Model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Integer> {

}
