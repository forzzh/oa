package cn.oa.service;

import java.util.List;

import cn.oa.domain.Department;

public interface DepartmentService {

	List<Department> findAll();

	void delete(Long id);

	void save(Department model);

	Department findById(Long id);

	void update(Department department);

	List<Department> findTopList();

	List<Department> findChildrenList(Long parentId);

}
