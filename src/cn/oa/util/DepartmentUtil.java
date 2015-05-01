package cn.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.oa.domain.Department;

public class DepartmentUtil {

	public static List<Department> getAllDepartments(List<Department> topList) {
		List<Department> list = new ArrayList<Department>();
		travesingTreeList(topList, list, "��");
		return list;
	}
	//Collection��set�������
	private static void travesingTreeList(Collection<Department> topList,
			List<Department> list, String prefix) {
		
		for (Department top : topList) {
			
			//ʹ�ø�����ԭ������session��
			Department copy = new Department();
			copy.setId(top.getId());
			copy.setName(prefix+top.getName());
			
			list.add(copy);
			//������ʹ��ȫ�ǿո�
			travesingTreeList(top.getChildren(), list, "��"+prefix);
		}
		
	}

}
