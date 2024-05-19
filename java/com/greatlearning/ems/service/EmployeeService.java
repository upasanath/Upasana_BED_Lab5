package com.greatlearning.ems.service;

import java.util.List;

import com.greatlearning.ems.entity.Employee;

public interface EmployeeService {

	List<Employee> list();
	
	public void save(Employee employee);

	public Employee findById(Long employeeID);	

	public void deleteById(Long employeeId);
}