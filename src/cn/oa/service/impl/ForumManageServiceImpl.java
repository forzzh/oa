package cn.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.oa.dao.ForumManageDao;
import cn.oa.domain.Forum;
import cn.oa.service.ForumManageService;

@Service
@Transactional
public class ForumManageServiceImpl implements ForumManageService {
	@Resource
	private ForumManageDao forumManagedao;

	@Override
	public void moveUp(Long id) {
		forumManagedao.moveUp(id);
	}
	
	@Override
	public void moveDown(Long id) {
		forumManagedao.moveDown(id);
	}
	
	@Override
	public List<Forum> findAll() {
		return forumManagedao.findAll();
	}

	@Override
	public void save(Forum model) {
		forumManagedao.save(model);
	}


	@Override
	public void delete(Long id) {
		forumManagedao.delete(id);
	}

	@Override
	public Forum getById(Long id) {
		return forumManagedao.findById(id);
	}

	@Override
	public void update(Forum forum) {
		forumManagedao.update(forum);
	}

}
