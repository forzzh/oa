package cn.oa.service;

import java.util.List;

import cn.oa.domain.Privilege;

public interface PrivilegeService {

	List<Privilege> getByIds(Long[] privilegeIds);

	List<Privilege> findAll();

	List<Privilege> getTopList();

	List<String> getPrivilegeUrls();

}
