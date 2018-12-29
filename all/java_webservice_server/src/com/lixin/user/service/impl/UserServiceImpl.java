package com.lixin.user.service.impl;

import java.util.List;

import javax.jws.WebService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lixin.model.Data;
import com.lixin.model.Menu;
import com.lixin.model.Role;
import com.lixin.model.StuAndRole;
import com.lixin.user.dao.DB;
import com.lixin.user.dao.DBMysql;
import com.lixin.user.service.interfaces.IUserService;

@WebService(portName="userservice",
serviceName="UserServiceImpl",
targetNamespace="http://lixin.com/wsdl",
endpointInterface="com.lixin.user.service.interfaces.IUserService")
public class UserServiceImpl   implements IUserService{

	@Override
	public String queryRoleData() {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryRoleData  start...  ");
		
		
		DBMysql  db  = new DBMysql();
		
		List<Role>   lists=db.queryRoleData();
		
		System.out.println("--->"+lists.size());
		
		// webservice发布的数据应该是各个平台和语言统一能够解析的数据格式:
		// json [{},{},{}]
		JSONArray array=new JSONArray();
		for(Role role:lists)
		{
			JSONObject obj=new JSONObject();
			obj.put("id", role.getRid());
			obj.put("rname", role.getRname());
			
			array.add(obj);
		}
		System.out.println("JSON-->" + array.toString());

		return array.toString();
	}
	
	@Override
	public String queryGroupByRoleCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryGroupByRoleCount  start...  ");
	    
		DBMysql db=new DBMysql();
		
		List<StuAndRole> lists=db.queryRoleGroupCount();
		System.out.println("--->" + lists.size());
		JSONArray array=new JSONArray();
		for(StuAndRole crole:lists)
		{
			JSONObject obj=new JSONObject();
			obj.put("rname", crole.getRname());
			obj.put("rcount", crole.getRcount());
			array.add(obj);
		}
		System.out.println("JSON-->" + array.toString());
		return array.toString();
	}
	
	@Override
	public String queryStuAndkmCount(String name) {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl  is queryGroupByRoleCount  start...  ");
		
		DBMysql db = new DBMysql();
		
		String  data=db.queryStuAndkmCount(name);
		
		System.out.println("data-->"+data);
		return data;
	}

	@Override
	public String checkUserLogin(String username, String pwd)
	{
		// TODO Auto-generated method stub
		DBMysql db = new DBMysql();
		System.out.println("UserServiceImpl is queryGroupByRoleCount start.......");
		
		int count=db.checkUserLogin(username,pwd);
		if(count>0)
		{
			return "登录成功";
		}
		return "登录失败";
	}

	@Override
	public String queryGirdMenuData()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl   is   queryGirdMenuData start......");
		
		DBMysql  db = new  DBMysql();
		
		List<Menu>  lists = db.queryMenuData();
		
		//阿里巴巴的json
		String  strJson = com.alibaba.fastjson.JSONArray.toJSONString(lists);
		
		System.out.println("strJson-->"+strJson);
		
		return  strJson;
	}
	@Override
	public String queryClassToStuCount()
	{
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl   is   queryGirdMenuData start......");
		
		DBMysql  db = new  DBMysql();
		
		List<Data>  lists = db.queryClassToStuCount();		
		//阿里巴巴的json
		String  strJson = com.alibaba.fastjson.JSONArray.toJSONString(lists);
		
		System.out.println("List<Data>-->strJson-->"+strJson);
		
		return  strJson;
	}

	
}
