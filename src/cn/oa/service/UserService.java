package cn.oa.service;

import java.util.List;

import cn.oa.domain.User;

public interface UserService {

	List<User> findAll();

	void delete(Long id);
	
	void save(User user);

	User getById(Long id);

	void update(User user);

	/**
	 * �����û������������
	 * @param loginName
	 * @param password
	 * @return
	 */
	User findByNameAndPassword(String loginName, String password);

}
