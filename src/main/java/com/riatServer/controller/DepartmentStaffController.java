package com.riatServer.controller;

import com.riatServer.domain.DepartmentStaff;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.riatServer.repo.DepartmentStaffsRepo;

import java.util.List;

@Api(description = "Операции по взаимодействию с составом отдела")
@RestController
@RequestMapping("departmentStaff")
public class DepartmentStaffController {
    private final DepartmentStaffsRepo departmentStaffRepo;

    @Autowired
    public DepartmentStaffController(DepartmentStaffsRepo departmentStaffsRepo)
    {
        this.departmentStaffRepo = departmentStaffsRepo;
    }

    @ApiOperation(value = "Получения списка всех составов отделов")
    @GetMapping
    public ResponseEntity<List<DepartmentStaff>> List(){
        List<DepartmentStaff> departmentStaffs = departmentStaffRepo.findAll();
        if(departmentStaffs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(departmentStaffs, HttpStatus.OK);
    }

    @ApiOperation(value = "Создание состава отдела")
    @PostMapping
    public ResponseEntity<DepartmentStaff> create(@RequestBody DepartmentStaff  departmentStaff){
        if(departmentStaff == null){
            return   new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        departmentStaffRepo.save(departmentStaff);
        return  new ResponseEntity<>(departmentStaff, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Обновление состава отдела")
    @PutMapping("{id}")
    public ResponseEntity<DepartmentStaff> update(
            @PathVariable("id") Long departmentStaffId,
            @RequestBody DepartmentStaff departmentStaff
    )
    {
        DepartmentStaff departmentStaffFromDb = departmentStaffRepo.findById(departmentStaffId).orElse(null);
        if(departmentStaffFromDb == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(departmentStaff, departmentStaffFromDb, "id");
        departmentStaffRepo.save(departmentStaffFromDb);
        return new ResponseEntity<>(departmentStaffFromDb, HttpStatus.OK);
    }

    @ApiOperation(value = "Удаление состава отдела")
    @DeleteMapping("{id}")
    public  ResponseEntity<DepartmentStaff> delete(@PathVariable("id") Long departmentStaffId){
        DepartmentStaff departmentStaff = departmentStaffRepo.findById(departmentStaffId).orElse(null);
        if(departmentStaff == null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        departmentStaffRepo.delete(departmentStaff);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
