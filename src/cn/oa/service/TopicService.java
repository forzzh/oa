package cn.oa.service;

import java.util.List;

import cn.oa.domain.Forum;
import cn.oa.domain.PageBean;
import cn.oa.domain.Topic;

public interface TopicService {

	List<Topic> findByForum(Forum forum);

	void save(Topic model);

	Topic findById(Long id);

	PageBean findPageBean(int pageNum, int pageSize, Forum forum);

}
