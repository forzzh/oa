package cn.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.oa.base.BaseDaoImpl;
import cn.oa.dao.TopicDao;
import cn.oa.domain.Forum;
import cn.oa.domain.PageBean;
import cn.oa.domain.Reply;
import cn.oa.domain.Topic;

@Repository
public class TopicDaoImpl extends BaseDaoImpl<Topic> implements TopicDao {

	@Override
	public List<Topic> findByForum(Forum forum) {
		//                              "FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC"                    
		return (List<Topic>) getSession().createQuery("from Topic where forum = ? order by (case type when 2 then 2 else 0 end) desc, "
				+ "lastUpdateTime desc")
				.setParameter(0, forum)
				.list();
	}

	@Override
	public PageBean findPageBean(int pageNum, int pageSize, Forum forum) {
		//≤È—Ø¡–±Ì
		List<Reply> replyList =  getSession().createQuery("from Topic where forum = ? order by (case type when 2 then 2 else 0 end) desc, "
				+ "lastUpdateTime desc")
				.setParameter(0, forum)
				.setFirstResult((pageNum-1)*pageSize)
				.setMaxResults(pageSize)
				.list();
		
		Long count = (Long) getSession().createQuery("select count(*) from Topic where forum = ?")
		.setParameter(0, forum)
		.uniqueResult();
		
		return new PageBean(pageNum, pageSize, count.intValue(), replyList);
	}

}
