package com.riatServer.repo;

import com.riatServer.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionsRepo extends JpaRepository<Position, Long> {
}
