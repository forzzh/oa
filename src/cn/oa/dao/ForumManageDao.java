package cn.oa.dao;

import cn.oa.base.BaseDao;
import cn.oa.domain.Forum;
import cn.oa.domain.Role;

public interface ForumManageDao extends BaseDao<Forum> {

	void moveUp(Long id);

	void moveDown(Long id);

}
