package com.riatServer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.riatServer.domain.ListOfEmployees;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeTaskDto {
    Long id;
    boolean active;
    Long taskId;
    Long userId;
    Long taskStatusId;
    Long ownerId;
    LocalDateTime createDate;
    LocalDateTime updateDate;

    public ListOfEmployees toUser(){
        ListOfEmployees user = new ListOfEmployees();
        user.setId(id);
        user.setTaskId(taskId);
        user.setUserId(userId);
        user.setTaskStatusId(taskStatusId);
        user.setOwnerId(ownerId);
        user.setCreateDate(createDate);
        user.setUpdateDate(updateDate);
        return user;
    }

    public static EmployeeTaskDto fromUser(ListOfEmployees task) {
        EmployeeTaskDto employeeTaskDto = new EmployeeTaskDto();
        employeeTaskDto.setActive(task.isActive());
        employeeTaskDto.setId(task.getId());
        employeeTaskDto.setCreateDate(task.getCreateDate());
        employeeTaskDto.setUpdateDate(task.getUpdateDate());
        employeeTaskDto.setTaskId(task.getTaskId());
        employeeTaskDto.setUserId(task.getUserId());
        employeeTaskDto.setTaskStatusId(task.getTaskStatusId());
        employeeTaskDto.setOwnerId(task.getOwnerId());
        return employeeTaskDto;
    }

}
