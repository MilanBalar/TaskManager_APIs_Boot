package com.balarinfotech.task.controllers;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balarinfotech.task.model.TblTask;
import com.balarinfotech.task.service.TaskService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	//For Get All Tasks
	@GetMapping("/tasks")
	public List<TblTask> getAllTasks(){
		return this.taskService.getTasks();
	}
	
	//Add New Task
	@PostMapping("/tasks")
	public TblTask addTask(@RequestBody TblTask tblTask) {
		return this.taskService.saveTask(tblTask);
	}
	
	//Update Task
	@PutMapping("/tasks/{id}")
	public ResponseEntity<?> updateTask(@RequestBody TblTask task,@PathVariable Long id) {
		if(this.taskService.isExistById(id)) {
			TblTask tblTask = this.taskService.getTaskById(id).orElseThrow(()->new EntityNotFoundException("Task not found"));
			tblTask.setTitle(task.getTitle());
			tblTask.setType(task.getType());
			tblTask.setDueDate(task.getDueDate());
			tblTask.setDescription(task.getDescription());
			this.taskService.saveTask(tblTask);
			return ResponseEntity.ok().body(tblTask);
		}else {
			HashMap<String, String> message=new HashMap<>();
			message.put("message", id+" task not found or matchd !!");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
	}
	
	//Delete Task
	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable Long id) {
		if(this.taskService.isExistById(id)) {
			this.taskService.deleteTask(id);
			HashMap<String, String> message=new HashMap<>();
			message.put("message", id+" task removed");
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}else {
			HashMap<String, String> message=new HashMap<>();
			message.put("message", id+" task not found or matchd !!");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
	}
	
	@GetMapping("/tasks/percentage")
	public List<?> getParcentageGroupByType(){
		return this.taskService.getParcentageGroupByType();
	}
	
}
