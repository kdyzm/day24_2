package com.kdyzm.dao;

import com.kdyzm.dbutils.DataSourceUtils_c3p0;
import com.kdyzm.dbutils.QueryRunner;
import com.kdyzm.domain.Person;

//介绍注解在数据库中的使用方法
public class UserDao {
	public static void main(String[] args) throws Exception {
		Person p=new Person();
		p.setPersonid("12455324");
		p.setPersonname("小强");
		p.setPersonage(24);
		p.setPersonAddress("山东理工大学");
		
		QueryRunner run=new QueryRunner(DataSourceUtils_c3p0.getDataSource());
		run.save(p);
	}
}	
