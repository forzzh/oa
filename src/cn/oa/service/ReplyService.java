package cn.oa.service;

import java.util.List;

import cn.oa.domain.PageBean;
import cn.oa.domain.Reply;
import cn.oa.domain.Topic;

public interface ReplyService {

	/**
	 * 根据主题查出回复
	 * @param topic
	 * @return
	 */
	List<Reply> findByTopic(Topic topic);

	void save(Reply model);

	/**
	 * 回复分页信息
	 * @param pageNum
	 * @param pageSize
	 * @param topic
	 * @return
	 */
	PageBean findPageBean(int pageNum, int pageSize, Topic topic);

}
