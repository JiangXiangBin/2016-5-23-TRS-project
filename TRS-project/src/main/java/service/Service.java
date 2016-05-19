package service;

import java.util.List;
import entity.Student;
import entity.Studentsecurity;

public interface Service {

	/** ����һ����½�ķ��� */

	Student login(String username, String pwd);

	/** ͨ��name���û����Ƿ���� */

	Student findName(String name);

	/** ����һ��ע��ķ��� */

	void save(Student student);

	/** ����һ���һ�����ķ��� */
	Student findpwd(String email);

	/** ����һ���޸�����ķ��� */

	void updatepwd(String pwd);

	/** ���� */
	List<Student> find(String name, String pwd);

	/** ����ID */
	Student findid(int id);

	/** �޸� */
	void update(Student student);

	// ת��
	void zhuanzhang(String from, String to, double money);

	Student findfrom(String from);

	Student findto(String to);

	Student findmoney(Double qian);

	// ����֤ȯ
	// void addson(int numble1, int id,int numble2);

	// ��ѯ֤ȯ
	List<Student> students(int id);

	void DeleteNumble(int id);

	Student findById(int id,int NumbleOne,int NumbleTwo);

//	void addNumbleOne(int NumbleOne,int id);
//
//	void addNumbleTwo(int NumbleTwo,int id);

}
