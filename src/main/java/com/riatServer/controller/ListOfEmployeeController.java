package com.riatServer.controller;

import com.riatServer.domain.ListOfEmployees;
import com.riatServer.dto.EmployeeTaskDto;
import com.riatServer.exception.ServiceException;
import com.riatServer.service.Impl.ListOfEmployeeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Api(description = "Операции по взаимодействию с отделами")
@RestController
@RequestMapping("listOfEmployee")
public class ListOfEmployeeController {
    private final ListOfEmployeeServiceImpl listOfEmployeeService;

    @Autowired
    public ListOfEmployeeController(ListOfEmployeeServiceImpl listOfEmployeeService)
    {
        this.listOfEmployeeService = listOfEmployeeService;
    }

    @ApiOperation(value = "Получения списка всех задач пользователя")
    @GetMapping("{userId}/{taskId}")
    public ResponseEntity<EmployeeTaskDto> List(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId){
        ListOfEmployees user = listOfEmployeeService.taskInfo(userId, true, taskId);
        System.out.println(user);
        EmployeeTaskDto task = EmployeeTaskDto.fromUser(user);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @ApiOperation(value = "Получения списка всех отделов")
    @GetMapping
    public ResponseEntity<List<ListOfEmployees>> List(){
        List<ListOfEmployees> listOfEmployees = listOfEmployeeService.getAll();
        if(listOfEmployees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listOfEmployees, HttpStatus.OK);
    }

    @ApiOperation(value = "Создание отдела")
    @PostMapping
    public ResponseEntity<ListOfEmployees> create(@RequestBody ListOfEmployees  listOfEmployee){
        if(listOfEmployee == null){
            return   new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        listOfEmployeeService.create(listOfEmployee);
        return  new ResponseEntity<>(listOfEmployee, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Обновление отдела")
    @PutMapping("{id}")
    public ResponseEntity<ListOfEmployees> update(
            @PathVariable("id") Long listOfEmployeeId,
            @RequestBody ListOfEmployees listOfEmployee
    )
    {
        ListOfEmployees listOfEmployeeFromDb = listOfEmployeeService.getById(listOfEmployeeId);
        if(listOfEmployeeFromDb == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(listOfEmployee, listOfEmployeeFromDb, "id");
        listOfEmployeeService.save(listOfEmployeeFromDb);
        return new ResponseEntity<>(listOfEmployeeFromDb, HttpStatus.OK);
    }

    @ApiOperation(value = "Удаление отдела")
    @DeleteMapping("{id}")
    public  ResponseEntity<ListOfEmployees> delete(@PathVariable("id") Long listOfEmployeeId) throws IOException, ServiceException {
        listOfEmployeeService.delete(listOfEmployeeId);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
