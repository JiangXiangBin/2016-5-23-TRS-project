package service;

import java.util.List;

import entity.Role;


public interface RoleService {

	//1查询所有
		List<Role> findAll();
		
	//2增加
		void addRole(Role role);
		
	//3删除	
		void deleteById(int id);
		
	//4修改	
		Role findById(int id);
		void updateRole(Role role);
		
	//5分页
		List<Role> findLimit(int start,int num);//如果从第一条开始，start为0；
		//总有多少条，返回条数！
		int findRoles();
	
}
