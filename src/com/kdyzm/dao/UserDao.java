package com.kdyzm.dao;

import com.kdyzm.dbutils.DataSourceUtils_c3p0;
import com.kdyzm.dbutils.QueryRunner;
import com.kdyzm.domain.Person;

//����ע�������ݿ��е�ʹ�÷���
public class UserDao {
	public static void main(String[] args) throws Exception {
		Person p=new Person();
		p.setPersonid("12455324");
		p.setPersonname("Сǿ");
		p.setPersonage(24);
		p.setPersonAddress("ɽ������ѧ");
		
		QueryRunner run=new QueryRunner(DataSourceUtils_c3p0.getDataSource());
		run.save(p);
	}
}	
