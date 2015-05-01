package cn.oa.dao;

import java.util.List;

import cn.oa.base.BaseDao;
import cn.oa.domain.Forum;
import cn.oa.domain.PageBean;
import cn.oa.domain.Topic;

public interface TopicDao extends BaseDao<Topic> {

	List<Topic> findByForum(Forum forum);

	PageBean findPageBean(int pageNum, int pageSize, Forum forum);


}
