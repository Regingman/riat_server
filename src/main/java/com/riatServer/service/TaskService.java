package com.riatServer.service;

import com.riatServer.domain.Department;
import com.riatServer.domain.ListOfTask;
import com.riatServer.domain.Task;
import com.riatServer.domain.User;
import com.riatServer.dto.EmployeeTaskDto;

import java.util.List;

public interface TaskService {
    void assignTaskToEmployee(User user, Long taskId);
    void assignTaskToDepartment(Department department, Long taskId);
    void changeTaskProgress(Long taskId, Long taskStatusId, Long userId);
    List<EmployeeTaskDto> listOfAllActiveTaskByEmployee(Long userId);
    List<Task> listOfAllActiveTaskByDepartment(Long userId);
    List<EmployeeTaskDto> listOfAllInactiveTaskByEmployee(Long userId);
    List<Task> listOfAllInactiveTaskByDepartment(Long userId);
    //void addFileToTask(File file);
    //void addCommentToTask(File file)
    List<Task> getAllSubTaskToTask(Long taskId);
    void addSubTaskToTask(Task task, Long taskId);
    List<Task> getAllTemplateTask();
    void setTemplateTask(Long taskId);
    void setPeriodicTask(Long markId, Long taskId);

}
