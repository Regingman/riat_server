package com.riatServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.riatServer.domain.Department;

public interface DepartmentsRepo extends JpaRepository<Department, Long> {
}
