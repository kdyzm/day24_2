package com.kdyzm.domain;

import javax.persistence.Column;
import javax.persistence.Table;
/*
 * 使用注解可以解决数据库表名和类名不一致的问题，可以解决字段名不匹配的问题，可以解决字段数量不匹配的问题
 */
@Table(name="user")//实际的表名为user，而不是person
public class Person {
	@Column(name="id")
	private String personid;
	@Column(name="name")
	private String personname;
	@Column(name="age")
	private int personage;
	
	private String personAddress;//该字段在数据库表中斌不存在。
	
	public Person() {
	}
	public Person(String personid, String personname, int personage,
			String personAddress) {
		this.personid = personid;
		this.personname = personname;
		this.personage = personage;
		this.personAddress = personAddress;
	}

	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public int getPersonage() {
		return personage;
	}
	public void setPersonage(int personage) {
		this.personage = personage;
	}
	public String getPersonAddress() {
		return personAddress;
	}
	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}
	@Override
	public String toString() {
		return "Person [personid=" + personid + ", personname=" + personname
				+ ", personage=" + personage + "]";
	}
}
