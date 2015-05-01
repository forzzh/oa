package cn.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.oa.dao.RoleDao;
import cn.oa.domain.Role;
import cn.oa.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Resource
	private RoleDao roleDao;

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public void delete(Long id) {
		roleDao.delete(id);
	}

	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

	@Override
	public Role getById(Long id) {
		return roleDao.findById(id);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public List<Role> getByIds(Long[] roleIds) {
		return roleDao.findByIds(roleIds);
	}

}
