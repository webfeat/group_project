package dao.permissionDao;

import java.util.HashSet;
import java.util.Set;

/**
 * Permission entity. @author MyEclipse Persistence Tools
 */

public class Permission implements java.io.Serializable {

	// Fields

	private Integer permissionid;
	private String permissionname;
	private Set roles = new HashSet();
	//建立角色列表，用于存储权限分配给哪些角色；
	private Set modules = new HashSet();
	//建立模块表，用于存储权限分配给哪些模块；
	// Constructors

	public Set getModules() {
		return modules;
	}

	public void setModules(Set modules) {
		this.modules = modules;
	}

	/** default constructor */
	public Permission() {
	}

	public Set getRoles() {
		return roles;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}

	/** full constructor */
	public Permission(Integer permissionid, String permissionname) {
		this.permissionid = permissionid;
		this.permissionname = permissionname;
	}

	// Property accessors

	public Integer getPermissionid() {
		return this.permissionid;
	}

	public void setPermissionid(Integer permissionid) {
		this.permissionid = permissionid;
	}

	public String getPermissionname() {
		return this.permissionname;
	}

	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}

}