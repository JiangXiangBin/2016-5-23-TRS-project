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

	// ��ҳ��ѯ
	@RequestMapping("/findall.do")
	public String findall(ModelMap map, HttpServletRequest request) {

		HttpSession session=request.getSession();
		
		
		List<Role> roles = roleServiceImp.findLimit(0, 3);
	
		map.addAttribute("roles", roles);

		return "adminall";

	}

	// ɾ��
	@RequestMapping("/delete.do")
	
	public String delete(int id) {

		roleServiceImp.deleteById(id);

		System.out.println(id);

		String path = "/test/findall.do";

		return "redirect:" + path;

	}
	
	// ͨ��ID�޸�
	@RequestMapping("/findById.do")
	
	public String findbyid(int id, ModelMap map) {

		Role role = roleServiceImp.findById(id);

		map.addAttribute("role", role);

		return "update";
	}
	// �޸�
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

	// ����
	@RequestMapping("/add.do")
	public String add(String name, String gender, String grade, String blood) {

		Role role = new Role(name, gender, Integer.parseInt(grade),
				Integer.parseInt(blood));

		roleServiceImp.addRole(role);

		String path = "/test/findall.do";

		return "redirect:" + path;

	}
    //����
	@RequestMapping("/addLoL.do")
	public String add() {
		return "add";

	}

	// ��ҳ
	@RequestMapping("/pageType.do")
	public String limit(String flag, ModelMap map) {

		// ��ȡ������
		int totalRows = roleServiceImp.findRoles();
		// ��ҳ��
		int totalPages = (int) Math.ceil(totalRows / 3.0);// ����ȡ��

		int flag1 = Integer.parseInt(flag);

		System.out.println(totalPages);

		System.out.println(flag1);

		if (flag1 == 0) {// ��ҳ
			pageNum = 1;
			List<Role> roles = roleServiceImp.findLimit(0, 3);
			map.addAttribute("roles", roles);
			return "adminall";

		} else if (flag1 == 1) {// ��һҳ

			if (pageNum == 1) {// ˵������ҳ
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

		} else if (flag1 == 2) {// ��һҳ
			if (pageNum == totalPages) {// ˵����βҳ
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

		} else if (flag1 == 3) {// βҳ
			pageNum = totalPages;
			List<Role> roles = roleServiceImp
					.findLimit((totalPages - 1) * 3, 3);
			map.addAttribute("roles", roles);
			return "adminall";

		}
		return "adminall";
	}
}
