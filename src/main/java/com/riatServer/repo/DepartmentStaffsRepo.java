package com.riatServer.repo;

import com.riatServer.domain.DepartmentStaff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentStaffsRepo extends JpaRepository<DepartmentStaff, Long> {
}
