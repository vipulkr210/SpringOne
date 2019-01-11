/**
 * 
 */
package com.example.DemoWebRest.employee;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author vipulk2
 *
 */
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	 
	 @Autowired
	    public void setClientRepository(EmployeeRepository employeeRepository){
	        this.employeeRepository=employeeRepository;
	    }   
	@Override
	public Employee getEmployeeById(UUID id) {
		// TODO Auto-generated method stub
		return employeeRepository.getOne(id);
	}

	@Override
	public void saveEmployee(Employee employee) {
		
		employeeRepository.save(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
		
	}

	@Override
	public void deleteEmployee(UUID id) {
		employeeRepository.deleteById(id);
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

}
