package cn.oa.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

//实现序列化，使得服务器重启时不用重新登录
public class User implements java.io.Serializable {
	private Long id;
	private Department department;
	private Set<Role> roles = new HashSet<Role>();
	
	private String loginName;
	private String password;
	private String name;	// 真实姓名
	private String gender;
	private String phoneNumber;
	private String email;
	private String description;
	
	/**
	 * 判断用户是否有某个权限
	 * @param name
	 * @return
	 */
	public boolean hasPrivilegesByName(String name) {
		if (isAdmin()) {
			return true;
		}
		//普通用户
		for (Role role : roles) {
			for (Privilege pri : role.getPrivileges()) {
				if (name.equals(pri.getName())) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 判断用户是否有某个权限
	 * @param url
	 * @return
	 */
	public boolean hasPrivilegesByUrl(String PrivUrl) {
		if (isAdmin()) {
			return true;
		}
		
		//去掉后面的参数
		int pos = PrivUrl.indexOf("?");
		if (pos > -1) {
			PrivUrl = PrivUrl.substring(0, pos);
		}
		//去掉UI后缀
		if (PrivUrl.endsWith("UI")) {
			PrivUrl = PrivUrl.substring(0, PrivUrl.length()-2);
		}
		
		//url是否需要控制
		List<String> privilegeUrls = (List<String>) ActionContext.getContext().getApplication().get("privilegeUrls");
		if(!privilegeUrls.contains(PrivUrl)) {
			return true;
		}
		
		//普通用户
		for (Role role : roles) {
			for (Privilege pri : role.getPrivileges()) {
				if (PrivUrl.equals(pri.getUrl())) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 判断是否是超级管理员
	 * @return
	 */
	public boolean isAdmin() {
		return "admin".equals(loginName);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
