package com.example.TaskManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TaskManagementSystem.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	
}
