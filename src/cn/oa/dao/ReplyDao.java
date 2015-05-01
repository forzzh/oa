package cn.oa.dao;

import java.util.List;

import cn.oa.base.BaseDao;
import cn.oa.domain.PageBean;
import cn.oa.domain.Reply;
import cn.oa.domain.Topic;

public interface ReplyDao extends BaseDao<Reply> {

	List<Reply> findByTopic(Topic topic);

	PageBean findPageBean(int pageNum, int pageSize, Topic topic);

}
