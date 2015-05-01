package cn.oa.service;

import java.util.List;

import cn.oa.domain.Role;

public interface RoleService {

	List<Role> findAll();

	void delete(Long id);

	void save(Role role);

	Role getById(Long id);

	void update(Role role);

	List<Role> getByIds(Long[] roleIds);

}
