package dao.moduleDao;

import java.util.Set;

/**
 * Module entity. @author MyEclipse Persistence Tools
 */

public class Module implements java.io.Serializable {

	// Fields

	private Integer moduleid;
	private String modulename;
	private Integer supmoduleid;
	private Integer submodelid;
	private String methodname;
	
	private Set permissions;
	// Constructors

	public Set getPermissions() {
		return permissions;
	}

	public void setPermissions(Set permissions) {
		this.permissions = permissions;
	}

	/** default constructor */
	public Module() {
	}

	/** minimal constructor */
	public Module(Integer moduleid, Integer supmoduleid, Integer submodelid) {
		this.moduleid = moduleid;
		this.supmoduleid = supmoduleid;
		this.submodelid = submodelid;
	}

	/** full constructor */
	public Module(Integer moduleid, String modulename, Integer supmoduleid,
			Integer submodelid, String methodname) {
		this.moduleid = moduleid;
		this.modulename = modulename;
		this.supmoduleid = supmoduleid;
		this.submodelid = submodelid;
		this.methodname = methodname;
	}

	// Property accessors

	public Integer getModuleid() {
		return this.moduleid;
	}

	public void setModuleid(Integer moduleid) {
		this.moduleid = moduleid;
	}

	public String getModulename() {
		return this.modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public Integer getSupmoduleid() {
		return this.supmoduleid;
	}

	public void setSupmoduleid(Integer supmoduleid) {
		this.supmoduleid = supmoduleid;
	}

	public Integer getSubmodelid() {
		return this.submodelid;
	}

	public void setSubmodelid(Integer submodelid) {
		this.submodelid = submodelid;
	}

	public String getMethodname() {
		return this.methodname;
	}

	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}

}