package service;

import java.util.List;
import entity.Student;
import entity.Studentsecurity;

public interface Service {

	/** 建立一个登陆的方法 */

	Student login(String username, String pwd);

	/** 通过name判用户名是否存在 */

	Student findName(String name);

	/** 建立一个注册的方法 */

	void save(Student student);

	/** 建立一个找回密码的方法 */
	Student findpwd(String email);

	/** 建立一个修改密码的方法 */

	void updatepwd(String pwd);

	/** 查找 */
	List<Student> find(String name, String pwd);

	/** 查找ID */
	Student findid(int id);

	/** 修改 */
	void update(Student student);

	// 转账
	void zhuanzhang(String from, String to, double money);

	Student findfrom(String from);

	Student findto(String to);

	Student findmoney(Double qian);

	// 增加证券
	// void addson(int numble1, int id,int numble2);

	// 查询证券
	List<Student> students(int id);

	void DeleteNumble(int id);

	Student findById(int id,int NumbleOne,int NumbleTwo);

//	void addNumbleOne(int NumbleOne,int id);
//
//	void addNumbleTwo(int NumbleTwo,int id);

}
