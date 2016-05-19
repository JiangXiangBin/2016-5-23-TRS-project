package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import entity.Student;
import entity.Studentsecurity;

@SuppressWarnings("unused")
@Component
// ͨ��ע�ͣ��൱��һ��bean
public class Daoimpl implements Dao {

	@Resource
	// �൱��һ��setterע��
	private SessionFactory sessionFactory;

	/** ��½�ķ��� */

	@SuppressWarnings("unchecked")
	public Student findNameAndPwd(String username, String pwd) {
		// ��ȡsession
		Session session = sessionFactory.getCurrentSession();

		// ����һ��hql���
		String hql = "from Student  where username='" + username
				+ "'and pwd ='" + pwd + "'";

		Query query = session.createQuery(hql);

		// ���������
		List<Student> st1 = query.list();

		if (!st1.isEmpty()) {
			return st1.get(0);
		}
		return null;
	}

	/** ��֤�û����Ƿ���� */
	@SuppressWarnings("unchecked")
	public Student findName(String name) {
		// ��ȡsession
		Session session = sessionFactory.getCurrentSession();
		// ����hql���
		String hql = "from Student where username='" + name + "'";

		Query query = session.createQuery(hql);
		// ���������
		List<Student> st2 = query.list();

		if (!st2.isEmpty()) {
			return st2.get(0);

		}

		return null;
	}

	/** ���� */
	public void addAll(Student student) {
		// TODO Auto-generated method stub

		// ��ȡsession

		Session session = sessionFactory.getCurrentSession();
		// ����
		session.save(student);
		// �������
		session.flush();

		System.out.println("����ɹ�");
	}

	/** ��ѯ���� */
	@SuppressWarnings("unchecked")
	public Student findpwd(String email) {

		// ��ȡsession
		Session session = sessionFactory.getCurrentSession();
		// ִ��hql���
		String hql = "from Student where email='" + email + "'";

		Query query = session.createQuery(hql);
		// ���������
		List<Student> st3 = query.list();

		if (!st3.isEmpty()) {

			return st3.get(0);
		}

		return null;
	}

	/** �޸����� */
	public void updatepwd(String pwd) {

		// ��ȡsession
		Session session = sessionFactory.getCurrentSession();
		// �޸�
		session.update(pwd);

		session.flush();

		System.out.println("�޸ĳɹ�");

	}

	/** ��ѯ������Ϣ */
	public List<Student> find(String name, String pwd) {

		Session session = sessionFactory.getCurrentSession();

		String hql = "from Student where username='" + name + "' and pwd='"
				+ pwd + "' ";

		Query query = session.createQuery(hql);
		// ���������
		@SuppressWarnings("unchecked")
		List<Student> students = query.list();

		if (!students.isEmpty()) {

			return students;

		} else {

			return null;
		}
	}

	/** ��ѯID */
	@SuppressWarnings("unchecked")
	public Student findid(int id) {
		Session session = sessionFactory.getCurrentSession();

		String hql = "from Student where id='" + id + "'";

		Query query = session.createQuery(hql);

		List<Student> p2 = query.list();

		if (!p2.isEmpty()) {

			return p2.get(0);
		}

		return null;
	}

	/** �޸ĺ������û�����Ϣ */
	public void update(Student student) {
		Session session = sessionFactory.getCurrentSession();

		session.update(student);

		session.flush();

		System.out.println("�޸ĳɹ�");

	}

	/** ת�� */
	public void zhuanzhang(String from, String to, Double balance) {

		Session session = sessionFactory.getCurrentSession();

		Transaction t = session.beginTransaction();

		try {
			// ��Ǯ����
			String hql1 = "update Student set money=money-? where username=?";

			Query query1 = session.createQuery(hql1);

			query1.setDouble(0, balance);

			query1.setString(1, from);

			query1.executeUpdate();

			// ��Ǯ����
			String hql2 = "update Student set money=money+? where username=?";

			Query query2 = session.createQuery(hql2);

			query2.setDouble(0, balance);

			query2.setString(1, to);

			query2.executeUpdate();

			System.out.println("ת�˳ɹ�");

		} catch (Exception e) {
			// �ع�
			t.rollback();
			System.out.println("�ع�����");
			e.printStackTrace();

		}
		t.commit();
	}

	@SuppressWarnings("unchecked")
	public Student findfrom(String from) {

		Session session = sessionFactory.getCurrentSession();

		String hql = "from Student where username='" + from + "'";

		Query query = session.createQuery(hql);

		List<Student> s1 = query.list();

		if (!s1.isEmpty()) {

			return s1.get(0);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public Student findto(String to) {
		Session session = sessionFactory.getCurrentSession();

		String hql = "from Student where username='" + to + "'";

		Query query = session.createQuery(hql);

		List<Student> s1 = query.list();

		if (!s1.isEmpty()) {

			return s1.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Student findmoney(Double qian) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Student where money='" + qian + "'";

		Query query = session.createQuery(hql);

		List<Student> s1 = query.list();

		if (!s1.isEmpty()) {

			return s1.get(0);
		}
		return null;
	}

	// ����֤ȯ���루ʹ��spring����
	// @SuppressWarnings({ "unchecked", "rawtypes" })
	// public void addson(int numble1, int id, int numble2) {
	//
	// Session session = sessionFactory.getCurrentSession();
	//
	// // Transaction t = session.beginTransaction();
	//
	// try {
	//
	// String hql = "from Student where id='" + id + "'";
	//
	// Query query = session.createQuery(hql);
	//
	// List<Student> s1 = query.list();
	//
	// if (!s1.isEmpty()) {
	// // �󶨶���
	// Student student = s1.get(0);
	//
	// student.setStudentsecurities(new HashSet());
	//
	// Studentsecurity se1 = new Studentsecurity();
	//
	// Studentsecurity se2 = new Studentsecurity();
	// // �����ֵ1
	// se1.setNumble(numble1);
	// // �����ֵ2
	// se2.setNumble(numble2);
	//
	// se1.setStudent(student);
	//
	// se2.setStudent(student);
	// // ��ֵ1��ֵ2ͨ��һ�Զ�Ĺ�����ϵ��ӵ�student��������
	// student.getStudentsecurities().add(se1);
	//
	// student.getStudentsecurities().add(se2);
	// // ����
	// session.save(student);
	//
	// System.out.println("����ɹ�-");
	//
	// session.flush();
	//
	// // t.commit();
	//
	// // System.out.println("����ɹ�+");
	// }
	// } catch (Exception e) {
	//
	// System.out.println("�ع�����");
	// // e.printStackTrace();
	// // t.rollback();
	//
	// // System.out.println("���治�ɹ�");
	// throw new RuntimeException("�쳣����");
	//
	// } finally {
	//
	// session.close();
	// }
	// }

	// �������Ұ��Ǹ�֤ȯ���� ��ʾ�����ķ���
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Student> students(int id) {
		// TODO Auto-generated method stub
		// ��ȡsession
		Session session = sessionFactory.getCurrentSession();
		// ��������
		// Transaction t = session.beginTransaction();

		String hql = "from Student where id='" + id + "'";

		Query query = session.createQuery(hql);

		List<Student> stu1 = query.list();

		if (!stu1.isEmpty()) {
			// �󶨶���
			// Student student = stu1.get(0);
			// System.out.println("ID�ǣ�"+stu1.get(id));
			// ��set���Ͻ���֤ȯ����
			// Set<Studentsecurity> set = student.getStudentsecurities();
			// ʹ�õ��������������
			// for (Iterator ite = set.iterator(); ite.hasNext();) {
			//
			// Studentsecurity se = (Studentsecurity) ite.next();
			//
			// System.out.println("֤ȯ�����ǣ�" + se.getNumble());
			// }
			// for (Studentsecurity se : set) {
			//
			// System.out.println("֤ȯ�����ǣ�" + se.getNumble());
			// }
			return stu1;
		} else {

			return null;
		}

	}

	// ɾ��֤ȯ�ķ���
	@SuppressWarnings("unchecked")
	public void DeleteNumble(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();

		String hql = "from Student where id='" + id + "'";

		Query query = session.createQuery(hql);

		List<Student> list = query.list();

		if (!list.isEmpty()) {

			Student student = list.get(0);

			session.delete(student);

			session.flush();

			System.out.println("ɾ���ɹ�");

		}

	}

	// ����֤ȯ����ĵ�һ��;��ȡstudent����
	@SuppressWarnings("unchecked")
	public Student FindById(int id) {
		// TODO Auto-generated method stub
		// ��ȡsession����
		Session session = sessionFactory.getCurrentSession();
		// ����hql���
		String hql = "from Student where id='" + id + "'";

		Query query = session.createQuery(hql);
		// ���������
		List<Student> s3 = query.list();

		if (!s3.isEmpty()) {

			return s3.get(0);
		}
		return null;
	}

	// ����֤ȯ���룺����ֵһ
	@SuppressWarnings("unchecked")
	public void addNumblOne(int NumbleOne, int id) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.getCurrentSession();

		Daoimpl daoimpl = new Daoimpl();

		Student s1 = daoimpl.FindById(id);

		Studentsecurity s2 = new Studentsecurity();

		s2.setNumble(NumbleOne);

		s2.setStudent(s1);

		s1.getStudentsecurities().add(s2);

		session.save(s1);

	}

	// ����֤ȯ���룺����ֵ��
	@SuppressWarnings("unchecked")
	public void addNumbletwo(int NumbleTwo, int id) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.getCurrentSession();

		Daoimpl daoimpl = new Daoimpl();

		Student s1 = daoimpl.FindById(id);

		System.out.println("����2������" + s1.getUsername());

		Studentsecurity s2 = new Studentsecurity();

		s2.setNumble(NumbleTwo);

		s2.setStudent(s1);

		s1.getStudentsecurities().add(s2);

		session.save(s1);

	}

}
