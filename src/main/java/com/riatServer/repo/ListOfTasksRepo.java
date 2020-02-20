package com.riatServer.repo;

import com.riatServer.domain.ListOfTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListOfTasksRepo extends JpaRepository<ListOfTask, Long> {
    @Query("select u.subtaskId from ListOfTask u where u.topId = ?1")
    List<Long> allSubTaskToTask(Long taskId);
}
