package com.riatServer.repo;

import com.riatServer.domain.ListOfEmployees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListOfEmployeesRepo extends JpaRepository<ListOfEmployees, Long> {
}
