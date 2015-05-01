package cn.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.oa.base.BaseAction;
import cn.oa.domain.Privilege;
import cn.oa.domain.Role;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
	
	
//	private Long id;
//	private String name;
//	private String description;
	private Long[] privilegeIds;

	/**
	 * �б�
	 * @return
	 */
	public String list() {
		List<Role> roleList = roleService.findAll();
		//�浽ֵջ��
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}
	
	/**
	 * ɾ��
	 * @return
	 */
	public String delete() {
		roleService.delete(model.getId());
		return "toList";
	}
	
	/**
	 * ���
	 * @return
	 */
	public String add() {
//		Role role = new Role();
//		role.setDescription(description);
//		role.setName(name);
		
		roleService.save(model);
		return "toList";
	}
	
	/**
	 * ���ҳ��
	 * @return
	 */
	public String addUI() {
		return "addUI";
	}
	
	/**
	 * �༭ҳ��
	 * @return
	 */
	public String editUI() {
		//�ڻ�������
		Role role = roleService.getById(model.getId());
		//���������ջ��
		ActionContext.getContext().getValueStack().push(role);
//		this.id = role.getId();
//		this.description = role.getDescription();
//		this.name = role.getName();
		return "editUI";
	}
	
	/**
	 * �༭
	 * @return
	 */
	public String edit() {
		Role role = roleService.getById(model.getId());
		role.setDescription(model.getDescription());
		role.setName(model.getName());
		
		roleService.update(role);
		return "toList";
	}
	
	/**����Ȩ��ҳ��*/
	public String privilegeUI() {
		//�ڻ�������
		Role role = roleService.getById(model.getId());
		//���������ջ��
		ActionContext.getContext().getValueStack().push(role);

		if (role.getPrivileges() != null) {
			privilegeIds = new Long[role.getPrivileges().size()];
			int index = 0;
			for (Privilege privilege : role.getPrivileges()) {
				privilegeIds[index++] = privilege.getId(); 
			}
		}
		
		//׼������privilegeList
		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList);
		
		return "privilegeUI";
	}
	
	/**����Ȩ��*/
	public String setprivilege() {
		
		Role role = roleService.getById(model.getId());

		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));
		
		roleService.update(role);
		return "toList";
	}

	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}

}
