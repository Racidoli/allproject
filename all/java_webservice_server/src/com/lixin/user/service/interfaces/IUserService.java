package com.lixin.user.service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace="http://lixin.com/wsdl")
public interface IUserService {
	
	//查询角色的数据
	@WebMethod
	public String queryRoleData();
	
	// 找出学生表各个职务的学生数量和职务名称
	@WebMethod
	public String  queryGroupByRoleCount();
		
	//-- **学生所学课程的数量
	@WebMethod
	public String  queryStuAndkmCount(String name);
	
	//检查登录
	@WebMethod
	public String checkUserLogin(String username,String  pwd);
	
	//数据菜单
	@WebMethod
	public String queryGirdMenuData();
	
	//查询各个职位的数量
	
	public String queryClassToStuCount();
	

}
