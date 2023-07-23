package com.example.TaskManagementSystem.ControllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;


import com.example.TaskManagementSystem.controller.TaskController;
import com.example.TaskManagementSystem.model.Task;
import com.example.TaskManagementSystem.service.TaskService;


@WebMvcTest(TaskController.class)
public class TaskControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TaskService taskService;
	
	@Test
	public void testViewAllTask() throws Exception{
		// Mock the service response
		Task task1 = new Task();
		Task task2 = new Task();
		
		when(taskService.getAllTask()).thenReturn(List.of(task1, task2));
		
		// Perform the GET request and validate the response
		mockMvc.perform(get("/viewTaskList"))
        .andExpect(status().isOk())
        .andExpect(view().name("ViewTaskList"))
        .andExpect(model().attributeExists("list"))
        .andExpect(model().attributeExists("message"));
	}
	
	@Test
    public void testUpdateTaskStatus_ValidId_Success() throws Exception {
        // Mock the service response
        when(taskService.updateStatus(1L)).thenReturn(true);

        // Perform the GET request and validate the redirected response
        mockMvc.perform(get("/updateTaskStatus/{id}", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("message"))
                .andExpect(redirectedUrl("/viewTaskList?message=Update+Success"));
    }

    @Test
    public void testUpdateTaskStatus_InvalidId_Failure() throws Exception {
        // Mock the service response
        when(taskService.updateStatus(1L)).thenReturn(false);

        // Perform the GET request and validate the redirected response
        mockMvc.perform(get("/updateTaskStatus/{id}", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("message"))
                .andExpect(redirectedUrl("/viewTaskList?message=Update+Failure"));
    }

    @Test
    public void testAddTask_GetRequest_Success() throws Exception {
        // Perform the GET request and validate the response
        mockMvc.perform(get("/addTask"))
                .andExpect(status().isOk())
                .andExpect(view().name("AddTask"))
                .andExpect(model().attributeExists("task"));
    }

    @Test
    public void testSaveTask_PostRequest_Success() throws Exception {
        // Mock the service response
        when(taskService.saveOrUpdateTask(any(Task.class))).thenReturn(true);

        // Perform the POST request and validate the redirected response
        mockMvc.perform(post("/saveTask").flashAttr("task", new Task()))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("message"))
                .andExpect(redirectedUrl("/viewTaskList?message=Save+Success"));
    }

    @Test
    public void testSaveTask_PostRequest_Failure() throws Exception {
        // Mock the service response
        when(taskService.saveOrUpdateTask(any(Task.class))).thenReturn(false);

        // Perform the POST request and validate the redirected response
        mockMvc.perform(post("/saveTask").flashAttr("task", new Task()))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("message"))
                .andExpect(redirectedUrl("/addTask"));
    }

    @Test
    public void testEditTask_GetRequest_ValidId_Success() throws Exception {
        // Mock the service response
        Task task = new Task();
        task.setId(1L);
        when(taskService.getTaskById(1L)).thenReturn(task);

        // Perform the GET request and validate the response
        mockMvc.perform(get("/editTask/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("EditTask"))
                .andExpect(model().attributeExists("task"))
                .andExpect(model().attribute("task", task));
    }

    @Test
    public void testEditTask_GetRequest_InvalidId_Redirect() throws Exception {
        // Mock the service response
        when(taskService.getTaskById(1L)).thenReturn(null);

        // Perform the GET request and validate the redirected response
        mockMvc.perform(get("/editTask/{id}", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("message"))
                .andExpect(redirectedUrl("/viewTaskList"));
    }

    @Test
    public void testEditSaveTask_PostRequest_ValidData_Success() throws Exception {
        // Mock the service response
        when(taskService.saveOrUpdateTask(any(Task.class))).thenReturn(true);

        // Perform the POST request and validate the redirected response
        mockMvc.perform(post("/editSaveTask").flashAttr("task", new Task()))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("message"))
                .andExpect(redirectedUrl("/viewTaskList?message=Edit+Success"));
    }

    @Test
    public void testEditSaveTask_PostRequest_InvalidData_Failure() throws Exception {
        // Mock the service response
        when(taskService.saveOrUpdateTask(any(Task.class))).thenReturn(false);

        // Perform the POST request and validate the redirected response
        mockMvc.perform(post("/editSaveTask").flashAttr("task", new Task()))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("message"))
                .andExpect(redirectedUrl("/editTask/-1"));
    }

    @Test
    public void testDeleteTask_ValidId_Success() throws Exception {
        // Mock the service response
        when(taskService.deleteTask(1L)).thenReturn(true);

        // Perform the GET request and validate the redirected response
        mockMvc.perform(get("/deleteTask/{id}", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("message"))
                .andExpect(redirectedUrl("/viewTaskList?message=Delete+Success"));
    }

    @Test
    public void testDeleteTask_InvalidId_Redirect() throws Exception {
        // Mock the service response
        when(taskService.deleteTask(1L)).thenReturn(false);

        // Perform the GET request and validate the redirected response
        mockMvc.perform(get("/deleteTask/{id}", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("message"))
                .andExpect(redirectedUrl("/viewTaskList"));
    }  
}
