package service;

import java.util.List;

import entity.Role;


public interface RoleService {

	//1��ѯ����
		List<Role> findAll();
		
	//2����
		void addRole(Role role);
		
	//3ɾ��	
		void deleteById(int id);
		
	//4�޸�	
		Role findById(int id);
		void updateRole(Role role);
		
	//5��ҳ
		List<Role> findLimit(int start,int num);//����ӵ�һ����ʼ��startΪ0��
		//���ж�����������������
		int findRoles();
	
}
