package dao;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.Entity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import entity.Role;

@Entity
@Component
public class RoleDaoimpl implements RolesDao {

	@Resource
	private SessionFactory sessionFactory;

	// 查询
	public List<Role> findAll() {
		// 获取session
		Session session = sessionFactory.getCurrentSession();

		String hql = "from Role";

		Query query = session.createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Role> roles = query.list();

		if (!roles.isEmpty()) {

			return roles;
		}

		return null;
	}

	// 添加
	public void addRole(Role role) {
		
		// TODO Auto-generated method stub
		
		// 获取session对象
		
		Session session = sessionFactory.getCurrentSession();

		session.save(role);

		session.flush();

		System.out.println("保存成功");

	}

	// 删除
	public void deleteById(int id) {
		// 获取session
		Session session = sessionFactory.getCurrentSession();

		Role role = (Role) session.get(Role.class, id);

		session.delete(role);

		session.flush();

		System.out.println("删除成功");

	}

	// 通过ID查询数据
	@SuppressWarnings("unchecked")
	public Role findById(int id) {
		// 获取session

		Session session = sessionFactory.getCurrentSession();

		String hql = "from Role where id='" + id + "'";

		Query query = session.createQuery(hql);

		List<Role> p2 = query.list();

		if (!p2.isEmpty()) {

			return p2.get(0);
		}

		return null;
	}

	// 修改
	public void updateRole(Role role) {

		// 获取session
		Session session = sessionFactory.getCurrentSession();

		// System.out.println("nnnnn");

		session.update(role);

		session.flush();

		System.out.println("修改成功");

	}

	// 分页查询
	@SuppressWarnings("unchecked")
	public List<Role> findLimit(int start, int num) {

		Session session = sessionFactory.getCurrentSession();

		String hql = "from Role";

		Query query = session.createQuery(hql);

		query.setFirstResult(start);
		
		query.setMaxResults(num);

		List<Role> roles = query.list();

		if (!roles.isEmpty()) {

			return roles;
		}

		return null;
	}

	// 查询总数
	public int findRoles() {
		// TODO Auto-generated method stub

		Session session = sessionFactory.getCurrentSession();

		String hql = "from Role";

		Query query = session.createQuery(hql);

		int a = query.list().size();

		System.out.println("a" + a + "条");

		return a;
	}

}
