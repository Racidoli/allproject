package com.lixin.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.lixin.model.Menu;

public class DBOracle implements DB
{
	private Connection conn;

	public DBOracle() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:XE", "student", "student");

			System.out.println("oracle数据库连接成功"+conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List queryRoleData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List queryRoleGroupCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String queryStuAndkmCount(String stuName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int checkUserLogin(String name, String pwd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Menu> queryMenuData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List queryClassToStuCount() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
