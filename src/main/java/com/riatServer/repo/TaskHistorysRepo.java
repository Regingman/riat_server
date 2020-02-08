package com.riatServer.repo;

import com.riatServer.domain.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskHistorysRepo extends JpaRepository<TaskHistory, Long> {
}
