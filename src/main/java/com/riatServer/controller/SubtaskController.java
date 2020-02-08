package com.riatServer.controller;

import com.riatServer.domain.Subtask;
import com.riatServer.repo.SubtasksRepo;
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
@RequestMapping("subtask")
public class SubtaskController {
    private final SubtasksRepo subtaskRepo;

    @Autowired
    public SubtaskController(SubtasksRepo subtasksRepo)
    {
        this.subtaskRepo = subtasksRepo;
    }

    @ApiOperation(value = "Получения списка всех отделов")
    @GetMapping
    public ResponseEntity<List<Subtask>> List(){
        List<Subtask> subtasks = subtaskRepo.findAll();
        if(subtasks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subtasks, HttpStatus.OK);
    }

    @ApiOperation(value = "Создание отдела")
    @PostMapping
    public ResponseEntity<Subtask> create(@RequestBody Subtask  subtask){
        if(subtask == null){
            return   new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        subtaskRepo.save(subtask);
        return  new ResponseEntity<>(subtask, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Обновление отдела")
    @PutMapping("{id}")
    public ResponseEntity<Subtask> update(
            @PathVariable("id") Long subtaskId,
            @RequestBody Subtask subtask
    )
    {
        Subtask subtaskFromDb = subtaskRepo.findById(subtaskId).orElse(null);
        if(subtaskFromDb == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(subtask, subtaskFromDb, "id");
        subtaskRepo.save(subtaskFromDb);
        return new ResponseEntity<>(subtaskFromDb, HttpStatus.OK);
    }

    @ApiOperation(value = "Удаление отдела")
    @DeleteMapping("{id}")
    public  ResponseEntity<Subtask> delete(@PathVariable("id") Long subtaskId){
        Subtask subtask = subtaskRepo.findById(subtaskId).orElse(null);
        if(subtask == null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        subtaskRepo.delete(subtask);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
