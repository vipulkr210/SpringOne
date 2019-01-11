package com.example.DemoWebRest.employee;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeRepository empRepository;

	@GetMapping("/Employees")
	@CrossOrigin(origins = "http://localhost:3000")

	public List<Employee> getAllEmployees() {
		return empRepository.findAll();
	}
	boolean b =false;
	@PostMapping("/Employees")
	@CrossOrigin(origins = "http://localhost:3000")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		// Employee Employee1= new Employee(1, "Vipul2", "Kr", "vipul@gmail.com");List<Employee> l = getAllEmployees();
		Stream<Employee> l = getAllEmployees().stream();
		Employee ee = new Employee();
		boolean result =  l.anyMatch(e ->e.getEmail().equals(employee.getEmail()));
		/*{
			System.out.println(e.getEmail() +"   :   "+employee.getEmail());
			if(e.getEmail().equals(employee.getEmail())) {
				b = true;
				ee.setCreatedAt(e.getCreatedAt());
				ee.setEmail(e.getEmail());
				ee.setFirstName(e.getFirstName());
				ee.setLastName(e.getLastName());
				ee.setTimestamp(e.getTimestamp());
			}
		});*/
		if(!result) {
		 return empRepository.save(employee);
		}
		else  return null;
	}

	@GetMapping("/Employees/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public Employee getEmployeeById(@PathVariable(value = "id") UUID EmployeeId) {
		return empRepository.findById(EmployeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", EmployeeId));
	}

	@PutMapping("/Employees/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public Employee updateEmployee(@PathVariable(value = "id") UUID EmployeeId,
			@Valid @RequestBody Employee EmployeeDetails) {

		Employee Employee = empRepository.findById(EmployeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", EmployeeId));

		Employee.setEmail(EmployeeDetails.getEmail());
		Employee.setFirstName(EmployeeDetails.getFirstName());
		Employee.setLastName(EmployeeDetails.getLastName());

		Employee updatedEmployee = empRepository.save(Employee);
		return updatedEmployee;
	}

	@DeleteMapping("/Employees/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<String> deleteEmployee(@PathVariable(value = "id") UUID EmployeeId) {
		Employee Employee = empRepository.findById(EmployeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", EmployeeId));
		try {
		empRepository.delete(Employee);

		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
