package dao.roleDao;

import java.util.HashSet;
import java.util.Set;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable {

	// Fields

	private Integer roleid;
	private String rolename;
	private Set operators = new HashSet();
	//建立列表，存储操作员的集合
	// Constructors
	
	
	private Set permissions = new HashSet();
	//建立列表，存储角色所拥有的权限集合
	

	public Set getPermissions() {
		return permissions;
	}

	public void setPermissions(Set permissions) {
		this.permissions = permissions;
	}

	/** default constructor */
	public Role() {
	}

	public Set getOperators() {
		return operators;
	}

	public void setOperators(Set operators) {
		this.operators = operators;
	}

	/** minimal constructor */
	public Role(Integer roleid) {
		this.roleid = roleid;
	}

	/** full constructor */
	public Role(Integer roleid, String rolename) {
		this.roleid = roleid;
		this.rolename = rolename;
	}

	// Property accessors

	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}