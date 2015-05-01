package cn.oa.util;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.oa.domain.Privilege;
import cn.oa.service.PrivilegeService;

/**
 * 
 *
 */
public class InitListener implements ServletContextListener {
//  InitListener将会使用tomcat自己的，而不是容器里的
//	@Resource
//	private PrivilegeService privilegeService;
	
    public void contextDestroyed(ServletContextEvent sce)  {
    	
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	//从作用域中得到对象
    	ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeServiceImpl");
		//不是请求的时候得到的，不会经过过滤器，将会有懒加载
		List<Privilege> topPrivilegeList = privilegeService.getTopList();
		sce.getServletContext().setAttribute("topPrivilegeList", topPrivilegeList);
		System.out.println("----------数据准备---------");

		//准备所有的url
		List<String> privilegeUrls = privilegeService.getPrivilegeUrls();
		sce.getServletContext().setAttribute("privilegeUrls", privilegeUrls);
		System.out.println("----------url准备---------");
    }
	
}
