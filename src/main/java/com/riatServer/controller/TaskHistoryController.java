package com.riatServer.controller;

import com.riatServer.domain.TaskHistory;
import com.riatServer.repo.TaskHistorysRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Операции по взаимодействию с отделами")
@RestController
@RequestMapping("taskHistory")
public class TaskHistoryController {
    private final TaskHistorysRepo departmentRepo;

    @Autowired
    public TaskHistoryController(TaskHistorysRepo departmentsRepo)
    {
        this.departmentRepo = departmentsRepo;
    }

    @ApiOperation(value = "Получения списка всех отделов")
    @GetMapping
    public ResponseEntity<List<TaskHistory>> List(){
        List<TaskHistory> departments = departmentRepo.findAll();
        if(departments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @ApiOperation(value = "Создание отдела")
    @PostMapping
    public ResponseEntity<TaskHistory> create(@RequestBody TaskHistory  department){
        if(department == null){
            return   new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        departmentRepo.save(department);
        return  new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Обновление отдела")
    @PutMapping("{id}")
    public ResponseEntity<TaskHistory> update(
            @PathVariable("id") Long departmentId,
            @RequestBody TaskHistory department
    )
    {
        TaskHistory departmentFromDb = departmentRepo.findById(departmentId).orElse(null);
        if(departmentFromDb == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(department, departmentFromDb, "id");
        departmentRepo.save(departmentFromDb);
        return new ResponseEntity<>(departmentFromDb, HttpStatus.OK);
    }

    @ApiOperation(value = "Удаление отдела")
    @DeleteMapping("{id}")
    public  ResponseEntity<TaskHistory> delete(@PathVariable("id") Long departmentId){
        TaskHistory department = departmentRepo.findById(departmentId).orElse(null);
        if(department == null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        departmentRepo.delete(department);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
