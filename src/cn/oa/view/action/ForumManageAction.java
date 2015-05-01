package cn.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.oa.base.BaseAction;
import cn.oa.domain.Forum;
import cn.oa.service.ForumManageService;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ForumManageAction extends BaseAction<Forum> {
	
	private static final long serialVersionUID = -8231243378141662249L;
	
	public String list() throws Exception {
		List<Forum> forumList = forumManageService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}
	
	public String delete() throws Exception {
		forumManageService.delete(model.getId());
		return "toList";
	}
	
	public String editUI() throws Exception {
		//根据id从数据库中查出
		Forum forum = forumManageService.getById(model.getId());
		
		ActionContext.getContext().getValueStack().push(forum);
		return "saveUI";
	}
	
	public String edit() throws Exception {
		Forum forum = forumManageService.getById(model.getId());
		
		//设置属性
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());
		
		forumManageService.update(forum);
		return "toList";
	}
	
	public String addUI() throws Exception {
		return "saveUI";
	}
	
	public String add() throws Exception {
		forumManageService.save(model);
		return "toList";
	}
	
	/**上移*/
	public String moveUp() throws Exception {
		forumManageService.moveUp(model.getId());
		return "toList";
	}
	/**下移*/
	public String moveDown() throws Exception {
		forumManageService.moveDown(model.getId());
		return "toList";
	}
}
