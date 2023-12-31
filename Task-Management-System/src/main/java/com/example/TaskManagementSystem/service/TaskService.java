package com.example.TaskManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaskManagementSystem.model.Task;
import com.example.TaskManagementSystem.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	TaskRepository repository;
	
	public List<Task> getAllTask(){
		ArrayList<Task> taskList = new ArrayList<>();
		
		repository.findAll().forEach(task -> taskList.add(task));
		
		return taskList;
	}
	
	public Task getTaskById(Long id){
		return repository.findById(id).get();
	}
	
	public boolean updateStatus(Long id) {
		Task task = getTaskById(id);
		task.setStatus("Completed");
		
		
		return saveOrUpdateTask(task);
	}
	
	public boolean saveOrUpdateTask(Task task) {
		Task updatedObj = repository.save(task);
		
		if(getTaskById(updatedObj.getId()) != null) {
			return true;
		}
		
		return false;
	}
	
	public boolean deleteTask(Long id){
		repository.deleteById(id);
		
		if(repository.findById(id).isEmpty()) {
			return true;
		}
		
		return false;
	}
	
}
