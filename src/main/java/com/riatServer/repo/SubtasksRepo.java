package com.riatServer.repo;

import com.riatServer.domain.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtasksRepo extends JpaRepository<Subtask, Long> {
}
