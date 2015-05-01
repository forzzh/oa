package cn.oa.dao.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Repository;

import cn.oa.base.BaseDaoImpl;
import cn.oa.dao.UserDao;
import cn.oa.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Override
	public User findByNameAndPassword(String loginName, String password) {
		String md5Digest = DigestUtils.md5Hex(password);
		 return (User) getSession().createQuery("from User where loginName = ? and password = ?")
		.setParameter(0, loginName)
		.setParameter(1, md5Digest).uniqueResult();
	}

}
