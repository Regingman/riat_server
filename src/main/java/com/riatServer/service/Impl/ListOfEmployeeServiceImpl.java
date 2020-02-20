package com.riatServer.service.Impl;

import com.riatServer.domain.ListOfEmployees;
import com.riatServer.domain.Task;
import com.riatServer.domain.User;
import com.riatServer.exception.ServiceException;
import com.riatServer.repo.ListOfEmployeesRepo;
import com.riatServer.repo.TasksRepo;
import com.riatServer.repo.UsersRepo;
import com.riatServer.service.EntityService;
import com.riatServer.service.ListOfEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class ListOfEmployeeServiceImpl implements ListOfEmployeeService, EntityService<ListOfEmployees, Long> {
    @Autowired
    ListOfEmployeesRepo listOfEmployeesRepo;
    @Autowired
    TasksRepo tasksRepo;
    @Autowired
    UsersRepo usersRepo;

    @Override
    public List<ListOfEmployees> getAll() {
        return listOfEmployeesRepo.findAll();
    }

    @Override
    public ListOfEmployees getById(Long id) {
        return listOfEmployeesRepo.findById(id).orElse(null);
    }

    @Override
    public ListOfEmployees save(ListOfEmployees listOfEmployees) {
        listOfEmployees.setUpdateDate(LocalDateTime.now());
        return listOfEmployeesRepo.save(listOfEmployees);
    }

    @Override
    public ListOfEmployees create(ListOfEmployees listOfEmployees) {
        Task task = tasksRepo.findById(listOfEmployees.getTaskId()).orElse(null);
        User user = usersRepo.findById(listOfEmployees.getUserId()).orElse(null);
        listOfEmployees.setUser_id(user);
        listOfEmployees.setTask_id(task);
        listOfEmployees.setCreateDate(LocalDateTime.now());
        listOfEmployees.setUpdateDate(LocalDateTime.now());
        return listOfEmployeesRepo.save(listOfEmployees);
    }

    @Override
    public void delete(Long id) throws IOException, ServiceException {
        ListOfEmployees listOfEmployees = getById(id);
        listOfEmployeesRepo.delete(listOfEmployees);
    }
}
