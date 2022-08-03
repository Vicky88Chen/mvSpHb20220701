package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	@Id
      int aid;
      String userName;
      String password;
      public Account() {}
      public Account(int id,String n , String p) {
    	  aid=id;
    	  userName=n;
    	  password=p;
      }
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Account [aid=" + aid + ", userName=" + userName + ", password=" + password + "]";
	}
	
}
