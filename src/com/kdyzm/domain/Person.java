package com.kdyzm.domain;

import javax.persistence.Column;
import javax.persistence.Table;
/*
 * ʹ��ע����Խ�����ݿ������������һ�µ����⣬���Խ���ֶ�����ƥ������⣬���Խ���ֶ�������ƥ�������
 */
@Table(name="user")//ʵ�ʵı���Ϊuser��������person
public class Person {
	@Column(name="id")
	private String personid;
	@Column(name="name")
	private String personname;
	@Column(name="age")
	private int personage;
	
	private String personAddress;//���ֶ������ݿ���б󲻴��ڡ�
	
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
