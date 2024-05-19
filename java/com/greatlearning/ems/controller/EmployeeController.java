package com.greatlearning.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greatlearning.ems.entity.Employee;
import com.greatlearning.ems.service.EmployeeService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

//@RequestMapping("/employees")
@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping("/employees/list")
	public String listAllEmployees(Model model){
		
		List<Employee> employees = employeeService.list();
		
		model.addAttribute("employees", employees);
		
		return "list-employees";
	}
	
	
	@RequestMapping("/employees/showEmployeeForm_Save")
	public String saveEmployee_Step1(Model theModel) {

	    // create model attribute to bind form data
	    Employee employee = new Employee();

	    theModel.addAttribute("employee", employee);

	    return "employee-form";
	}    

	@PostMapping("/employees/save")
	public String saveEmployee(
	    @ModelAttribute("employee") Employee employee) {
	    
	    // save the employee
	    employeeService.save(employee);
	    
	    // use a redirect to prevent duplicate submissions
	    return "redirect:/employees/list";
	}

	@RequestMapping("/employees/showEmployeeForm_Update")
	public String updateEmployee_Step1(
	    @RequestParam("employeeId") Long employeeId,
	    Model theModel) {

	    // get the employee from the service
	    Employee employee = employeeService.findById(employeeId);

	    // set Employee as a model attribute to pre-populate the form
	    theModel.addAttribute("employee", employee);

	    // send over to our form
	    return "employee-form";   
	}    
	
	
	@RequestMapping("/employees/delete")
	public String delete(
	    @RequestParam("employeeId") Long employeeId) {

	    // delete the Employee
	    employeeService.deleteById(employeeId);

	    // redirect to Employees-Listing page
	    return "redirect:/employees/list";
	}
	
}