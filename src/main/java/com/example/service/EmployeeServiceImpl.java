package com.example.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> fetchAllEmployees() {
		List<Employee> allEmployees = employeeRepository.findAll();
		return allEmployees;
	}

	@Override
	public Employee getEmployeeById(Long id) {
	Optional<Employee> employee =	employeeRepository.findById(id);
	if (employee.isPresent()) {
		return employee.get();
	}	
	return null;
	}

	
	public Employee updateEmployeeById(Long id, Employee employee) {
		Optional<Employee> employee1 =employeeRepository.findById(id);
		if (employee1.isPresent()) {                           
		Employee originalEmployee=	employee1.get();
		if (Objects.nonNull(employee.getEmployeeName()) && !"".equalsIgnoreCase(employee.getEmployeeName())) {
			originalEmployee.setEmployeeName(employee.getEmployeeName());
		}    
		if (Objects.nonNull(employee.getEmployeeSalary()) && !"".equalsIgnoreCase(employee.getEmployeeSalary())) {
			originalEmployee.setEmployeeSalary(employee.getEmployeeSalary()); 
		}
		return employeeRepository.save(originalEmployee);
		}
		
		return null;
	}

	
	public String deleteDepartmentById(Long id) {
	if(employeeRepository.findById(id).isPresent() ) {
		employeeRepository.deleteById(id);
		return "Employee deleted successfully";
	}
		return "No such employee in the database";
	}

}
