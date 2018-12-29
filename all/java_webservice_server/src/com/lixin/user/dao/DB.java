package com.lixin.user.dao;

import java.util.List;

import com.lixin.model.Menu;


public interface DB {
	
	public List queryRoleData();

	public List queryRoleGroupCount() ;

	public String queryStuAndkmCount(String stuName) ;
	
	public int checkUserLogin(String name, String pwd) ;

	public List<Menu> queryMenuData() ;
	
	public  List  queryClassToStuCount();
	
	
}
