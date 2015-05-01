package cn.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.oa.base.BaseDaoImpl;
import cn.oa.dao.PrivilegeDao;
import cn.oa.domain.Privilege;

@Repository
@SuppressWarnings("unchecked")
public class PrivilegeDaoImpl extends BaseDaoImpl<Privilege> implements PrivilegeDao{

	@Override
	public List<Privilege> getTopList() {
		return getSession().createQuery("from Privilege where parent is null").list();
	}

	
	@Override
	public List<String> getPrivilegeUrls() {
		return getSession().createQuery("select distinct url from Privilege where url is not null").list();
	}

}
