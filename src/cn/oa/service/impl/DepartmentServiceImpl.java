package cn.oa.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.oa.dao.DepartmentDao;
import cn.oa.domain.Department;
import cn.oa.service.DepartmentService;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentDao Departmentdao;
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Department> findAll() {
		return Departmentdao.findAll();
	}

	@Override
	public void delete(Long id) {
		Departmentdao.delete(id);
	}

	@Override
	public void save(Department model) {
		Departmentdao.save(model);
	}

	@Override
	public Department findById(Long id) {
		return Departmentdao.findById(id);
	}

	@Override
	public void update(Department department) {
		Departmentdao.update(department);
	}

	@Override
	public List<Department> findTopList() {
		return sessionFactory.getCurrentSession().createQuery("from Department d where d.parent is null").list();
	}

	@Override
	public List<Department> findChildrenList(Long parentId) {
		return sessionFactory.getCurrentSession().createQuery(
				"from Department d where d.parent.id = ?").setParameter(0, parentId).list();
	}

}
