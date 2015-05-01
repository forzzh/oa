package cn.oa.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.oa.base.BaseAction;
import cn.oa.domain.Reply;
import cn.oa.domain.Topic;
import cn.oa.domain.User;
import cn.oa.service.ReplyService;

@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply> {
	private Long topicId;
	
	/**单个主题列表*/
	public String addUI() throws Exception {
		//准备数据
		Topic topic = topicService.findById(topicId);
		ActionContext.getContext().put("topic", topic);
		return "addUI";
	}

	/**单个主题列表*/
	public String add() throws Exception {
		//表单字段已经封装到了model里title,content
		User user = (User) ActionContext.getContext().getSession().get("user");
		model.setAuthor(user);
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		
		model.setTopic(topicService.findById(topicId));
		
		replyService.save(model);
		return "toTopicShow";
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

}
