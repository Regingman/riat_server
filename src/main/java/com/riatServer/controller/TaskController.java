package com.riatServer.controller;

import com.riatServer.domain.Task;
import com.riatServer.repo.TasksRepo;
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
@RequestMapping("task")
public class TaskController {
    private final TasksRepo taskRepo;

    @Autowired
    public TaskController(TasksRepo tasksRepo)
    {
        this.taskRepo = tasksRepo;
    }

    @ApiOperation(value = "Получения списка всех отделов")
    @GetMapping
    public ResponseEntity<List<Task>> List(){
        List<Task> tasks = taskRepo.findAll();
        if(tasks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @ApiOperation(value = "Создание отдела")
    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task  task){
        if(task == null){
            return   new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        taskRepo.save(task);
        return  new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Обновление отдела")
    @PutMapping("{id}")
    public ResponseEntity<Task> update(
            @PathVariable("id") Long taskId,
            @RequestBody Task task
    )
    {
        Task taskFromDb = taskRepo.findById(taskId).orElse(null);
        if(taskFromDb == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(task, taskFromDb, "id");
        taskRepo.save(taskFromDb);
        return new ResponseEntity<>(taskFromDb, HttpStatus.OK);
    }

    @ApiOperation(value = "Удаление отдела")
    @DeleteMapping("{id}")
    public  ResponseEntity<Task> delete(@PathVariable("id") Long taskId){
        Task task = taskRepo.findById(taskId).orElse(null);
        if(task == null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        taskRepo.delete(task);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
