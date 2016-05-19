package dao;

import java.util.List;

import entity.Student;
import entity.Studentsecurity;

public interface Dao {

	// ����һ����½�ķ���

	Student findNameAndPwd(String username, String pwd);

	// ͨ��name�ж��û����Ƿ����

	Student findName(String name);

	// ����һ��ע��ķ���

	void addAll(Student student);

	// ��һ���һ�����ķ���
	Student findpwd(String email);

	// �޸�����ķ���
	void updatepwd(String pwd);

	// ����
	List<Student> find(String name, String pwd);

	// ����ID
	Student findid(int id);

	// �޸�
	void update(Student student);

	// ת��
	void zhuanzhang(String from, String to, Double balance);

	Student findfrom(String from);

	Student findto(String to);

	Student findmoney(Double qian);

	// ����֤ȯ��һ����ͨ��id��ȡ����
	// void addson(int numble1, int id,int numble2);

	// ����֤ȯ�����һ����ͨ��id��ȡstudent����
	Student FindById(int id);

	// ���ӵ�һ������
	void addNumblOne(int NumbleOne,int id);

	// ���ӵڶ�������
	void addNumbletwo(int NumbleTwo,int id);

	// ��ѯ֤ȯ
	List<Student> students(int id);

	// ɾ��֤ȯ
	void DeleteNumble(int id);

}
