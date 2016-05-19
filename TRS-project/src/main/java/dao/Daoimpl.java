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
// 通用注释，相当于一个bean
public class Daoimpl implements Dao {

	@Resource
	// 相当于一个setter注入
	private SessionFactory sessionFactory;

	/** 登陆的方法 */

	@SuppressWarnings("unchecked")
	public Student findNameAndPwd(String username, String pwd) {
		// 获取session
		Session session = sessionFactory.getCurrentSession();

		// 创建一个hql语句
		String hql = "from Student  where username='" + username
				+ "'and pwd ='" + pwd + "'";

		Query query = session.createQuery(hql);

		// 遍历结果集
		List<Student> st1 = query.list();

		if (!st1.isEmpty()) {
			return st1.get(0);
		}
		return null;
	}

	/** 验证用户名是否存在 */
	@SuppressWarnings("unchecked")
	public Student findName(String name) {
		// 获取session
		Session session = sessionFactory.getCurrentSession();
		// 创建hql语句
		String hql = "from Student where username='" + name + "'";

		Query query = session.createQuery(hql);
		// 遍历结果集
		List<Student> st2 = query.list();

		if (!st2.isEmpty()) {
			return st2.get(0);

		}

		return null;
	}

	/** 增加 */
	public void addAll(Student student) {
		// TODO Auto-generated method stub

		// 获取session

		Session session = sessionFactory.getCurrentSession();
		// 保存
		session.save(student);
		// 清除缓冲
		session.flush();

		System.out.println("保存成功");
	}

	/** 查询邮箱 */
	@SuppressWarnings("unchecked")
	public Student findpwd(String email) {

		// 获取session
		Session session = sessionFactory.getCurrentSession();
		// 执行hql语句
		String hql = "from Student where email='" + email + "'";

		Query query = session.createQuery(hql);
		// 遍历结果集
		List<Student> st3 = query.list();

		if (!st3.isEmpty()) {

			return st3.get(0);
		}

		return null;
	}

	/** 修改密码 */
	public void updatepwd(String pwd) {

		// 获取session
		Session session = sessionFactory.getCurrentSession();
		// 修改
		session.update(pwd);

		session.flush();

		System.out.println("修改成功");

	}

	/** 查询个人信息 */
	public List<Student> find(String name, String pwd) {

		Session session = sessionFactory.getCurrentSession();

		String hql = "from Student where username='" + name + "' and pwd='"
				+ pwd + "' ";

		Query query = session.createQuery(hql);
		// 遍历结果集
		@SuppressWarnings("unchecked")
		List<Student> students = query.list();

		if (!students.isEmpty()) {

			return students;

		} else {

			return null;
		}
	}

	/** 查询ID */
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

	/** 修改和完善用户的信息 */
	public void update(Student student) {
		Session session = sessionFactory.getCurrentSession();

		session.update(student);

		session.flush();

		System.out.println("修改成功");

	}

	/** 转账 */
	public void zhuanzhang(String from, String to, Double balance) {

		Session session = sessionFactory.getCurrentSession();

		Transaction t = session.beginTransaction();

		try {
			// 给钱的人
			String hql1 = "update Student set money=money-? where username=?";

			Query query1 = session.createQuery(hql1);

			query1.setDouble(0, balance);

			query1.setString(1, from);

			query1.executeUpdate();

			// 拿钱的人
			String hql2 = "update Student set money=money+? where username=?";

			Query query2 = session.createQuery(hql2);

			query2.setDouble(0, balance);

			query2.setString(1, to);

			query2.executeUpdate();

			System.out.println("转账成功");

		} catch (Exception e) {
			// 回滚
			t.rollback();
			System.out.println("回滚操作");
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

	// 增加证券号码（使用spring事务）
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
	// // 绑定对象
	// Student student = s1.get(0);
	//
	// student.setStudentsecurities(new HashSet());
	//
	// Studentsecurity se1 = new Studentsecurity();
	//
	// Studentsecurity se2 = new Studentsecurity();
	// // 传入的值1
	// se1.setNumble(numble1);
	// // 传入的值2
	// se2.setNumble(numble2);
	//
	// se1.setStudent(student);
	//
	// se2.setStudent(student);
	// // 将值1和值2通过一对多的关联关系添加到student对象里面
	// student.getStudentsecurities().add(se1);
	//
	// student.getStudentsecurities().add(se2);
	// // 保存
	// session.save(student);
	//
	// System.out.println("保存成功-");
	//
	// session.flush();
	//
	// // t.commit();
	//
	// // System.out.println("保存成功+");
	// }
	// } catch (Exception e) {
	//
	// System.out.println("回滚操作");
	// // e.printStackTrace();
	// // t.rollback();
	//
	// // System.out.println("保存不成功");
	// throw new RuntimeException("异常操作");
	//
	// } finally {
	//
	// session.close();
	// }
	// }

	// 这里是我把那个证券号码 显示出来的方法
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Student> students(int id) {
		// TODO Auto-generated method stub
		// 获取session
		Session session = sessionFactory.getCurrentSession();
		// 开启事务
		// Transaction t = session.beginTransaction();

		String hql = "from Student where id='" + id + "'";

		Query query = session.createQuery(hql);

		List<Student> stu1 = query.list();

		if (!stu1.isEmpty()) {
			// 绑定对象
			// Student student = stu1.get(0);
			// System.out.println("ID是："+stu1.get(id));
			// 用set集合接收证券对象
			// Set<Studentsecurity> set = student.getStudentsecurities();
			// 使用迭代器遍历结果集
			// for (Iterator ite = set.iterator(); ite.hasNext();) {
			//
			// Studentsecurity se = (Studentsecurity) ite.next();
			//
			// System.out.println("证券号码是：" + se.getNumble());
			// }
			// for (Studentsecurity se : set) {
			//
			// System.out.println("证券号码是：" + se.getNumble());
			// }
			return stu1;
		} else {

			return null;
		}

	}

	// 删除证券的方法
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

			System.out.println("删除成功");

		}

	}

	// 增加证券号码的第一步;获取student对象
	@SuppressWarnings("unchecked")
	public Student FindById(int id) {
		// TODO Auto-generated method stub
		// 获取session对象
		Session session = sessionFactory.getCurrentSession();
		// 创建hql语句
		String hql = "from Student where id='" + id + "'";

		Query query = session.createQuery(hql);
		// 遍历结果集
		List<Student> s3 = query.list();

		if (!s3.isEmpty()) {

			return s3.get(0);
		}
		return null;
	}

	// 增加证券号码：增加值一
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

	// 增加证券号码：增加值二
	@SuppressWarnings("unchecked")
	public void addNumbletwo(int NumbleTwo, int id) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.getCurrentSession();

		Daoimpl daoimpl = new Daoimpl();

		Student s1 = daoimpl.FindById(id);

		System.out.println("号码2名字是" + s1.getUsername());

		Studentsecurity s2 = new Studentsecurity();

		s2.setNumble(NumbleTwo);

		s2.setStudent(s1);

		s1.getStudentsecurities().add(s2);

		session.save(s1);

	}

}
