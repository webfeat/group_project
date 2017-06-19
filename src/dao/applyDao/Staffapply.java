package dao.applyDao;

import java.util.Date;

import dao.staffDao.Staff;

/**
 * Staffapply entity. @author MyEclipse Persistence Tools
 */

public class Staffapply implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer staffapplyid;
	private Date applytime;
	private String applicant;
	private String applystate;
	private String stafemail;
	private String staffphone;
	private String trainname;
	private String password;
	private String userName;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTrainname() {
		return trainname;
	}

	public void setTrainname(String trainname) {
		this.trainname = trainname;
	}

	private Staff staff;
	// Constructors

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	/** default constructor */
	public Staffapply() {
	}

	/** minimal constructor */
	public Staffapply(Integer staffapplyid, String applicant,
			String applystate, String stafemail) {
		this.staffapplyid = staffapplyid;
		this.applicant = applicant;
		this.applystate = applystate;
		this.stafemail = stafemail;
	}

	/** full constructor */
	public Staffapply(Integer staffapplyid, Date applytime, String applicant,
			String applystate, String stafemail, String staffphone) {
		this.staffapplyid = staffapplyid;
		this.applytime = applytime;
		this.applicant = applicant;
		this.applystate = applystate;
		this.stafemail = stafemail;
		this.staffphone = staffphone;
	}

	// Property accessors

	public Integer getStaffapplyid() {
		return this.staffapplyid;
	}

	public void setStaffapplyid(Integer staffapplyid) {
		this.staffapplyid = staffapplyid;
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

	public String getStafemail() {
		return this.stafemail;
	}

	public void setStafemail(String stafemail) {
		this.stafemail = stafemail;
	}

	public String getStaffphone() {
		return this.staffphone;
	}

	public void setStaffphone(String staffphone) {
		this.staffphone = staffphone;
	}

}