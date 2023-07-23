package com.example.TaskManagementSystem.RepositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.TaskManagementSystem.model.Task;
import com.example.TaskManagementSystem.repository.TaskRepository;

@DataJpaTest
public class TaskRepositoryTest {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Test
	public void testSaveAndGetTask() {
		// Create a sample task and save it to the repository
		Task task = new Task();
		
		task.setTitle("Sample Task");
		task.setStatus("Pending");
		
		Task savedTask = taskRepository.save(task);
		
		// Retrieve the task from the repository
		Task retrievedTask = taskRepository.findById(savedTask.getId()).orElse(null);
		
		// Check if the retrieved task matches the saved task
		assertNotNull(retrievedTask);
		
		assertEquals("Sample Task", retrievedTask.getTitle());
		assertEquals("Pending", retrievedTask.getStatus());
		
	}
	
}
