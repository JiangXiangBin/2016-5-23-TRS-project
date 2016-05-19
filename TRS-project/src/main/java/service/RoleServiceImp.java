package service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import dao.RoleDaoimpl;

import entity.Role;


@Component
public class RoleServiceImp implements RoleService {
	
	@Resource
	private RoleDaoimpl roleDaoimpl;

	public List<Role> findAll() {
		
		return roleDaoimpl.findAll();
	}

	public void addRole(Role role) {
		
		roleDaoimpl.addRole(role);

	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub

		roleDaoimpl.deleteById(id);
	}

	public Role findById(int id) {
		// TODO Auto-generated method stub
		return roleDaoimpl.findById(id);
	}

	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		
		roleDaoimpl.updateRole(role);

	}

	public List<Role> findLimit(int start, int num) {
		// TODO Auto-generated method stub
		
		
		return roleDaoimpl.findLimit(start, num);
	}

	public int findRoles() {
		// TODO Auto-generated method stub
		return roleDaoimpl.findRoles();
	}

}
