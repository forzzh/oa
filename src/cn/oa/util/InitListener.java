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
//  InitListener����ʹ��tomcat�Լ��ģ��������������
//	@Resource
//	private PrivilegeService privilegeService;
	
    public void contextDestroyed(ServletContextEvent sce)  {
    	
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	//���������еõ�����
    	ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeServiceImpl");
		//���������ʱ��õ��ģ����ᾭ����������������������
		List<Privilege> topPrivilegeList = privilegeService.getTopList();
		sce.getServletContext().setAttribute("topPrivilegeList", topPrivilegeList);
		System.out.println("----------����׼��---------");

		//׼�����е�url
		List<String> privilegeUrls = privilegeService.getPrivilegeUrls();
		sce.getServletContext().setAttribute("privilegeUrls", privilegeUrls);
		System.out.println("----------url׼��---------");
    }
	
}
