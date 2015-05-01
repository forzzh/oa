package cn.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.oa.base.BaseAction;
import cn.oa.domain.Department;
import cn.oa.domain.Role;
import cn.oa.domain.User;
import cn.oa.service.UserService;
import cn.oa.util.DepartmentUtil;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	@Autowired
	private UserService userService;
	
	private Long departmentId;
	
	private Long[] roleIds;
	
	public String list() throws Exception {
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);
		return "list";
	}
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}
	public String editUI() throws Exception {
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtil.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		
		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		if (user.getRoles() != null) {
			int index = 0;
			roleIds = new Long[user.getRoles().size()];
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}
		return "saveUI";
	}
	
	public String edit() throws Exception {
		//1.ȡ������
		User user = userService.getById(model.getId());
		
		//2.����Ҫ�޸ĵ�����
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		
		//���ò���
		user.setDepartment(departmentService.findById(departmentId));
		//���ø�λ
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));
		
		//3.���浽���ݿ�
		userService.update(user);
		
		return "toList";
	}
	
	public String addUI() throws Exception {
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtil.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "saveUI";
	}
	public String add() throws Exception {
		//���ò���
		model.setDepartment(departmentService.findById(departmentId));
		//���ø�λ
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		
		//����Ĭ������,����MD5ժҪ
		String md5Digest = DigestUtils.md5Hex("1234");
		model.setPassword(md5Digest);
		
		userService.save(model);
		return "toList";
	}

	/**
	 * ��ʼ������Ϊ1234
	 * @return
	 * @throws Exception
	 */
	public String initPassword() throws Exception {
		//1.ȡ������
		User user = userService.getById(model.getId());
		
		//2.����Ҫ�޸ĵ����ԣ�ʹ��MD5
		String md5Digest = DigestUtils.md5Hex("1234");
		user.setPassword(md5Digest);
		
		//3.����
		userService.update(user);
		return "toList";
	}
	
	/**��½ҳ��*/
	public String loginUI() throws Exception {
		return "loginUI";
	}
	
	/**��½*/
	public String login() throws Exception {
		User user = userService.findByNameAndPassword(model.getLoginName(), model.getPassword());
		if (user != null) {
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex";
		} else {
			addFieldError("login", "�û��������벻��ȷ");
			return "loginUI";
		}
	}
	
	/**ע��*/
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
	
	public Long[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}
	
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
}
