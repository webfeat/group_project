package dao.staffDao;

import java.util.Date;

import dao.applyDao.Staffapply;
import dao.operatorDao.Operator;
import dao.trainDao.Train;

/**
 * Staff entity. @author MyEclipse Persistence Tools
 */

public class Staff implements java.io.Serializable {

	// Fields

	private Integer staffid;
	private String staffname;
	private String email;
	private String phone;
	private String trainid;
	private Date departureTime;
	private Train train;
	// Constructors

	/** default constructor */
	public Staff() {
	}

	/** minimal constructor */
	public Staff(Integer staffid, String staffname, String email, String phone,
			String trainid) {
		this.staffid = staffid;
		this.staffname = staffname;
		this.email = email;
		this.phone = phone;
		this.trainid = trainid;
	}

	/** full constructor */
	public Staff(Integer staffid, String staffname, String email, String phone,
			String trainid, Date departureTime) {
		this.staffid = staffid;
		this.staffname = staffname;
		this.email = email;
		this.phone = phone;
		this.trainid = trainid;
		this.departureTime = departureTime;
	}

	// Property accessors

	public Integer getStaffid() {
		return this.staffid;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public void setStaffid(Integer staffid) {
		this.staffid = staffid;
	}

	public String getStaffname() {
		return this.staffname;
	}

	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTrainid() {
		return this.trainid;
	}

	public void setTrainid(String trainid) {
		this.trainid = trainid;
	}

	public Date getDepartureTime() {
		return this.departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

}