package com.riatServer.repo;

import com.riatServer.domain.DepartmentStaff;
import com.riatServer.domain.ListOfEmployees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentStaffsRepo extends JpaRepository<DepartmentStaff, Long> {
    @Query("select u from DepartmentStaff u where u.departmentId = ?1 and u.userId <> ?2")
    List<DepartmentStaff> userDepartmentList(Long departmentId, Long userId);

    @Query("select u.departmentId from DepartmentStaff u where  u.userId = ?1")
    long userDepartmentId(Long userId);

    @Query("select u.userId from DepartmentStaff u where u.departmentId = ?1")
    List<Long> userAllDep(Long departmentId);

    @Query("select u.departmentId from DepartmentStaff u where u.userId = ?1")
    Long userDep(Long departmentId);
}
