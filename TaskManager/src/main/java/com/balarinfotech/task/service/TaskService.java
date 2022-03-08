package com.balarinfotech.task.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.balarinfotech.task.model.TblTask;
import com.balarinfotech.task.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	//get all the task
	@Transactional(readOnly = true)
	public List<TblTask> getTasks(){
		return this.taskRepository.getAllTaskByDueDateDescOrder();
	}

	//add task
	public TblTask saveTask(TblTask tblTask) {
		return this.taskRepository.saveAndFlush(tblTask);
	}
	
	@Transactional(readOnly = true)
    public boolean isExistById(Long id) {
	   return this.taskRepository.existsById(id);	
	}
	
	@Transactional(readOnly = true)
	public Optional<TblTask> getTaskById(Long id) {
		return this.taskRepository.findById(id);
	}

	public void deleteTask(Long id) {
	     this.taskRepository.deleteById(id);
	}
	
	public List<?> getParcentageGroupByType(){
        return this.taskRepository.getParcentageGroupByType();
	}
}
