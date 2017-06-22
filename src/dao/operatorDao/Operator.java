package dao.operatorDao;

import java.util.HashSet;
import java.util.Set;

import dao.staffDao.Staff;



/**
 * Operator entity. @author MyEclipse Persistence Tools
 */

public class Operator  implements java.io.Serializable {


    // Fields    

     private Integer operatorid;
     private String loginname;
     private String password;
     private Integer empid;

     private Set roles = new HashSet();//建立角色列表与操作员的多对多关联关系
    // Constructors
     
     private Staff staff;//新建人员变量，同时也需要一个操作员；
     
    public Staff getStaff() {
		return staff;
	}


	public void setStaff(Staff staff) {
		this.staff = staff;
	}


	public Set getRoles() {
		return roles;
	}


	public void setRoles(Set roles) {
		this.roles = roles;
	}


	/** default constructor */
    public Operator() {
    }

    
    /** full constructor */
    public Operator(Integer operatorid, String loginname, String password, Integer empid) {
        this.operatorid = operatorid;
        this.loginname = loginname;
        this.password = password;
        this.empid = empid;
    }

   
    // Property accessors

    public Integer getOperatorid() {
        return this.operatorid;
    }
    
    public void setOperatorid(Integer operatorid) {
        this.operatorid = operatorid;
    }

    public String getLoginname() {
        return this.loginname;
    }
    
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEmpid() {
        return this.empid;
    }
    
    public void setEmpid(Integer empid) {
        this.empid = empid;
    }
   
}







