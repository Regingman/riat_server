package com.riatServer.service.Impl;

import com.riatServer.domain.*;
import com.riatServer.domain.Task;
import com.riatServer.dto.EmployeeTaskDto;
import com.riatServer.exception.ServiceException;
import com.riatServer.repo.*;
import com.riatServer.service.EntityService;
import com.riatServer.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService, EntityService<Task, Long> {
    @Autowired
    TasksRepo taskRepo;
    @Autowired
    ListOfTasksRepo listOfTasksRepo;
    @Autowired
    ListOfEmployeesRepo listOfEmployeesRepo;
    @Autowired
    PeriodicTasksRepo periodicTasksRepo;
    @Autowired
    MarksRepo marksRepo;
    @Autowired
    TaskStatusRepo taskStatusRepo;

    @Override
    public List<Task> getAll() {
        return  taskRepo.findAll();
    }

    @Override
    public Task getById(Long id) {
        return taskRepo.findById(id).orElse(null);
    }

    @Override
    public Task save(Task task) {
        task.setUpdateDate(LocalDateTime.now());
        return taskRepo.save(task);
    }

    @Override
    public Task create(Task task) {
        task.setCreateDate(LocalDateTime.now());
        task.setUpdateDate(LocalDateTime.now());
        return taskRepo.save(task);
    }

    @Override
    public void delete(Long id) throws IOException, ServiceException {
        Task task = getById(id);
        taskRepo.deleteById(id);
    }

    @Override
    public void assignTaskToEmployee(User user, Long taskId) {

    }

    @Override
    public void assignTaskToDepartment(Department department, Long taskId) {

    }

    @Override
    public void changeTaskProgress(Long taskId, Long taskStatusId, Long userId) {

        ListOfEmployees listOfEmployees = listOfEmployeesRepo.changeTaskStatus(taskId, userId);
        TaskStatus taskStatus = taskStatusRepo.findById(taskStatusId).orElse(null);
        listOfEmployees.setUpdateDate(LocalDateTime.now());
        listOfEmployees.setTaskStatusId(taskStatusId);
        listOfEmployees.setTaskStatus_id(taskStatus);
        if(taskStatusId==2){
            listOfEmployees.setActive(false);
        }
        listOfEmployeesRepo.save(listOfEmployees);
    }


    @Override
    public List<EmployeeTaskDto> listOfAllActiveTaskByEmployee(Long userId)
    {
        return listOfTaskToEmployee(userId, true);
    }

    @Override
    public List<Task> listOfAllActiveTaskByDepartment(Long userId) {
        return null;
    }

    @Override
    public List<EmployeeTaskDto> listOfAllInactiveTaskByEmployee(Long userId) {

        return listOfTaskToEmployee(userId, false);
    }

    @Override
    public List<Task> listOfAllInactiveTaskByDepartment(Long userId) {
        return null;
    }

    @Override
    public List<Task> getAllSubTaskToTask(Long taskId) {
        List<Long> subtasksId =  listOfTasksRepo.allSubTaskToTask(taskId);
        List<Task> tasks = new ArrayList<Task>();
        for(int i =0;i<subtasksId.size();i++){
            tasks.add(taskRepo.findById(subtasksId.get(i)).orElse(null));
        }
        return tasks;
    }

    @Override
    public void addSubTaskToTask(Task task, Long taskId) {
        create(task);
        Task topTask = getById(taskId);
        Task subTask = getById(task.getId());
        ListOfTask listOfTask = new ListOfTask();
        listOfTask.setSubtaskId(task.getId());
        listOfTask.setTopId(taskId);
        listOfTask.setTopTask(topTask);
        listOfTask.setSubtask(subTask);
        listOfTask.setCreateDate(LocalDateTime.now());
        listOfTasksRepo.save(listOfTask);
    }

    @Override
    public List<Task> getAllTemplateTask() {
        return taskRepo.allTemplateTask(true);
    }

    @Override
    public void setTemplateTask(Long taskId) {
        Task task = getById(taskId);
        task.setTemplateTask(true);
        task.setUpdateDate(LocalDateTime.now());
        taskRepo.save(task);
    }

    @Override
    public void setPeriodicTask(Long markId, Long taskId) {
        Task task = getById(taskId);
        Mark mark = marksRepo.findById(markId).orElse(null);
        PeriodicTask periodicTask = new PeriodicTask();
        periodicTask.setCreateDate(LocalDateTime.now());
        periodicTask.setTaskId(taskId);
        periodicTask.setActive(true);
        periodicTask.setMarkId(markId);
        periodicTask.setMark(mark);
        periodicTask.setTask(task);
        periodicTasksRepo.save(periodicTask);
    }

    public List<EmployeeTaskDto> listOfTaskToEmployee(Long userId, boolean active)
    {
        List<ListOfEmployees> listOfEmployees = listOfEmployeesRepo.listOfAllActiveTaskByEmployee(userId, active);
        List<Task> tasks = new ArrayList<Task>();
        ListOfEmployees tempListOfEmpl = new ListOfEmployees();
        for(int i =0;i<listOfEmployees.size();i++){
            tempListOfEmpl = listOfEmployees.get(i);
            tasks.add(taskRepo.findById(tempListOfEmpl.getTaskId()).orElse(null));
        }
        List <EmployeeTaskDto> employeeTaskDto = new ArrayList<>();
        for(int i=0;i<listOfEmployees.size();i++){
            //System.out.println();
            employeeTaskDto.add(EmployeeTaskDto.fromUser(listOfEmployees.get(i),tasks.get(i)));
        }
        return employeeTaskDto;
    }
}
