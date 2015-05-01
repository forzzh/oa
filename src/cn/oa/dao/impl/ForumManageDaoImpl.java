package cn.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.oa.base.BaseDaoImpl;
import cn.oa.dao.ForumManageDao;
import cn.oa.domain.Forum;

@Repository
public class ForumManageDaoImpl extends BaseDaoImpl<Forum> implements  ForumManageDao{

	@Override
	public List<Forum> findAll() {
		return getSession().createQuery("from Forum order by position").list();
	}
	
	

	@Override
	public void save(Forum entity) {
		// TODO Auto-generated method stub
		super.save(entity);
		entity.setPosition(entity.getId().intValue());
	}



	@Override
	public void moveUp(Long id) {
		Forum forum = findById(id);
		
		Forum other = (Forum) getSession().createQuery("from Forum where position < ? order by position desc")
		.setParameter(0, forum.getPosition())
		.setFirstResult(0).setMaxResults(1).uniqueResult();
		
		if (other == null) {
			return;
		}
		
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		
		//在session中不用调用update了
	}

	@Override
	public void moveDown(Long id) {
		Forum forum = findById(id);
		
		Forum other = (Forum) getSession().createQuery("from Forum where position > ? order by position")
		.setParameter(0, forum.getPosition())
		.setFirstResult(0).setMaxResults(1).uniqueResult();
		
		if (other == null) {
			return;
		}
		
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
	}
	
}
