package loginInterceptor;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Entity
public class SecurityInterceptor implements HandlerInterceptor {
	 /** 
     * �÷���Ҳ����Ҫ��ǰ��Ӧ��Interceptor��preHandle�����ķ���ֵΪtrueʱ�Ż�ִ�С�
     * �÷������������������֮��
     * Ҳ����DispatcherServlet��Ⱦ����ͼִ�У� 
     * �����������Ҫ����������������Դ�ģ�
     * ��Ȼ�������Ҳֻ���ڵ�ǰ���Interceptor��preHandle�����ķ���ֵΪtrueʱ�Ż�ִ�С� 
     */  
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}
    /** 
     * �������ֻ���ڵ�ǰ���Interceptor��preHandle��������ֵΪtrue��ʱ��Ż�ִ�С�postHandle�ǽ��д����������õģ�����ִ��ʱ�����ڴ��������д���֮ 
     * ��Ҳ������Controller�ķ�������֮��ִ�У�����������DispatcherServlet������ͼ����Ⱦ֮ǰִ�У�Ҳ����˵���������������Զ�ModelAndView���в� 
     * ���������������ʽ�ṹ���������ʵķ������෴�ģ�Ҳ����˵��������Interceptor�������÷������������ã����Struts2�������������ִ�й����е��� 
     * ֻ��Struts2�����intercept������Ҫ�ֶ��ĵ���ActionInvocation��invoke������Struts2�е���ActionInvocation��invoke�������ǵ�����һ��Interceptor 
     * �����ǵ���action��Ȼ��Ҫ��Interceptor֮ǰ���õ����ݶ�д�ڵ���invoke֮ǰ��Ҫ��Interceptor֮����õ����ݶ�д�ڵ���invoke����֮�� 
     */ 
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * preHandle�����ǽ��д����������õģ� �÷�������Controller����֮ǰ���е��ã�
	 * SpringMVC�е�Interceptor����������ʽ�ģ� ����ͬʱ���ڶ��Interceptor��
	 * Ȼ��SpringMVC�����������ǰ��˳��һ����һ����ִ�У� �������е�Interceptor�е�preHandle����������
	 * Controller��������֮ǰ���á� SpringMVC������Interceptor��ʽ�ṹҲ�ǿ��Խ����жϵģ�
	 * �����жϷ�ʽ����preHandle�ķ� ��ֵΪfalse�� ��preHandle�ķ���ֵΪfalse��ʱ����������ͽ����ˡ�
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub

		// �������·����u r i
		String uri = request.getRequestURI();

		System.out.println("Ŀǰ��URI��ַ�ǣ�" + uri);

		HttpSession session = request.getSession();
		String str = (String) session.getAttribute("loginsessionone");
		System.out.println("session�󶨵�ֵ�ǣ�" + str);
		System.out.println("request.getsession()��ֵ�ǣ�" + session);

		// �ж�session���Ƿ���key���еĻ������û��Ĳ���
		if (request.getSession() != null && str != null) {

			return true;
		}
		System.out.println("���Ѿ���������" + uri);
		// �ض��򵽵�¼ҳ��
		response.sendRedirect(request.getContextPath() + "/login.jsp");

		System.out.println("�������.....");
		
		return false;

	}

}