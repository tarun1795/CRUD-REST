package com.accolite.miniau.CRUDEmployeeRest.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.accolite.miniau.CRUDEmployeeRest.databaseConnection.DatabaseConnection;
import com.accolite.miniau.CRUDEmployeeRest.pojo.Employee;

public class EmployeeServiceImpl implements EmployeeService{

	PreparedStatement stmt;
	DatabaseConnection con;
	
	private void prepareStatment(String sql)
	{
		try {
			stmt = con.getCon().prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public EmployeeServiceImpl() {
		
		con = DatabaseConnection.getConnection();
	}
	
	@Override
	public List<Employee> getAllEmployees() throws SQLException {
		 String sql = "SELECT * FROM EMPLOYEE";
		 prepareStatment(sql);
		 ResultSet rs = stmt.executeQuery();
		 List<Employee> list = new ArrayList<>();
		 while(rs.next())
		 {
			 Employee employee = new Employee(rs.getInt("ID"),rs.getString("NAME"),rs.getString("EMAIL"),
						rs.getString("PHONE"),rs.getString("ADDRESS"),rs.getString("GENDER").charAt(0));
			 list.add(employee);
		 }
		 return list;
	}

	@Override
	public Employee getEmployeeById(int id) throws SQLException {
		String sql = "SELECT * FROM EMPLOYEE WHERE ID=?";
		prepareStatment(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		Employee employee = new Employee(rs.getInt("ID"),rs.getString("NAME"),rs.getString("EMAIL"),
				rs.getString("PHONE"),rs.getString("ADDRESS"),rs.getString("GENDER").charAt(0));
		return employee;
	}

	@Override
	public void addEmployee(Employee employee) throws SQLException {
		
		String sql = "INSERT INTO EMPLOYEE(NAME,EMAIL,PHONE,ADDRESS,GENDER) VALUES(?,?,?,?,?)";
		prepareStatment(sql);
		stmt.setString(1, employee.getName());
		stmt.setString(2, employee.getEmail());
		stmt.setString(3, employee.getPhone());
		stmt.setString(4, employee.getAddress());
		stmt.setString(5, employee.getGender()+"");
		stmt.executeUpdate();
	}

	@Override
	public void updateEmployee(int id,Employee employee) throws SQLException {
		Employee oldEmployee = getEmployeeById(id);
		if(employee.getName() == null) 
			employee.setName(oldEmployee.getName());
		if(employee.getPhone() == null)
			employee.setPhone(oldEmployee.getPhone());
		if(employee.getAddress() == null)
			employee.setAddress(oldEmployee.getAddress());
		if(employee.getEmail() == null)
			employee.setEmail(oldEmployee.getEmail());
		if(employee.getGender() == '\u0000')
			employee.setGender(oldEmployee.getGender());
		String sql = "UPDATE EMPLOYEE SET NAME=?,PHONE=?,ADDRESS=?,EMAIL=?,GENDER=? WHERE ID=?";
		prepareStatment(sql);
		stmt.setString(1, employee.getName());
		stmt.setString(2, employee.getPhone());
		stmt.setString(3, employee.getAddress());
		stmt.setString(4, employee.getEmail());
		stmt.setString(5, employee.getGender()+"");
		stmt.setInt(6, id);
		stmt.executeUpdate();
	}

	@Override
	public void deleteEmployee(int id) throws SQLException {
		
		String sql = "DELETE FROM EMPLOYEE WHERE ID=?";
		prepareStatment(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();
	}

}
