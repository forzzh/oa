package cn.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.oa.dao.ReplyDao;
import cn.oa.domain.Forum;
import cn.oa.domain.PageBean;
import cn.oa.domain.Reply;
import cn.oa.domain.Topic;
import cn.oa.service.ReplyService;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {
	@Resource
	private ReplyDao replyDao;
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public List<Reply> findByTopic(Topic topic) {
		return replyDao.findByTopic(topic);
	}

	@Override
	public void save(Reply reply) {
		replyDao.save(reply);
		
		//维护其他属性
		Topic topic = reply.getTopic();
		Forum forum = topic.getForum();
		
		forum.setArticleCount(forum.getArticleCount() + 1);
		topic.setLastReply(reply);
		topic.setReplyCount(topic.getReplyCount() + 1);
		topic.setLastUpdateTime(reply.getPostTime());
		
		sessionFactory.getCurrentSession().update(forum);;
		sessionFactory.getCurrentSession().update(topic);;
	}

	@Override
	public PageBean findPageBean(int pageNum, int pageSize, Topic topic) {
		return replyDao.findPageBean(pageNum, pageSize, topic);
	}

}
