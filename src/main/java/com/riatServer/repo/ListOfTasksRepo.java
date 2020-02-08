package com.riatServer.repo;

import com.riatServer.domain.ListOfTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListOfTasksRepo extends JpaRepository<ListOfTask, Long> {
}
