package com.accolite.miniau.CRUDEmployeeRest.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.accolite.miniau.CRUDEmployeeRest.pojo.Employee;
import com.accolite.miniau.CRUDEmployeeRest.service.EmployeeServiceImpl;
import javax.ws.rs.PathParam;

@Path("employee")
public class EmployeeResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getAllEmployees() throws SQLException
	{
		return new EmployeeServiceImpl().getAllEmployees();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addEmployee(Employee employee) throws SQLException
	{
		new EmployeeServiceImpl().addEmployee(employee);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmpById(@javax.ws.rs.PathParam("id")int id) throws SQLException
	{
		return new EmployeeServiceImpl().getEmployeeById(id);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(@PathParam("id") int id,Employee employee) throws SQLException
	{
		new EmployeeServiceImpl().updateEmployee(id, employee);
	}
	

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") int id) throws SQLException
	{
		new EmployeeServiceImpl().deleteEmployee(id);
	}
	
}