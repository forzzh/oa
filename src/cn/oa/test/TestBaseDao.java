package cn.oa.test;

import org.junit.Test;

import cn.oa.dao.RoleDao;
import cn.oa.dao.UserDao;
import cn.oa.dao.impl.RoleDaoImpl;
import cn.oa.dao.impl.UserDaoImpl;

public class TestBaseDao {
	
	@Test
	public void testD() {
		UserDao ud = new UserDaoImpl();
		RoleDao roleDao = new RoleDaoImpl();
	}
}
