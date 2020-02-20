package com.riatServer.controller;

import com.riatServer.domain.Task;
import com.riatServer.exception.ServiceException;
import com.riatServer.repo.TasksRepo;
import com.riatServer.service.Impl.TaskServiceImpl;
import com.riatServer.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Api(description = "Операции по взаимодействию с задачами")
@RestController
@RequestMapping("task")
@ComponentScan(value = "com.riatServer.service.impl")
public class TaskController {
    @Autowired
    TaskServiceImpl taskService;


    @ApiOperation(value = "Получения списка всех задач")
    @GetMapping
    public ResponseEntity<List<Task>> List(){
        List<Task> tasks = taskService.getAll();
        if(tasks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @ApiOperation(value = "Получения списка всех подзадач одной задачи")
    @GetMapping("subTask/{id}")
    public ResponseEntity<List<Task>> allSubTaskToTask(@PathVariable("id") Long taskId){
        List<Task> tasks = taskService.getAllSubTaskToTask(taskId);
        if(tasks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }



    @ApiOperation(value = "Создание подзадачи для задачи")
    @PostMapping("subTask/{id}")
    public ResponseEntity<Task> createSubTask(@PathVariable("id") Long taskId, @RequestBody Task task){
        taskService.addSubTaskToTask(task,taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Получения списка всех активных задач сотрудника")
    @GetMapping("employee/{id}/active")
    public ResponseEntity<List<Task>> listOfAllActiveTaskByEmployee(@PathVariable("id") Long userId){
        List<Task> tasks = taskService.listOfAllActiveTaskByEmployee(userId);
        if(tasks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @ApiOperation(value = "Получения списка всех неактивных задач сотрудника")
    @GetMapping("employee/{id}/inactive")
    public ResponseEntity<List<Task>> listOfAllInactiveTaskByEmployee(@PathVariable("id") Long userId){
        List<Task> tasks = taskService.listOfAllInactiveTaskByEmployee(userId);
        if(tasks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @ApiOperation(value = "Сохранение шаблонной задачи")
    @PostMapping("template/{id}")
    public ResponseEntity<Task> allTemplateTask(@PathVariable("id") Long taskId){
        taskService.setTemplateTask(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Сохранение шаблонной задачи")
    @PostMapping("period/{markId}/{taskId}")
    public ResponseEntity<Task> setPeriodicTask(@PathVariable("markId") Long markId, @PathVariable("taskId") Long taskId){
        taskService.setPeriodicTask(markId, taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Сохранение шаблонной задачи")
    @PostMapping("status/{taskId}/{userId}/{taskStatusId}")
    public ResponseEntity<Task> setTaskStatus(
            @PathVariable("taskId") Long taskId,
            @PathVariable("userId") Long userId,
            @PathVariable("taskStatusId") Long taskStatusId)
    {
        taskService.changeTaskProgress(taskId, taskStatusId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Создание задачи")
    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task  task){
        if(task == null){
            return   new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        taskService.create(task);
        return  new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Обновление задачи")
    @PutMapping("{id}")
    public ResponseEntity<Task> update(
            @PathVariable("id") Long taskId,
            @RequestBody Task task
    )
    {
        Task taskFromDb = taskService.getById(taskId);
        if(taskFromDb == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(task, taskFromDb, "id");
        taskService.save(taskFromDb);
        return new ResponseEntity<>(taskFromDb, HttpStatus.OK);
    }

    @ApiOperation(value = "Удаление задачи")
    @DeleteMapping("{id}")
    public  ResponseEntity<Task> delete(@PathVariable("id") Long taskId) throws IOException, ServiceException {
        taskService.delete(taskId);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
