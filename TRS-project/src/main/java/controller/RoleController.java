package controller;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.ManyToOne;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.persistence.Entity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import service.RoleServiceImp;
import entity.Role;


@SuppressWarnings("unused")
@Controller
@RequestMapping("/test")
public class RoleController {
	private int pageNum = 1;
	@Resource
	private RoleServiceImp roleServiceImp;

	// 分页查询
	@RequestMapping("/findall.do")
	public String findall(ModelMap map, HttpServletRequest request) {

		HttpSession session=request.getSession();
		
		
		List<Role> roles = roleServiceImp.findLimit(0, 3);
	
		map.addAttribute("roles", roles);

		return "adminall";

	}

	// 删除
	@RequestMapping("/delete.do")
	
	public String delete(int id) {

		roleServiceImp.deleteById(id);

		System.out.println(id);

		String path = "/test/findall.do";

		return "redirect:" + path;

	}
	
	// 通过ID修改
	@RequestMapping("/findById.do")
	
	public String findbyid(int id, ModelMap map) {

		Role role = roleServiceImp.findById(id);

		map.addAttribute("role", role);

		return "update";
	}
	// 修改
	@RequestMapping("/update.do")
	public String update(String id, String name, String gender, String grade,
			String blood) {

		Role role = new Role();
		role.setId(Integer.parseInt(id));
		role.setUsername(name);
		role.setGender(gender);
		role.setGrade(Integer.parseInt(grade));
		role.setBlood(Integer.parseInt(blood));
		roleServiceImp.updateRole(role);
		String path = "/test/findall.do";
		return "redirect:" + path;
	}

	// 增加
	@RequestMapping("/add.do")
	public String add(String name, String gender, String grade, String blood) {

		Role role = new Role(name, gender, Integer.parseInt(grade),
				Integer.parseInt(blood));

		roleServiceImp.addRole(role);

		String path = "/test/findall.do";

		return "redirect:" + path;

	}
    //创建
	@RequestMapping("/addLoL.do")
	public String add() {
		return "add";

	}

	// 分页
	@RequestMapping("/pageType.do")
	public String limit(String flag, ModelMap map) {

		// 获取总条数
		int totalRows = roleServiceImp.findRoles();
		// 总页数
		int totalPages = (int) Math.ceil(totalRows / 3.0);// 向上取整

		int flag1 = Integer.parseInt(flag);

		System.out.println(totalPages);

		System.out.println(flag1);

		if (flag1 == 0) {// 首页
			pageNum = 1;
			List<Role> roles = roleServiceImp.findLimit(0, 3);
			map.addAttribute("roles", roles);
			return "adminall";

		} else if (flag1 == 1) {// 上一页

			if (pageNum == 1) {// 说明是首页
				List<Role> roles = roleServiceImp.findLimit(0, 3);
				map.addAttribute("roles", roles);
				return "adminall";

			} else {
				pageNum -= 1;
				List<Role> roles = roleServiceImp.findLimit((pageNum - 1) * 3,
						3);
				map.addAttribute("roles", roles);
				return "adminall";
			}

		} else if (flag1 == 2) {// 下一页
			if (pageNum == totalPages) {// 说明是尾页
				List<Role> roles = roleServiceImp.findLimit(
						(totalPages - 1) * 3, 3);
				map.addAttribute("roles", roles);
				return "adminall";

			} else {
				pageNum += 1;
				List<Role> roles = roleServiceImp.findLimit((pageNum - 1) * 3,
						3);
				map.addAttribute("roles", roles);
				return "adminall";
			}

		} else if (flag1 == 3) {// 尾页
			pageNum = totalPages;
			List<Role> roles = roleServiceImp
					.findLimit((totalPages - 1) * 3, 3);
			map.addAttribute("roles", roles);
			return "adminall";

		}
		return "adminall";
	}
}
