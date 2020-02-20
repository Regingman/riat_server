package com.riatServer.repo;

import com.riatServer.domain.ListOfEmployees;
import com.riatServer.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TasksRepo extends JpaRepository<Task, Long> {
    @Query("select u from Task u where u.templateTask = ?1")
    List<Task> allTemplateTask(boolean active);

}
