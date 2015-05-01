package cn.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.oa.base.BaseDaoImpl;
import cn.oa.dao.ReplyDao;
import cn.oa.domain.PageBean;
import cn.oa.domain.Reply;
import cn.oa.domain.Topic;

@SuppressWarnings("unchecked")
@Repository
public class ReplyDaoImpl extends BaseDaoImpl<Reply> implements ReplyDao{

	@Override
	public List<Reply> findByTopic(Topic topic) {
		return (List<Reply>) getSession().createQuery("from Reply where topic = ? order by postTime")
				.setParameter(0, topic).list();
	}

	@Override
	public PageBean findPageBean(int pageNum, int pageSize, Topic topic) {
		//≤È—Ø¡–±Ì
		List<Reply> replyList =  getSession().createQuery("from Reply where topic = ? order by postTime")
				.setParameter(0, topic)
				.setFirstResult((pageNum-1)*pageSize)
				.setMaxResults(pageSize)
				.list();
		
		Long count = (Long) getSession().createQuery("select count(*) from Reply where topic = ?")
		.setParameter(0, topic)
		.uniqueResult();
		
		return new PageBean(pageNum, pageSize, count.intValue(), replyList);
	}

}
