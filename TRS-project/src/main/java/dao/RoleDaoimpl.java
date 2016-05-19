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

	// ��ѯ
	public List<Role> findAll() {
		// ��ȡsession
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

	// ���
	public void addRole(Role role) {
		
		// TODO Auto-generated method stub
		
		// ��ȡsession����
		
		Session session = sessionFactory.getCurrentSession();

		session.save(role);

		session.flush();

		System.out.println("����ɹ�");

	}

	// ɾ��
	public void deleteById(int id) {
		// ��ȡsession
		Session session = sessionFactory.getCurrentSession();

		Role role = (Role) session.get(Role.class, id);

		session.delete(role);

		session.flush();

		System.out.println("ɾ���ɹ�");

	}

	// ͨ��ID��ѯ����
	@SuppressWarnings("unchecked")
	public Role findById(int id) {
		// ��ȡsession

		Session session = sessionFactory.getCurrentSession();

		String hql = "from Role where id='" + id + "'";

		Query query = session.createQuery(hql);

		List<Role> p2 = query.list();

		if (!p2.isEmpty()) {

			return p2.get(0);
		}

		return null;
	}

	// �޸�
	public void updateRole(Role role) {

		// ��ȡsession
		Session session = sessionFactory.getCurrentSession();

		// System.out.println("nnnnn");

		session.update(role);

		session.flush();

		System.out.println("�޸ĳɹ�");

	}

	// ��ҳ��ѯ
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

	// ��ѯ����
	public int findRoles() {
		// TODO Auto-generated method stub

		Session session = sessionFactory.getCurrentSession();

		String hql = "from Role";

		Query query = session.createQuery(hql);

		int a = query.list().size();

		System.out.println("a" + a + "��");

		return a;
	}

}
