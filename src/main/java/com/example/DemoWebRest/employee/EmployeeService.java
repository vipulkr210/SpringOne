package com.example.DemoWebRest.employee;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
@Service
public interface EmployeeService
{
    public abstract Employee getEmployeeById(UUID id);
    public abstract void saveEmployee(Employee employee);
    public abstract void updateEmployee(Employee employee);
    public abstract void deleteEmployee(UUID id);
    public abstract List<Employee> getAllEmployees();
}