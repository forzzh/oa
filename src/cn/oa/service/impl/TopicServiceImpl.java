package cn.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.oa.dao.TopicDao;
import cn.oa.domain.Forum;
import cn.oa.domain.PageBean;
import cn.oa.domain.Topic;
import cn.oa.service.TopicService;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {
	@Resource
	private TopicDao topicDao;
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public List<Topic> findByForum(Forum forum) {
		return topicDao.findByForum(forum);
	}

	@Override
	public void save(Topic topic) {
		topic.setLastReply(null);
		topic.setLastUpdateTime(topic.getPostTime());
		topic.setReplyCount(0);
		topic.setType(Topic.TYPE_NORMAL);
		
		topicDao.save(topic);
		
		//Œ¨ª§Ãÿ ‚ Ù–‘
		Forum forum = topic.getForum();
		forum.setTopicCount(forum.getTopicCount() + 1);
		forum.setArticleCount(forum.getArticleCount() + 1);
		forum.setLastTopic(topic);
		sessionFactory.getCurrentSession().update(forum);
	}

	@Override
	public Topic findById(Long id) {
		return topicDao.findById(id);
	}

	@Override
	public PageBean findPageBean(int pageNum, int pageSize, Forum forum) {
		return topicDao.findPageBean(pageNum, pageSize, forum);
	}

}
