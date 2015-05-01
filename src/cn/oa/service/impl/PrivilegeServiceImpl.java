package cn.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.oa.dao.PrivilegeDao;
import cn.oa.domain.Privilege;
import cn.oa.service.PrivilegeService;

@Service
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {
	@Resource
	private PrivilegeDao privilegeDao;

	@Override
	public List<Privilege> getByIds(Long[] privilegeIds) {
		return privilegeDao.findByIds(privilegeIds);
	}

	@Override
	public List<Privilege> findAll() {
		return privilegeDao.findAll();
	}

	@Override
	public List<Privilege> getTopList() {
		return privilegeDao.getTopList();
	}

	@Override
	public List<String> getPrivilegeUrls() {
		return privilegeDao.getPrivilegeUrls();
	}

}
