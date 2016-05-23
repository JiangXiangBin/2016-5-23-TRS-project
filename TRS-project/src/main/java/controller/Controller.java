package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javassist.expr.NewArray;

import javax.annotation.Resource;
import javax.persistence.ManyToOne;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.persistence.Entity;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.omg.CORBA.Request;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import entity.Student;
import entity.Studentsecurity;
import service.Serviceimpl;

@SuppressWarnings("unused")
@org.springframework.stereotype.Controller
@RequestMapping("/test")
public class Controller {

	@Resource
	private Serviceimpl serviceimpl;

	/** ��½ */

	@RequestMapping("/login.do")
	public String login(@Valid Student student1, BindingResult result,
			String username, String pwd, ModelMap map,
			HttpServletRequest request) {
		// ��ȡsession
		HttpSession session = request.getSession();
		
		Student student = serviceimpl.login(username, pwd);

		// ���������֤������ת��"login"��ͼ
		if (result.hasErrors()) {

			List<ObjectError> errors = result.getAllErrors();
              
			for (ObjectError error : errors) {
				
				System.out.println("������:" + error.getDefaultMessage());
			}
			request.setAttribute("errors", errors);

			return "login";
		} 
		// �ж��û����������Ƿ���ȷ�����Ƿ�Ϊ����Ա
		if (student != null && "����".equals(username) && "123456".equals(pwd)) {
			session.setAttribute("loginsessionone", "welcome to myeclipse");
			// ��ȡ����·��
			String path = "/test/findall.do";

			return "redirect:" + path;
			// �ж��û��������Ƿ���ȷ
		} else if (student != null) {

			// ��session��ֵ

			session.setAttribute("loginsession", "hello world");

			// ת��
			map.addAttribute("success", student);

			return "success";

		} else {
			// ת��
			map.addAttribute("fail", "�û��������������");

			return "login";
		}
	}

	/** ������ʹ��hibernate.validator��̨��֤ʹ�õĴ��� */

//	@RequestMapping("/login11.do")
//	public String processStudent(@Valid Student student, BindingResult result,
//			HttpServletRequest request, String username, String pwd, Model model) {
//
//		// ���������֤������ת��"login"��ͼ
//		if (result.hasErrors()) {
//
//			List<ObjectError> errors = result.getAllErrors();
//
//			for (ObjectError error : errors) {
//				System.out.println("������:" + error.getDefaultMessage());
//			}
//			request.setAttribute("errors", errors);
//
//			return "login";
//		}
//		// ��ȡ����·��
//		String path = "/test/login.do";
//
//		return "redirect:" + path;
//	}

	/** ע�� */
	/**
	 * ��֤AJAX
	 * 
	 * 
	 */
	@RequestMapping("/register.do")
	public void addAll(HttpServletRequest request, HttpServletResponse response)
			throws IOException, InterruptedException {

		System.out.println();
		// �����
		PrintWriter out = response.getWriter();
		// ��ȡJSPҳ���ı��������ֵ
		String name = request.getParameter("name");
		// System.out.println(name);
		Student student = serviceimpl.findName(name);
		// �ж��û����Ƿ����
		if (student == null) {
			out.print(true);
		} else {
			out.print(false);

		}
	}

	/** ����û� */

	@RequestMapping("/register1.do")
	public String addadminall(String name, String pwd, String telphone,
			String email, ModelMap map) {

		Student student1 = serviceimpl.findName(name);

		if (student1 == null) {

			Student student3 = new Student(name, pwd, Long.parseLong(telphone),
					email);

			serviceimpl.save(student3);

			map.addAttribute("register2", student3);
		}
		return "success1";
	}

	/** �ض���ע��ҳ�� */

	@RequestMapping("/registerout.do")
	public String registerout() {

		String path = "/register.jsp";

		return "redirect:" + path;
	}

	/** �һ����� */

	@RequestMapping("/find.do")
	public String findpwd(String email, ModelMap map) {

		Student student = serviceimpl.findpwd(email);

		if (student != null) {

			map.addAttribute("find", student);

			return "updatepwd";

		} else {

			map.addAttribute("findno", "û�д�����");

			return "nofindpwd";
		}
	}

	/** �޸����� */

	@RequestMapping("/updatepwd.do")
	public String updatepwd(String pwd, String pwd1, ModelMap map) {

		Student student = new Student();

		student.setPwd(pwd);

		serviceimpl.updatepwd(pwd);
		if (pwd.equals(pwd1) && !"".equals(pwd) && !"".equals(pwd1)) {

			map.addAttribute("good", "�޸ĳɹ�");

			return "good";
		} else if ("".equals(pwd) || "".equals(pwd1)) {
			map.addAttribute("kong", "����Ϊ��");

			return "updatepwd";
		}
		map.addAttribute("no", "��������벻һ��");

		return "updatepwd";

	}

	/** �ǳ����� */

	@RequestMapping("/loginout.do")
	public String loginout() {
		return "login";
	}

	/** ��ѯ������Ϣ */

	@RequestMapping("/finduser.do")
	public ModelAndView findAll(ModelMap map, String name, String pwd) {
		// ����find�ķ������ҷ��ص���һ������
		List<Student> students = serviceimpl.find(name, pwd);

		// ��ֵ����ת��
		map.addAttribute("find", students);
		// ת����finduserJSP��
		return new ModelAndView("finduser");

	}

	/** ����ID���ҷ���һ�������JSP */

	@RequestMapping("/findid.do")
	public ModelAndView findId(int id, ModelMap map) {
		// ����findid�ķ���
		Student student = serviceimpl.findid(id);

		System.out.println("ID��" + id);
		// ��ֵ����ת��
		map.addAttribute("xinxi", student);
		// ת����updateuserJSP��
		return new ModelAndView("updateuser");
	}

	/** �޸ĸ�ֵ */

	@RequestMapping("/updateuser.do")
	public ModelAndView update(String id, HttpServletRequest request,
			ModelMap map, String name, String pwd, String tel, String email) {
		// ����student����
		Student student1 = new Student();
		// ������ֵ
		student1.setId(Integer.parseInt(id));
		student1.setUsername(name);
		student1.setPwd(pwd);
		student1.setTelphone(Long.parseLong(tel));
		student1.setEmail(email);
		// ����update����
		serviceimpl.update(student1);
		/** �ٴα���������Ϣ */
		List<Student> students = serviceimpl.find(request.getParameter("name"),
				request.getParameter("pwd"));
		map.addAttribute("find", students);
		// ����ת����������Ϣҳ��
		return new ModelAndView("finduser");
	}

	/** ��ȡ���˵��˻���Ϣ */
	@RequestMapping("/zhuanmoney.do")
	public ModelAndView zhuanmoney(int id, ModelMap map) {
		// ����findid�ķ���
		Student student = serviceimpl.findid(id);
		// ��ֵ����ת��
		map.addAttribute("money", student);
		// ת����updateuserJSP��
		return new ModelAndView("money");
	}

	/** ִ��ת��ҵ�� */

	@RequestMapping("/updatemoney.do")
	public String updatemoney(String from, String to, String qian,
			ModelMap map, HttpServletRequest request) {
		System.out.println("��ӡ");
		Double d = Double.valueOf(qian);
		System.out.println(d + ",ת��");

		Student s1 = serviceimpl.findfrom(from);

		Student s2 = serviceimpl.findto(to);

		Student s3 = serviceimpl.findmoney(Double.parseDouble(qian));

		if (s1 != null && s2 != null
				&& s1.getMoney() >= Double.parseDouble(qian)) {

			serviceimpl.zhuanzhang(from, to, Double.valueOf(qian));

			map.addAttribute("money", "�ɹ�");
			System.out.println(from);
			System.out.println(to);
			System.out.println(qian);

			return "zhuanzhang";

		} else if (s1 != null && s2 != null
				&& s1.getMoney() < Double.parseDouble(qian)) {

			map.addAttribute("name", "����");

			return "money";
		} else {

			map.addAttribute("name1", "�˺Ŵ���");

			return "money";
		}

	}

	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// ���session
		session.invalidate();

		String path = "/login.jsp";

		return "redirect:" + path;

	}

	/** ͨ��ID�õ�student���� */

	@RequestMapping("/findsonone.do")
	public String finddtuid(int id, ModelMap map) {

		Student stu = serviceimpl.findid(id);

		map.addAttribute("student", stu);

		return "Studentsecurity";
	}

	// ����֤ȯ
	// @RequestMapping("/addson.do")
	// public String addson(String numbleone, ModelMap map, int id,String
	// numbletwo) {
	//
	// serviceimpl.addson(Integer.parseInt(numbleone),
	// id,Integer.parseInt(numbletwo));
	//
	// map.addAttribute("name", numbleone);
	// map.addAttribute("name1", numbletwo);
	//
	// return "findall";
	// }
	// ��ѯ֤ȯ
	@SuppressWarnings("unchecked")
	@RequestMapping("/findsontwo.do")
	public String addson1(ModelMap map, int id) {

		System.out.println("id�ǣ�" + id);
		// ��list���Ͻ���
		List<Student> s1 = serviceimpl.students(id);
		// ��ֵ
		Student student = s1.get(0);
		// ��set���Ͻ���studentsecurity����
		Set<Studentsecurity> set = student.getStudentsecurities();
		// ת��
		map.addAttribute("some", s1);

		map.addAttribute("some1", set);

		return "findeveryone";
	}

	// ɾ��֤ȯ
	@RequestMapping("/findnumble.do")
	public String addson2(int id, ModelMap map) {

		serviceimpl.DeleteNumble(id);

		return "findeveryone";

	}

	@RequestMapping("/addson.do")
	public String addson(String numbleone, ModelMap map, int id,
			String numbletwo) {
		System.out.println("11�ǣ�" + id);
		System.out.println("22�ǣ�" + Integer.parseInt(numbleone));
		System.out.println("33�ǣ�" + Integer.parseInt(numbletwo));
		Student s1 = serviceimpl.findById(id, Integer.parseInt(numbleone),
				Integer.parseInt(numbletwo));
		System.out.println("1" + id);
		System.out.println("2" + Integer.parseInt(numbleone));
		System.out.println("3" + Integer.parseInt(numbletwo));
		map.addAttribute("name", s1);

		return "findall";
	}

}
