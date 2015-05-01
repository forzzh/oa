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
//		System.out.println("之前");
//		//放行
//		String result = invocation.invoke();
//		System.out.println("之后");
//		return result;
		//获取用户信息
		User user = (User) ActionContext.getContext().getSession().get("user");
		//获取url
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String PrivUrl = namespace + actionName;
		//如果用户未登录
		if (user == null) {
			//正在登录就放行/user_login,/user_loginUI
			if (PrivUrl.startsWith("/user_login")) {
				return invocation.invoke();
			}
			return "loginUI";
		}
		//用户是否有权限
		if (user.hasPrivilegesByUrl(PrivUrl)) {
			return invocation.invoke();
		} else {
			return "noPrivilegeError";
		}
	}


}
