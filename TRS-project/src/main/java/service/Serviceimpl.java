package service;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import dao.Daoimpl;
import entity.Student;
@Component
// 通用注释，相当于一个bean
public class Serviceimpl implements Service {

	@Resource
	// 相当于一个setter注入
	private Daoimpl daoimpl;

	/** 登陆 */
	public Student login(String username, String pwd) {
		// TODO Auto-generated method stub
		return daoimpl.findNameAndPwd(username, pwd);
	}

	/** 判断用户名是否存在 */
	public Student findName(String name) {
		// TODO Auto-generated method stub
		return daoimpl.findName(name);
	}

	/** 增加 */
	public void save(Student student) {
		// TODO Auto-generated method stub

		daoimpl.addAll(student);
	}

	/** 找回密码 */
	public Student findpwd(String email) {
		// TODO Auto-generated method stub
		return daoimpl.findpwd(email);
	}

	/** 修改密码 */
	public void updatepwd(String pwd) {
		// TODO Auto-generated method stub

		daoimpl.updatepwd(pwd);

	}

	/** 查询个人信息 */
	public List<Student> find(String name, String pwd) {
		// TODO Auto-generated method stub
		return daoimpl.find(name, pwd);
	}

	/** 查询ID的值 */
	public Student findid(int id) {
		// TODO Auto-generated method stub
		return daoimpl.findid(id);
	}

	/** 修改完善个人信息 */
	public void update(Student student) {
		// TODO Auto-generated method stub
		daoimpl.update(student);
	}

	public void zhuanzhang(String from, String to, double money) {
		// TODO Auto-generated method stub
		daoimpl.zhuanzhang(from, to, money);
	}

	public Student findfrom(String from) {
		// TODO Auto-generated method stub
		return daoimpl.findfrom(from);
	}

	public Student findto(String to) {
		// TODO Auto-generated method stub
		return daoimpl.findto(to);
	}

	public Student findmoney(Double qian) {
		// TODO Auto-generated method stub
		return daoimpl.findmoney(qian);
	}

	// // 增加证券号码
	// public void addson(int numble1, int id, int numble2) {
	// // TODO Auto-generated method stub
	// daoimpl.addson(numble1, id, numble2);
	// // throw new RuntimeException();
	// }

	// 查询证券号码
	public List<Student> students(int id) {
		// TODO Auto-generated method stub
		return daoimpl.students(id);
	}

	// 查询证券号码
	public void DeleteNumble(int id) {
		// TODO Auto-generated method stub
		daoimpl.DeleteNumble(id);
	}

	@SuppressWarnings("rawtypes")
	public Student findById(int id, int NumbleOne, int NumbleTwo) {

		Student s1 = daoimpl.FindById(id);
		System.out.println("第一个id是" + s1.getId());
		System.out.println("第二个id是" + id);
		
		if (s1.getId() == id) {
			System.out.println("你好啊");

			s1.setStudentsecurities(new HashSet());

			daoimpl.addNumblOne(NumbleOne,id);

			daoimpl.addNumbletwo(NumbleTwo,id);

			return s1;
		}

          return null;
	}

	// public void addNumbleOne(int NumbleOne, int id) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// public void addNumbleTwo(int NumbleTwo, int id) {
	// // TODO Auto-generated method stub
	// Studentsecurity s1 = new Studentsecurity();
	//
	// s1.setNumble(NumbleTwo);
	// }

}
