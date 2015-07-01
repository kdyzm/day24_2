package com.kdyzm.dbutils;
/*
 * 重写QueryRunner类，将异常全部捕捉
 * 新增加一个方法save，这个方法利用反射机制可以省去书写sql的麻烦。
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
		 * 获取表名，这里理应当先进行判断是否存在注解即调用isAnnotationPresents方法，
		 * 但是因为一定存在注解，所以不进行判断了
		 */
		Table table=cls.getAnnotation(Table.class);
		//首先获取表名
		String tableName=table.name();
		
		//组成sql语句，这是重中自重
		String sql="insert into "+tableName;
		String columns="(";//拼接字段名称
		String values="values(";//拼接值
		
		//获取所有声明的字段名称
		Field[]fields=cls.getDeclaredFields();
		for(Field field:fields)
		{
			if(field.isAnnotationPresent(Column.class))//如果进行了注解的话，这句话是解决字段数量不匹配的核心
			{
				field.setAccessible(true);//设置可以暴力访问
				String columnName=field.getName();//本来的字段名
				Column column=field.getAnnotation(Column.class);
				//优先考虑有没有注解的字段名，如果没有注解的话则使用原来的字段名否则使用注解声明的字段名
				if(column.name()!=null||!column.name().equals(""))//如果有注解的话使用注解上声明的字段名
				{
					columnName=column.name();
				}
				//获取列值
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
