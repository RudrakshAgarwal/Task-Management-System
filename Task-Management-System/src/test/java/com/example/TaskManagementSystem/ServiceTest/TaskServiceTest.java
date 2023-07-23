package com.example.TaskManagementSystem.ServiceTest;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.TaskManagementSystem.model.Task;
import com.example.TaskManagementSystem.repository.TaskRepository;
import com.example.TaskManagementSystem.service.TaskService;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTask() {
        // Mock data for repository response
        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Task 1");
        task1.setDate(LocalDate.of(2023, 7, 1));
        task1.setStatus("Pending");

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task 2");
        task2.setDate(LocalDate.of(2023, 7, 2));
        task2.setStatus("InProgress");

        List<Task> mockTasks = new ArrayList<>();
        mockTasks.add(task1);
        mockTasks.add(task2);

        when(taskRepository.findAll()).thenReturn(mockTasks);

        // Perform the service method
        List<Task> resultTasks = taskService.getAllTask();

        // Assertions
        assertEquals(2, resultTasks.size());
        assertEquals(task1, resultTasks.get(0));
        assertEquals(task2, resultTasks.get(1));
    }

    @Test
    void testGetTaskById_ValidId() {
        // Mock data for repository response
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDate(LocalDate.of(2023, 7, 1));
        task.setStatus("Pending");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // Perform the service method
        Task resultTask = taskService.getTaskById(1L);

        // Assertions
        assertNotNull(resultTask);
        assertEquals(task, resultTask);
    }

    @Test
    void testGetTaskById_InvalidId() {
        // Mock data for repository response (Empty Optional)
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        // Perform the service method
        Task resultTask = taskService.getTaskById(1L);

        // Assertions
        assertNull(resultTask);
    }

    @Test
    void testUpdateStatus_ValidId() {
        // Mock data for repository response
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDate(LocalDate.of(2023, 7, 1));
        task.setStatus("Pending");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Perform the service method
        boolean updateResult = taskService.updateStatus(1L);

        // Assertions
        assertTrue(updateResult);
        assertEquals("Completed", task.getStatus());
    }

    @Test
    void testUpdateStatus_InvalidId() {
        // Mock data for repository response (Empty Optional)
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        // Perform the service method
        boolean updateResult = taskService.updateStatus(1L);

        // Assertions
        assertFalse(updateResult);
    }

    @Test
    void testSaveOrUpdateTask_Save() {
        // Mock data for repository response
        Task task = new Task();
        task.setTitle("New Task");
        task.setDate(LocalDate.of(2023, 7, 1));
        task.setStatus("Pending");

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Perform the service method
        boolean saveResult = taskService.saveOrUpdateTask(task);

        // Assertions
        assertTrue(saveResult);
        assertNotNull(task.getId());
    }

    @Test
    void testSaveOrUpdateTask_Update() {
        // Mock data for repository response
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDate(LocalDate.of(2023, 7, 1));
        task.setStatus("Pending");

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Perform the service method
        boolean updateResult = taskService.saveOrUpdateTask(task);

        // Assertions
        assertTrue(updateResult);
    }

    @Test
    void testDeleteTask_ValidId() {
        // Mock data for repository response
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");
        task.setDate(LocalDate.of(2023, 7, 1));
        task.setStatus("Pending");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // Perform the service method
        boolean deleteResult = taskService.deleteTask(1L);

        // Assertions
        assertTrue(deleteResult);
        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteTask_InvalidId() {
        // Mock data for repository response (Empty Optional)
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        // Perform the service method
        boolean deleteResult = taskService.deleteTask(1L);

        // Assertions
        assertFalse(deleteResult);
        verify(taskRepository, never()).deleteById(1L);
    }
}
