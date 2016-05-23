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

	/** 登陆 */

	@RequestMapping("/login.do")
	public String login(@Valid Student student1, BindingResult result,
			String username, String pwd, ModelMap map,
			HttpServletRequest request) {
		// 获取session
		HttpSession session = request.getSession();
		
		Student student = serviceimpl.login(username, pwd);

		// 如果出现验证错误，则转到"login"视图
		if (result.hasErrors()) {

			List<ObjectError> errors = result.getAllErrors();
              
			for (ObjectError error : errors) {
				
				System.out.println("错误是:" + error.getDefaultMessage());
			}
			request.setAttribute("errors", errors);

			return "login";
		} 
		// 判断用户名和密码是否正确，和是否为管理员
		if (student != null && "李四".equals(username) && "123456".equals(pwd)) {
			session.setAttribute("loginsessionone", "welcome to myeclipse");
			// 获取绝对路径
			String path = "/test/findall.do";

			return "redirect:" + path;
			// 判断用户名密码是否正确
		} else if (student != null) {

			// 绑定session的值

			session.setAttribute("loginsession", "hello world");

			// 转发
			map.addAttribute("success", student);

			return "success";

		} else {
			// 转发
			map.addAttribute("fail", "用户名或者密码错误");

			return "login";
		}
	}

	/** 这里是使用hibernate.validator后台验证使用的代码 */

//	@RequestMapping("/login11.do")
//	public String processStudent(@Valid Student student, BindingResult result,
//			HttpServletRequest request, String username, String pwd, Model model) {
//
//		// 如果出现验证错误，则转到"login"视图
//		if (result.hasErrors()) {
//
//			List<ObjectError> errors = result.getAllErrors();
//
//			for (ObjectError error : errors) {
//				System.out.println("错误是:" + error.getDefaultMessage());
//			}
//			request.setAttribute("errors", errors);
//
//			return "login";
//		}
//		// 获取绝对路径
//		String path = "/test/login.do";
//
//		return "redirect:" + path;
//	}

	/** 注册 */
	/**
	 * 验证AJAX
	 * 
	 * 
	 */
	@RequestMapping("/register.do")
	public void addAll(HttpServletRequest request, HttpServletResponse response)
			throws IOException, InterruptedException {

		System.out.println();
		// 输出流
		PrintWriter out = response.getWriter();
		// 获取JSP页面文本框输入的值
		String name = request.getParameter("name");
		// System.out.println(name);
		Student student = serviceimpl.findName(name);
		// 判断用户名是否存在
		if (student == null) {
			out.print(true);
		} else {
			out.print(false);

		}
	}

	/** 添加用户 */

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

	/** 重定向到注册页面 */

	@RequestMapping("/registerout.do")
	public String registerout() {

		String path = "/register.jsp";

		return "redirect:" + path;
	}

	/** 找回密码 */

	@RequestMapping("/find.do")
	public String findpwd(String email, ModelMap map) {

		Student student = serviceimpl.findpwd(email);

		if (student != null) {

			map.addAttribute("find", student);

			return "updatepwd";

		} else {

			map.addAttribute("findno", "没有此邮箱");

			return "nofindpwd";
		}
	}

	/** 修改密码 */

	@RequestMapping("/updatepwd.do")
	public String updatepwd(String pwd, String pwd1, ModelMap map) {

		Student student = new Student();

		student.setPwd(pwd);

		serviceimpl.updatepwd(pwd);
		if (pwd.equals(pwd1) && !"".equals(pwd) && !"".equals(pwd1)) {

			map.addAttribute("good", "修改成功");

			return "good";
		} else if ("".equals(pwd) || "".equals(pwd1)) {
			map.addAttribute("kong", "不能为空");

			return "updatepwd";
		}
		map.addAttribute("no", "输入的密码不一致");

		return "updatepwd";

	}

	/** 登出操作 */

	@RequestMapping("/loginout.do")
	public String loginout() {
		return "login";
	}

	/** 查询个人信息 */

	@RequestMapping("/finduser.do")
	public ModelAndView findAll(ModelMap map, String name, String pwd) {
		// 调用find的方法并且返回的是一个集合
		List<Student> students = serviceimpl.find(name, pwd);

		// 绑定值并且转发
		map.addAttribute("find", students);
		// 转发到finduserJSP中
		return new ModelAndView("finduser");

	}

	/** 查找ID并且返回一个对象给JSP */

	@RequestMapping("/findid.do")
	public ModelAndView findId(int id, ModelMap map) {
		// 调用findid的方法
		Student student = serviceimpl.findid(id);

		System.out.println("ID是" + id);
		// 绑定值并且转发
		map.addAttribute("xinxi", student);
		// 转发到updateuserJSP中
		return new ModelAndView("updateuser");
	}

	/** 修改赋值 */

	@RequestMapping("/updateuser.do")
	public ModelAndView update(String id, HttpServletRequest request,
			ModelMap map, String name, String pwd, String tel, String email) {
		// 创建student对象
		Student student1 = new Student();
		// 给对象赋值
		student1.setId(Integer.parseInt(id));
		student1.setUsername(name);
		student1.setPwd(pwd);
		student1.setTelphone(Long.parseLong(tel));
		student1.setEmail(email);
		// 调用update方法
		serviceimpl.update(student1);
		/** 再次遍历个人信息 */
		List<Student> students = serviceimpl.find(request.getParameter("name"),
				request.getParameter("pwd"));
		map.addAttribute("find", students);
		// 重新转发到个人信息页面
		return new ModelAndView("finduser");
	}

	/** 获取个人的账户信息 */
	@RequestMapping("/zhuanmoney.do")
	public ModelAndView zhuanmoney(int id, ModelMap map) {
		// 调用findid的方法
		Student student = serviceimpl.findid(id);
		// 绑定值并且转发
		map.addAttribute("money", student);
		// 转发到updateuserJSP中
		return new ModelAndView("money");
	}

	/** 执行转账业务 */

	@RequestMapping("/updatemoney.do")
	public String updatemoney(String from, String to, String qian,
			ModelMap map, HttpServletRequest request) {
		System.out.println("打印");
		Double d = Double.valueOf(qian);
		System.out.println(d + ",转账");

		Student s1 = serviceimpl.findfrom(from);

		Student s2 = serviceimpl.findto(to);

		Student s3 = serviceimpl.findmoney(Double.parseDouble(qian));

		if (s1 != null && s2 != null
				&& s1.getMoney() >= Double.parseDouble(qian)) {

			serviceimpl.zhuanzhang(from, to, Double.valueOf(qian));

			map.addAttribute("money", "成功");
			System.out.println(from);
			System.out.println(to);
			System.out.println(qian);

			return "zhuanzhang";

		} else if (s1 != null && s2 != null
				&& s1.getMoney() < Double.parseDouble(qian)) {

			map.addAttribute("name", "余额不足");

			return "money";
		} else {

			map.addAttribute("name1", "账号错误");

			return "money";
		}

	}

	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 清除session
		session.invalidate();

		String path = "/login.jsp";

		return "redirect:" + path;

	}

	/** 通过ID得的student对象 */

	@RequestMapping("/findsonone.do")
	public String finddtuid(int id, ModelMap map) {

		Student stu = serviceimpl.findid(id);

		map.addAttribute("student", stu);

		return "Studentsecurity";
	}

	// 增加证券
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
	// 查询证券
	@SuppressWarnings("unchecked")
	@RequestMapping("/findsontwo.do")
	public String addson1(ModelMap map, int id) {

		System.out.println("id是：" + id);
		// 用list集合接收
		List<Student> s1 = serviceimpl.students(id);
		// 绑定值
		Student student = s1.get(0);
		// 用set集合接收studentsecurity对象
		Set<Studentsecurity> set = student.getStudentsecurities();
		// 转发
		map.addAttribute("some", s1);

		map.addAttribute("some1", set);

		return "findeveryone";
	}

	// 删除证券
	@RequestMapping("/findnumble.do")
	public String addson2(int id, ModelMap map) {

		serviceimpl.DeleteNumble(id);

		return "findeveryone";

	}

	@RequestMapping("/addson.do")
	public String addson(String numbleone, ModelMap map, int id,
			String numbletwo) {
		System.out.println("11是：" + id);
		System.out.println("22是：" + Integer.parseInt(numbleone));
		System.out.println("33是：" + Integer.parseInt(numbletwo));
		Student s1 = serviceimpl.findById(id, Integer.parseInt(numbleone),
				Integer.parseInt(numbletwo));
		System.out.println("1" + id);
		System.out.println("2" + Integer.parseInt(numbleone));
		System.out.println("3" + Integer.parseInt(numbletwo));
		map.addAttribute("name", s1);

		return "findall";
	}

}
