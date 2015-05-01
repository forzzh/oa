package cn.oa.dao;

import cn.oa.base.BaseDao;
import cn.oa.domain.User;

public interface UserDao extends BaseDao<User> {

	User findByNameAndPassword(String loginName, String password);

}
