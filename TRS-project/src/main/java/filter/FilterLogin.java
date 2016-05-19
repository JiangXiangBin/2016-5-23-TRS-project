package filter;

import java.io.IOException;
import javax.persistence.Entity;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Entity
public class FilterLogin implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

		System.out.println("过滤器初始化中...");

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/** 基于Http 协议的Servlet */
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		/** 基于Http 协议的Servlet */
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		// 获取session
		HttpSession session = httpServletRequest.getSession();

		System.out.println("开 始 过 滤....");
		// httpServletRequest.getServletPath()得到的是部署名/*.jsp文件
		if ((httpServletRequest.getServletPath().indexOf("login.jsp") > 0

		|| httpServletRequest.getServletPath().indexOf("trs1/test/login.do") > 0)
				|| (httpServletRequest.getServletPath().indexOf("register.jsp") > 0 || httpServletRequest
						.getServletPath().indexOf("trs1/test/register.do") > 0)) {

			System.out.println("放过该请求...");

			System.out.println(httpServletRequest.getServletPath().indexOf(
					"login.jsp"));

			chain.doFilter(request, response);

		} else {

			// 获取controller类里面session绑定过来的值

			String user = (String) session.getAttribute("loginsession");

			if (user != null) {

				System.out.println("已登录，取消过滤...");

				chain.doFilter(request, response);

			} else {

				System.out.println("未登录，跳转到登录页面...");
				// 未登录重定向到login.jsp页面
				httpServletResponse.sendRedirect(httpServletRequest

				.getContextPath() + "/login.jsp");
			}
		}

		System.out.println("完成过滤....");
	}

	public void destroy() {

		System.out.println("过滤器正在销毁...");
	}

}
