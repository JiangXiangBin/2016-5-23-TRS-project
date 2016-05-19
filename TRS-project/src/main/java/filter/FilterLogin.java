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

		System.out.println("��������ʼ����...");

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/** ����Http Э���Servlet */
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		/** ����Http Э���Servlet */
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		// ��ȡsession
		HttpSession session = httpServletRequest.getSession();

		System.out.println("�� ʼ �� ��....");
		// httpServletRequest.getServletPath()�õ����ǲ�����/*.jsp�ļ�
		if ((httpServletRequest.getServletPath().indexOf("login.jsp") > 0

		|| httpServletRequest.getServletPath().indexOf("trs1/test/login.do") > 0)
				|| (httpServletRequest.getServletPath().indexOf("register.jsp") > 0 || httpServletRequest
						.getServletPath().indexOf("trs1/test/register.do") > 0)) {

			System.out.println("�Ź�������...");

			System.out.println(httpServletRequest.getServletPath().indexOf(
					"login.jsp"));

			chain.doFilter(request, response);

		} else {

			// ��ȡcontroller������session�󶨹�����ֵ

			String user = (String) session.getAttribute("loginsession");

			if (user != null) {

				System.out.println("�ѵ�¼��ȡ������...");

				chain.doFilter(request, response);

			} else {

				System.out.println("δ��¼����ת����¼ҳ��...");
				// δ��¼�ض���login.jspҳ��
				httpServletResponse.sendRedirect(httpServletRequest

				.getContextPath() + "/login.jsp");
			}
		}

		System.out.println("��ɹ���....");
	}

	public void destroy() {

		System.out.println("��������������...");
	}

}
