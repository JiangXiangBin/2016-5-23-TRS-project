package dao;

import java.util.List;

import entity.Student;
import entity.Studentsecurity;

public interface Dao {

	// 建立一个登陆的方法

	Student findNameAndPwd(String username, String pwd);

	// 通过name判断用户名是否存在

	Student findName(String name);

	// 建立一个注册的方法

	void addAll(Student student);

	// 建一个找回密码的方法
	Student findpwd(String email);

	// 修改密码的方法
	void updatepwd(String pwd);

	// 查找
	List<Student> find(String name, String pwd);

	// 查找ID
	Student findid(int id);

	// 修改
	void update(Student student);

	// 转账
	void zhuanzhang(String from, String to, Double balance);

	Student findfrom(String from);

	Student findto(String to);

	Student findmoney(Double qian);

	// 增加证券第一步：通过id获取对象
	// void addson(int numble1, int id,int numble2);

	// 增加证券号码第一步：通过id获取student对象
	Student FindById(int id);

	// 增加第一个号码
	void addNumblOne(int NumbleOne,int id);

	// 增加第二个号码
	void addNumbletwo(int NumbleTwo,int id);

	// 查询证券
	List<Student> students(int id);

	// 删除证券
	void DeleteNumble(int id);

}
