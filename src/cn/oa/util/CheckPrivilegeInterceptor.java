package cn.oa.util;

import cn.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
//		System.out.println("֮ǰ");
//		//����
//		String result = invocation.invoke();
//		System.out.println("֮��");
//		return result;
		//��ȡ�û���Ϣ
		User user = (User) ActionContext.getContext().getSession().get("user");
		//��ȡurl
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String PrivUrl = namespace + actionName;
		//����û�δ��¼
		if (user == null) {
			//���ڵ�¼�ͷ���/user_login,/user_loginUI
			if (PrivUrl.startsWith("/user_login")) {
				return invocation.invoke();
			}
			return "loginUI";
		}
		//�û��Ƿ���Ȩ��
		if (user.hasPrivilegesByUrl(PrivUrl)) {
			return invocation.invoke();
		} else {
			return "noPrivilegeError";
		}
	}


}
