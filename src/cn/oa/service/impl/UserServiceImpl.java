package cn.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.oa.dao.UserDao;
import cn.oa.domain.User;
import cn.oa.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userdao;

	@Override
	public List<User> findAll() {
		return userdao.findAll();
	}

	@Override
	public void delete(Long id) {
		userdao.delete(id);
	}

	@Override
	public void save(User user) {
		userdao.save(user);
	}

	@Override
	public User getById(Long id) {
		return userdao.findById(id);
	}

	@Override
	public void update(User user) {
		userdao.update(user);
	}

	@Override
	public User findByNameAndPassword(String loginName, String password) {
		return userdao.findByNameAndPassword(loginName, password);
	}
}
