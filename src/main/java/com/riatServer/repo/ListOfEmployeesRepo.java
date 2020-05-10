package com.riatServer.repo;

import com.riatServer.domain.ListOfEmployees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListOfEmployeesRepo extends JpaRepository<ListOfEmployees, Long> {
    @Query("select u from ListOfEmployees u where u.userId = ?1 and u.active = ?2")
    List<ListOfEmployees> listOfAllActiveTaskByEmployee(Long userId, boolean active);


    @Query("select u from ListOfEmployees u where u.userId = ?1 and u.active = ?2 and u.taskId = ?3")
    ListOfEmployees activeTask(Long userId, boolean active, Long taskId);

    @Query("select u from ListOfEmployees u where u.taskId = ?1 and u.userId = ?2")
    ListOfEmployees changeTaskStatus(Long taskId, Long UserId);

    @Query("select u from ListOfEmployees u where u.userId = ?1")
    List<ListOfEmployees> statistic(Long UserId);



}
