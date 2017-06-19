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
	//�����б?�洢����Ա�ļ���
	// Constructors
	
	
	private Set permissions = new HashSet();
	//�����б?�洢��ɫ��ӵ�е�Ȩ�޼���
	

	public Set getPermissions() {
		return permissions;
	}

	public void setPermissions(Set permissions) {
		this.permissions = permissions;
	}

	/** default constructor */
	public Role() {
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