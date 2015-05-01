package cn.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.oa.base.BaseAction;
import cn.oa.domain.Department;
import cn.oa.util.DepartmentUtil;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {
	
	private Long parentId;
	
	public String list() throws Exception {
		List<Department> departmentList;
		if (parentId == null) {
			departmentList = departmentService.findTopList();
		} else {
			//子列表
			departmentList = departmentService.findChildrenList(parentId);
			//传入parent便于返回上一级
			Department parent = departmentService.findById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
//		departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}
	
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}
	
	public String editUI() throws Exception {
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		//回显数据
		Department department = departmentService.findById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		
		if (department.getParent() != null) {
			parentId = department.getParent().getId();
		}
		return "saveUI";
	}
	
	public String edit() throws Exception {
		Department department = departmentService.findById(model.getId());
		department.setName(model.getName());
		department.setDescription(model .getDescription());
		
		//关联上级
		department.setParent(departmentService.findById(parentId));
		
		departmentService.update(department);
		return "toList";
	}
	
	public String addUI() throws Exception {
//		List<Department> departmentList = departmentService.findAll();
		
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtil.getAllDepartments(topList);
		 
		
		ActionContext.getContext().put("departmentList", departmentList);
		return "saveUI";
	}
	
	public String add() throws Exception {
		
		Department parent = departmentService.findById(parentId);
		model.setParent(parent);
		
		departmentService.save(model);
		return "toList";
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
