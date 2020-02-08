package com.riatServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.riatServer.domain.TaskStatus;

public interface TaskStatusRepo extends JpaRepository<TaskStatus, Long> {
}
