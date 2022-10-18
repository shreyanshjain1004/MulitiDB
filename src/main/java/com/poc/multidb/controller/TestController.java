package com.poc.multidb.controller;

import com.poc.multidb.first.dao.EmployeeDao;
import com.poc.multidb.first.entity.Employee;
import com.poc.multidb.second.dao.ManagerDao;
import com.poc.multidb.second.entity.ManagerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TestController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    ManagerDao managerDao;

    @RequestMapping(value="/employeeList", method=RequestMethod.GET)
    public List<Employee> getFirstDatabaseData() {
        List<Employee> list = employeeDao.findAll();
        return list;
    }

    @RequestMapping(value="/managerList", method=RequestMethod.GET)
    public List<ManagerEntity> getSecondDatabaseData() {
        List<ManagerEntity> list = managerDao.findAll();
        return list;
    }
}
