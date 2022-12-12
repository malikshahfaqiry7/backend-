package com.employee.controller;

import com.employee.exception.MyCustomException;
import com.employee.service.EmployeeService;
import com.employee.vo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    @Qualifier("jpaes")
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @RequestMapping("/{id}")
    public Employee getAllEmployee(@PathVariable("id") int eid) {
        return employeeService.getEmployeeByEid(eid);
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> addEmployee(@RequestBody Employee emp) {
        boolean flag = employeeService.addEmployee(emp);
        Map<String,String> m =new HashMap<>();
        m.put("msg",flag ? "Employee Added" : "Employee Not Added");
        return m;
    }

    @RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> updateEmployee(@RequestBody Employee emp) {
        System.out.println("Received");
        boolean flag = employeeService.updateEmployee(emp);
        Map<String,String> m =new HashMap<>();
        m.put("message",flag ? "Employee Updated" : "Employee Not Updated");
        return m;
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "/{id}")
    public Map<String,String> deleteEmployee(@PathVariable("id") int eid) {
        boolean flag = employeeService.deleteEmployee(eid);
        Map<String,String> m =new HashMap<>();
        m.put("message",flag ? "Employee Deleted" : "Employee Not Deleted");
        return  m;
    }
    @RequestMapping("/path/say/{number}")
    public String sayHello(@PathVariable("number") int number){
        if(number==1) {
            String s=null;
            s.toCharArray();
        }else if (number==2){
            throw new MyCustomException("This is for custom exception testing");
        }
        return "SUCCESS";
    }
}
