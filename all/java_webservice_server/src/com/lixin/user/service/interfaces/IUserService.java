package com.lixin.user.service.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace="http://lixin.com/wsdl")
public interface IUserService {
	
	//��ѯ��ɫ������
	@WebMethod
	public String queryRoleData();
	
	// �ҳ�ѧ�������ְ���ѧ��������ְ������
	@WebMethod
	public String  queryGroupByRoleCount();
		
	//-- **ѧ����ѧ�γ̵�����
	@WebMethod
	public String  queryStuAndkmCount(String name);
	
	//����¼
	@WebMethod
	public String checkUserLogin(String username,String  pwd);
	
	//���ݲ˵�
	@WebMethod
	public String queryGirdMenuData();
	
	//��ѯ����ְλ������
	
	public String queryClassToStuCount();
	

}
