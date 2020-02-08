package com.riatServer.repo;

import com.riatServer.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepo extends JpaRepository<Task, Long> {
}
