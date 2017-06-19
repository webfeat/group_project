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
	//������ɫ�б����ڴ洢Ȩ�޷������Щ��ɫ��
	private Set modules = new HashSet();
	//����ģ������ڴ洢Ȩ�޷������Щģ�飻
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