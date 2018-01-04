package com.accolite.miniau.CRUDEmployeeRest.service;

import java.sql.SQLException;
import java.util.List;

import com.accolite.miniau.CRUDEmployeeRest.pojo.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees() throws SQLException;
	public Employee getEmployeeById(int id) throws SQLException;
	public void addEmployee(Employee employee) throws SQLException;
	public void updateEmployee(int id,Employee employee) throws SQLException;
	public void deleteEmployee(int id) throws SQLException;
}
