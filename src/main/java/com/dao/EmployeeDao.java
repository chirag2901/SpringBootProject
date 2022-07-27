package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.tree.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.bean.EmployeeBean;

@Repository
public class EmployeeDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int addEmployee(EmployeeBean employeeBean) {
		
		
		return jdbcTemplate.update("insert into employee(ename,eage,eemail,epassword) values (?,?,?,?)",employeeBean.geteName(),employeeBean.geteAge(),employeeBean.geteEmail(),employeeBean.getePassword());
		
	}
	public final static class EmployeeMapper implements org.springframework.jdbc.core.RowMapper<EmployeeBean>{

		@Override
		public EmployeeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmployeeBean bean = new EmployeeBean();
			bean.seteId(rs.getInt("eid"));			
			bean.seteAge(rs.getInt("eage"));
			bean.seteName(rs.getString("ename"));
			bean.seteEmail(rs.getString("eemail"));
			bean.setePassword(rs.getString("epassword"));
			return bean;
		}
		
	}
	
	@Cacheable("employee") ///key value 
	public EmployeeBean getEmployeeById(int eId) {
		System.out.println("Dao Called....");
		return jdbcTemplate.queryForObject("select * from employee where eid= "+eId+"", new EmployeeMapper());
	}
	
	public List<EmployeeBean> getAllEmployee() {
		System.out.println("In GetAllEmpluee Dao method");
		return   jdbcTemplate.query("select * from employee", new EmployeeMapper());
	}
	
	public int deleteEmployee(int eid) {
		return jdbcTemplate.update("delete from employee where eid = ?",eid);
	}
}
