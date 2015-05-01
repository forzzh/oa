package cn.oa.service;

import java.util.List;

import cn.oa.domain.PageBean;
import cn.oa.domain.Reply;
import cn.oa.domain.Topic;

public interface ReplyService {

	/**
	 * �����������ظ�
	 * @param topic
	 * @return
	 */
	List<Reply> findByTopic(Topic topic);

	void save(Reply model);

	/**
	 * �ظ���ҳ��Ϣ
	 * @param pageNum
	 * @param pageSize
	 * @param topic
	 * @return
	 */
	PageBean findPageBean(int pageNum, int pageSize, Topic topic);

}
