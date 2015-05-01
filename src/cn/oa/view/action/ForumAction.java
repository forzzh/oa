package cn.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.oa.base.BaseAction;
import cn.oa.domain.Forum;
import cn.oa.domain.PageBean;
import cn.oa.domain.Topic;

@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum>{
	
	/**板块列表*/
	public String list() throws Exception {
		List<Forum> forumList = forumManageService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}
	
	
	/**单个主题列表*/
	public String show() throws Exception {
		
		Forum forum = forumManageService.getById(model.getId());
		ActionContext.getContext().put("forum", forum);
		
		//准备数据
//		List<Topic> topicList = topicService.findByForum(forum);
//		ActionContext.getContext().put("topicList", topicList);
		
		//分页了啊
		PageBean pageBean = topicService.findPageBean(pageNum, pageSize, forum);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "show";
	}

}
