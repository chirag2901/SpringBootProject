package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bean.EmployeeBean;
import com.dao.EmployeeDao;

@RestController //Controller ni jagya rest controller 
public class EmployeeController {
	
//	@RequestMapping(value = 	"/home")
//	public void home() {
//		System.out.println("Home Called....");
//	}
//	@RequestMapping(value = "/home",method = RequestMethod.POST)
//	public String home() {
//		return "api called ..";	
//		}
//	
//	@PostMapping(value = "/addemployee")
//	public String addEmployee(String ename,int age) {
//		System.out.println("Name: "+ename);
//		System.out.println("Age: "+age);
//		return ename;
//	}
	@Autowired
	EmployeeDao employeeDao;
	
	@PostMapping(value = "/addemployee")
	public ResponseEntity<String> addEmployee(EmployeeBean employeeBean) {
			 employeeDao.addEmployee(employeeBean);
			 return new ResponseEntity<String>("Record Added",HttpStatus.CREATED);
	}
	@PostMapping(value = "/addemployee1",consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addEmployee1(@RequestBody EmployeeBean employeeBean) {
			 employeeDao.addEmployee(employeeBean);
			 return new ResponseEntity<String>("Record Added",HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/deleteemployee/{eid}")//http://localhost:8080/deleteemployee/55
	public ResponseEntity<String> deleteEmployee(@PathVariable("eid") int eId){
		int record = employeeDao.deleteEmployee(eId);
		if(record>0) {	
			return new ResponseEntity<String>("Data Deleted",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Data Not Deleted",HttpStatus.CONFLICT);
	}
	@GetMapping(value = "/getemployeebyid/{eid}")
	public ResponseEntity<EmployeeBean> getEmployeeById(@PathVariable ("eid")int eId){
		System.out.println("Controller called...called.");
		EmployeeBean employeeBean = employeeDao.getEmployeeById(eId);
		
		return new ResponseEntity<EmployeeBean>(employeeBean,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getusers")
	public ResponseEntity<List<EmployeeBean>> getAllUser(){
		System.out.println("In GetAllUsers Controller");
		List<EmployeeBean>  list=  employeeDao.getAllEmployee();
		return new ResponseEntity<List<EmployeeBean>>(list,HttpStatus.OK);
		
	}
}
