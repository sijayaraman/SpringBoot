package com.spring.bl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	public JdbcTemplate jdbcTemplate = null;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
		this.jdbcTemplate = jdbcTemplate;  
	} 
	
	public List<Employee> getEmployeeList() {

		String SQL = "select * from employee";
		List<Employee> emp = (List<Employee>) jdbcTemplate.query(SQL, new EmployeeMapper());
		System.out.println("Employee:"+emp.toString());
		return emp;
	}

	public Employee getEmployeeById(Integer id) {

		String SQL = "select * from employee where id=?";
		Employee emp = (Employee) jdbcTemplate.queryForObject(SQL,new Object[]{id}, new EmployeeMapper());
		System.out.println("Employee:"+emp.toString());
		return emp;
	}
	
	public Employee createEmployee(Employee employee){
		String SQL = "insert into employee(id,age,location,name) values(?,?,?,?)";
		jdbcTemplate.update(SQL,employee.getId(),employee.getAge(),employee.getLocation(),employee.getName());
		return employee;
	}
	
	public Employee modifyEmployee(Employee employee){
		String SQL = "update employee set age=?,location=?,name=? where id=?";
		jdbcTemplate.update(SQL,employee.getAge(),employee.getLocation(),employee.getName(),employee.getId());
		return employee;
	}
	
	public void deleteEmployee(Integer id){
		String SQL = "delete from employee where id=?";
		jdbcTemplate.update(SQL,id);
	}

}
