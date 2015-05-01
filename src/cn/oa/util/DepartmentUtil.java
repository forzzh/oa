package cn.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.oa.domain.Department;

public class DepartmentUtil {

	public static List<Department> getAllDepartments(List<Department> topList) {
		List<Department> list = new ArrayList<Department>();
		travesingTreeList(topList, list, "┣");
		return list;
	}
	//Collection中set是无序的
	private static void travesingTreeList(Collection<Department> topList,
			List<Department> list, String prefix) {
		
		for (Department top : topList) {
			
			//使用副本，原对象在session中
			Department copy = new Department();
			copy.setId(top.getId());
			copy.setName(prefix+top.getName());
			
			list.add(copy);
			//子树，使用全角空格
			travesingTreeList(top.getChildren(), list, "　"+prefix);
		}
		
	}

}
