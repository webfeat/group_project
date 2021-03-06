package dao.trainDao;

import java.util.Date;

import dao.parkDao.Park;
import dao.staffDao.Staff;

/**
 * Train entity. @author MyEclipse Persistence Tools
 */

public class Train implements java.io.Serializable {

	// Fields

	private Integer trainid;
	private String trainname;
	private Date checkInTime;
	private Date departureTime;
	private Integer manageid;
	private String managename;
	private String managephone;
	private Integer parkid;
	private Staff staff;
	
	private Park park;
	// Constructors

	public Park getPark() {
		return park;
	}

	public void setPark(Park park) {
		this.park = park;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	/** default constructor */
	public Train() {
	}

	/** minimal constructor */
	public Train(Integer trainid, String trainname, Date checkInTime,
			Integer manageid, String managename) {
		this.trainid = trainid;
		this.trainname = trainname;
		this.checkInTime = checkInTime;
		this.manageid = manageid;
		this.managename = managename;
	}

	/** full constructor */
	public Train(Integer trainid, String trainname, Date checkInTime,
			Date departureTime, Integer manageid, String managename,
			String managephone, Integer parkid) {
		this.trainid = trainid;
		this.trainname = trainname;
		this.checkInTime = checkInTime;
		this.departureTime = departureTime;
		this.manageid = manageid;
		this.managename = managename;
		this.managephone = managephone;
		this.parkid = parkid;
	}

	// Property accessors

	public Integer getTrainid() {
		return this.trainid;
	}

	public void setTrainid(Integer trainid) {
		this.trainid = trainid;
	}

	public String getTrainname() {
		return this.trainname;
	}

	public void setTrainname(String trainname) {
		this.trainname = trainname;
	}

	public Date getCheckInTime() {
		return this.checkInTime;
	}

	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}

	public Date getDepartureTime() {
		return this.departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Integer getManageid() {
		return this.manageid;
	}

	public void setManageid(Integer manageid) {
		this.manageid = manageid;
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

	public Integer getParkid() {
		return this.parkid;
	}

	public void setParkid(Integer parkid) {
		this.parkid = parkid;
	}

}