package cn.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.oa.service.DepartmentService;
import cn.oa.service.ForumManageService;
import cn.oa.service.PrivilegeService;
import cn.oa.service.ReplyService;
import cn.oa.service.RoleService;
import cn.oa.service.TopicService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 公共抽取action,不需要被实例化
 * @author zzh
 *
 * @param <T>
 */

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	protected T model;

	public BaseAction() {
		ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
		Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
		try {
			model = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public T getModel() {
		return model;
	}
	
	//注入service
	@Resource
	protected RoleService roleService;
	
	@Resource
	protected DepartmentService departmentService;
	
	@Resource
	protected PrivilegeService privilegeService;
	
	@Resource
	protected ForumManageService forumManageService;
	@Resource
	protected TopicService topicService;
	@Resource
	protected ReplyService replyService;
	
	//分页用的信息
	protected int pageNum = 1;//第几页
	protected int pageSize = 10; //每页多少条记录

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
