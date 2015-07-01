package com.kdyzm.dbutils;
/*
 * ��дQueryRunner�࣬���쳣ȫ����׽
 * ������һ������save������������÷�����ƿ���ʡȥ��дsql���鷳��
 */
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.sql.DataSource;

import org.apache.commons.dbutils.ResultSetHandler;

public class QueryRunner extends org.apache.commons.dbutils.QueryRunner{
	public <T>T save(T t)throws Exception
	{
		Class<?> cls=t.getClass();
		/*
		 * ��ȡ������������Ӧ���Ƚ����ж��Ƿ����ע�⼴����isAnnotationPresents������
		 * ������Ϊһ������ע�⣬���Բ������ж���
		 */
		Table table=cls.getAnnotation(Table.class);
		//���Ȼ�ȡ����
		String tableName=table.name();
		
		//���sql��䣬������������
		String sql="insert into "+tableName;
		String columns="(";//ƴ���ֶ�����
		String values="values(";//ƴ��ֵ
		
		//��ȡ�����������ֶ�����
		Field[]fields=cls.getDeclaredFields();
		for(Field field:fields)
		{
			if(field.isAnnotationPresent(Column.class))//���������ע��Ļ�����仰�ǽ���ֶ�������ƥ��ĺ���
			{
				field.setAccessible(true);//���ÿ��Ա�������
				String columnName=field.getName();//�������ֶ���
				Column column=field.getAnnotation(Column.class);
				//���ȿ�����û��ע����ֶ��������û��ע��Ļ���ʹ��ԭ�����ֶ�������ʹ��ע���������ֶ���
				if(column.name()!=null||!column.name().equals(""))//�����ע��Ļ�ʹ��ע�����������ֶ���
				{
					columnName=column.name();
				}
				//��ȡ��ֵ
				Object value=field.get(t);

				if(columns.equals("("))
				{
					columns+=columnName;
					if(value instanceof String)
					{
						values+="'"+value+"'";
					}
					else
					{
						values+=value;
					}
				}
				else
				{
					columns+=","+columnName;
					if(value instanceof String)
					{
						values+=",'"+value+"'";
					}
					else
					{
						values+=","+value;
					}
				}
			}
		}
		columns+=") ";
		values+=")";
		sql+=columns;
		sql+=values;
		System.out.println(sql);
		update(sql);
		return t;
	}
	public QueryRunner() {
		super();
	}
	public QueryRunner(boolean pmdKnownBroken) {
		super(pmdKnownBroken);
	}
	public QueryRunner(DataSource ds, boolean pmdKnownBroken) {
		super(ds, pmdKnownBroken);
	}
	public QueryRunner(DataSource ds) {
		super(ds);
	}
	@Override
	public int[] batch(Connection conn, String sql, Object[][] params)
			throws SQLException {
		return super.batch(conn, sql, params);
	}
	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException{
		return super.batch(sql, params);
	}
	@Override
	public <T> T insert(Connection conn, String sql, ResultSetHandler<T> rsh,
			Object... params) throws SQLException {
		return super.insert(conn, sql, rsh, params);
	}
	@Override
	public <T> T insert(Connection conn, String sql, ResultSetHandler<T> rsh)
			throws SQLException {
		return super.insert(conn, sql, rsh);
	}
	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {
		return super.insert(sql, rsh, params);
	}
	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh)
			throws SQLException {
		return super.insert(sql, rsh);
	}
	@Override
	public <T> T insertBatch(Connection conn, String sql,
			ResultSetHandler<T> rsh, Object[][] params) throws SQLException {
		return super.insertBatch(conn, sql, rsh, params);
	}
	@Override
	public <T> T insertBatch(String sql, ResultSetHandler<T> rsh,
			Object[][] params) throws SQLException {
		return super.insertBatch(sql, rsh, params);
	}
	@Override
	public <T> T query(Connection conn, String sql, Object param,
			ResultSetHandler<T> rsh) throws SQLException {
		return super.query(conn, sql, param, rsh);
	}
	@Override
	public <T> T query(Connection conn, String sql, Object[] params,
			ResultSetHandler<T> rsh) throws SQLException {
		return super.query(conn, sql, params, rsh);
	}
	@Override
	public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh,
			Object... params) throws SQLException {
		return super.query(conn, sql, rsh, params);
	}

	@Override
	public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh)
			throws SQLException {
		return super.query(conn, sql, rsh);
	}

	@Override
	public <T> T query(String sql, Object param, ResultSetHandler<T> rsh)
			throws SQLException {
		return super.query(sql, param, rsh);
	}

	@Override
	public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh)
			throws SQLException {
		return super.query(sql, params, rsh);
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {
		return super.query(sql, rsh, params);
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		return super.query(sql, rsh);
	}

	@Override
	public int update(Connection conn, String sql, Object... params)
			throws SQLException {
		return super.update(conn, sql, params);
	}

	@Override
	public int update(Connection conn, String sql, Object param)
			throws SQLException {
		return super.update(conn, sql, param);
	}

	@Override
	public int update(Connection conn, String sql) throws SQLException {
		return super.update(conn, sql);
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		return super.update(sql, params);
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		return super.update(sql, param);
	}

	@Override
	public int update(String sql) throws SQLException {
		return super.update(sql);
	}

	@Override
	protected void close(Connection conn) throws SQLException {
		super.close(conn);
	}

	@Override
	protected void close(ResultSet rs) throws SQLException {
		super.close(rs);
	}

	@Override
	protected void close(Statement stmt) throws SQLException {
		super.close(stmt);
	}

	@Override
	public void fillStatement(PreparedStatement arg0, Object... arg1)
			throws SQLException {
		super.fillStatement(arg0, arg1);
	}

	@Override
	public void fillStatementWithBean(PreparedStatement arg0, Object arg1,
			PropertyDescriptor[] arg2) throws SQLException {
		super.fillStatementWithBean(arg0, arg1, arg2);
	}

	@Override
	public void fillStatementWithBean(PreparedStatement arg0, Object arg1,
			String... arg2) throws SQLException {
		super.fillStatementWithBean(arg0, arg1, arg2);
	}

	@Override
	public DataSource getDataSource() {
		return super.getDataSource();
	}

	@Override
	public boolean isPmdKnownBroken() {
		return super.isPmdKnownBroken();
	}

	@Override
	protected Connection prepareConnection() throws SQLException {
		return super.prepareConnection();
	}

	@Override
	protected PreparedStatement prepareStatement(Connection conn, String sql,
			int returnedKeys) throws SQLException {
		return super.prepareStatement(conn, sql, returnedKeys);
	}

	@Override
	protected PreparedStatement prepareStatement(Connection conn, String sql)
			throws SQLException {
		return super.prepareStatement(conn, sql);
	}

	@Override
	protected void rethrow(SQLException cause, String sql, Object... params)
			throws SQLException {
		super.rethrow(cause, sql, params);
	}

	@Override
	protected ResultSet wrap(ResultSet rs) {
		return super.wrap(rs);
	}
}
