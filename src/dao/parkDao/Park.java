package dao.parkDao;

import dao.trainDao.Train;

/**
 * Park entity. @author MyEclipse Persistence Tools
 */

public class Park implements java.io.Serializable {

	// Fields

	private Integer parkid;
	private String parkname;

	// Constructors
	private Train train;
	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	/** default constructor */
	public Park() {
	}

	/** full constructor */
	public Park(Integer parkid, String parkname) {
		this.parkid = parkid;
		this.parkname = parkname;
	}

	// Property accessors

	public Integer getParkid() {
		return this.parkid;
	}

	public void setParkid(Integer parkid) {
		this.parkid = parkid;
	}

	public String getParkname() {
		return this.parkname;
	}

	public void setParkname(String parkname) {
		this.parkname = parkname;
	}

}