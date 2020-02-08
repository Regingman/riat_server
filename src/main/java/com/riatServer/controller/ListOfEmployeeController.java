package com.riatServer.controller;

import com.riatServer.domain.ListOfEmployees;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.riatServer.repo.ListOfEmployeesRepo;

import java.util.List;

@Api(description = "Операции по взаимодействию с отделами")
@RestController
@RequestMapping("listOfEmployee")
public class ListOfEmployeeController {
    private final ListOfEmployeesRepo listOfEmployeeRepo;

    @Autowired
    public ListOfEmployeeController(ListOfEmployeesRepo listOfEmployeesRepo)
    {
        this.listOfEmployeeRepo = listOfEmployeesRepo;
    }

    @ApiOperation(value = "Получения списка всех отделов")
    @GetMapping
    public ResponseEntity<List<ListOfEmployees>> List(){
        List<ListOfEmployees> listOfEmployees = listOfEmployeeRepo.findAll();
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
        listOfEmployeeRepo.save(listOfEmployee);
        return  new ResponseEntity<>(listOfEmployee, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Обновление отдела")
    @PutMapping("{id}")
    public ResponseEntity<ListOfEmployees> update(
            @PathVariable("id") Long listOfEmployeeId,
            @RequestBody ListOfEmployees listOfEmployee
    )
    {
        ListOfEmployees listOfEmployeeFromDb = listOfEmployeeRepo.findById(listOfEmployeeId).orElse(null);
        if(listOfEmployeeFromDb == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(listOfEmployee, listOfEmployeeFromDb, "id");
        listOfEmployeeRepo.save(listOfEmployeeFromDb);
        return new ResponseEntity<>(listOfEmployeeFromDb, HttpStatus.OK);
    }

    @ApiOperation(value = "Удаление отдела")
    @DeleteMapping("{id}")
    public  ResponseEntity<ListOfEmployees> delete(@PathVariable("id") Long listOfEmployeeId){
        ListOfEmployees listOfEmployee = listOfEmployeeRepo.findById(listOfEmployeeId).orElse(null);
        if(listOfEmployee == null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        listOfEmployeeRepo.delete(listOfEmployee);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
