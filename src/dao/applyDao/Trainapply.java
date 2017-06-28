package dao.applyDao;

import java.util.Date;

/**
 * Trainapply entity. @author MyEclipse Persistence Tools
 */

public class Trainapply implements java.io.Serializable {

	// Fields

	private Integer trainapplyid;
	private Date applytime;
	private String applicant;
	private String applystate;
	private String trainname;
	private String managename;
	private String managephone;
	private Long trainid;
	private String loginname;
	private String password;
	// Constructors
	private String email;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/** default constructor */
	public Trainapply() {
	}

	/** minimal constructor */
	public Trainapply(Integer trainapplyid) {
		this.trainapplyid = trainapplyid;
	}

	/** full constructor */
	public Trainapply(Integer trainapplyid, Date applytime, String applicant,
			String applystate, String trainname, String managename,
			String managephone, Long trainid,String loginname,String password) {
		this.trainapplyid = trainapplyid;
		this.applytime = applytime;
		this.applicant = applicant;
		this.applystate = applystate;
		this.trainname = trainname;
		this.managename = managename;
		this.managephone = managephone;
		this.trainid = trainid;
		this.loginname = loginname;
		this.password = password;
	}

	// Property accessors

	public Integer getTrainapplyid() {
		return this.trainapplyid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public void setTrainapplyid(Integer trainapplyid) {
		this.trainapplyid = trainapplyid;
	}

	public Date getApplytime() {
		return this.applytime;
	}

	public void setApplytime(Date applytime) {
		this.applytime = applytime;
	}

	public String getApplicant() {
		return this.applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getApplystate() {
		return this.applystate;
	}

	public void setApplystate(String applystate) {
		this.applystate = applystate;
	}

	public String getTrainname() {
		return this.trainname;
	}

	public void setTrainname(String trainname) {
		this.trainname = trainname;
	}

	public String getManagename() {
		return this.managename;
	}

	public void setManagename(String managename) {
		this.managename = managename;
	}

	public String getManagephone() {
		return this.managephone;
	}

	public void setManagephone(String managephone) {
		this.managephone = managephone;
	}

	public Long getTrainid() {
		return this.trainid;
	}

	public void setTrainid(Long trainid) {
		this.trainid = trainid;
	}

}