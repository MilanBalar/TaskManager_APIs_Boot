package com.balarinfotech.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.balarinfotech.task.model.TblTask;

public interface TaskRepository extends JpaRepository<TblTask, Long>{
	
	@Query(value = "select * from tbl_task order by due_date desc",nativeQuery = true)
	public List<TblTask> getAllTaskByDueDateDescOrder();
	
	@Query(value = "select (count(*)/(select count(*) from youtube.tbl_task))*100, type from youtube.tbl_task group by type",nativeQuery = true)
	public List<Object> getParcentageGroupByType();

}
