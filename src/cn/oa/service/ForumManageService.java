package cn.oa.service;

import java.util.List;

import cn.oa.domain.Forum;

public interface ForumManageService {

	List<Forum> findAll();

	void save(Forum model);

	void moveUp(Long id);

	void moveDown(Long id);

	void delete(Long id);

	Forum getById(Long id);

	void update(Forum forum);

}
