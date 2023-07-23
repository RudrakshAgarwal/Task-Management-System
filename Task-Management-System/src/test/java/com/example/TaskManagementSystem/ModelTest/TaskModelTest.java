package com.example.TaskManagementSystem.ModelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.example.TaskManagementSystem.model.Task;

public class TaskModelTest {
	
	@Test
	public void testTaskModelCreation()
	{
		// Create a sample task		
		Task task = new Task();
		task.setId(1L);
		task.setTitle("Sample Task");
		task.setDate(LocalDate.now());
		task.setStatus("Pending");
		
		
		// Check if all the properties are set correctly
		assertEquals(1L, task.getId());
		assertEquals("Sample Task", task.getTitle());
		assertNotNull(task.getDate());
		assertEquals("Pending", task.getStatus());
		
	}

}
