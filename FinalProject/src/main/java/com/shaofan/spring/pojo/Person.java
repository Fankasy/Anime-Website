package com.shaofan.spring.pojo;

import java.util.Date;
import javax.persistence.*;
@MappedSuperclass

public class Person {
	
//	protected long personID;
//	
	
	private String fname;
	
	private String lname;
	
	private Date birthday;
	
	private int age;
	
	
	private float height;
	
	private String gender;
	
	private float weight;
	
	private String bornplace;
//	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name="personID",unique=true,nullable=false)
//	public long getPersonID() {
//		return personID;
//	}

	@Column(name="firstname")
	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}

	@Column(name="lastname")
	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}

	@Column(name="birthday")
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Column(name="age")
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Column(name="height")
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	
	@Column(name="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name="weight")
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	
	@Column(name="bornplace")
	public String getBornplace() {
		return bornplace;
	}
	public void setBornplace(String bornplace) {
		this.bornplace = bornplace;
	}


//	public void setPersonID(long personID) {
//		this.personID = personID;
//	}
//	
//	
//	
	
}
