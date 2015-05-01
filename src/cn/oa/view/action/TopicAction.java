package cn.oa.view.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.oa.base.BaseAction;
import cn.oa.domain.Forum;
import cn.oa.domain.PageBean;
import cn.oa.domain.Reply;
import cn.oa.domain.Topic;
import cn.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic>{
	
	private Long forumId;
	
	/**单个主题列表(主题加回帖)*/
	public String show() throws Exception {
		Topic topic = topicService.findById(model.getId());
		ActionContext.getContext().put("topic", topic);
		
		//准备回复数据
//		List<Reply> replyList = replyService.findByTopic(topic);
//		ActionContext.getContext().put("replyList", replyList);
		
		//分页
		PageBean pageBean = replyService.findPageBean(pageNum, pageSize, topic);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "show";
	}
	
	/**单个主题列表*/
	public String addUI() throws Exception {
		//单个数据
		Forum forum = forumManageService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "addUI";
	}

	/**单个主题列表*/
	public String add() throws Exception {
		//封装
//		Topic model = new Topic();
//		model.setContent(content);
//		model.setTitle(title);
		model.setForum(forumManageService.getById(forumId));
		
		User user = (User) ActionContext.getContext().getSession().get("user");
		model.setAuthor(user);
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		
		topicService.save(model);
		return "toShow";
	}

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
	
}
