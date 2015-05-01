package cn.oa.dao;

import java.util.List;

import cn.oa.base.BaseDao;
import cn.oa.domain.Privilege;

public interface PrivilegeDao extends BaseDao<Privilege> {

	List<Privilege> getTopList();

	List<String> getPrivilegeUrls();

}
