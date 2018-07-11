package myclass.dal;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;

public class DBAccess {
	private Connection conn=null;
	private Statement stmt=null;
	private ResultSet rs=null;
	private PreparedStatement prpSql=null;

	public DBAccess(){}
	//����һ�����ݿ�����
	public Connection getConn() {
		if(conn==null) {
			getConnection();
		}
		return conn;
	}
	//��ȡ���Ӷ���
	public void getConnection() {
		try {
			Context ctx=new InitialContext();
			
			DataSource ds=(DataSource) ctx.lookup("java:comp/env/jdbc/dataexam");
			conn=ds.getConnection();
			stmt=conn.createStatement();
			System.out.println("���ݿ⽨�����ӳ�");
		}catch(NamingException ex1){
			System.out.println("�������ݿ����ӳ������Ƿ���ȷ");
			ex1.printStackTrace();
		}catch(SQLException ex2) {
			System.out.println("�������ݿ��Ƿ�����");
			ex2.printStackTrace();
		}
	}
	//       ��ѯ���        ���ؽ��
	public ResultSet query(String strSql) {
		ResultSet rs=null;
		try {
			rs=stmt.executeQuery(strSql);
			return rs;
		}catch(SQLException ex) {
			ex.printStackTrace();
			return rs;
		}
	}
	//��ѯԤ����     ���ؽ����
	public ResultSet query(PreparedStatement prpSql) {
		this.prpSql=prpSql;
		ResultSet rs=null;
		try {
			rs=this.prpSql.executeQuery();
			return rs;
		}catch(SQLException ex) {
			ex.printStackTrace();
			return rs;
		}
	}
	//����������       ���ؽ��
	public boolean insert(String[] sqls) {
		boolean breturn = false;
		try {
			conn.setAutoCommit(false);
			for(int i=0;i<sqls.length;i++) {
				if(sqls[i] != null) {
					stmt.addBatch(sqls[i]);
				}
			}
			stmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			breturn = true;
		}catch(SQLException ex){
		}
		return  breturn;
	}
	//����Ӱ������
	public int excuteSql(String strSql) {
		int result=0;
		try {
			stmt=conn.createStatement();
			result=stmt.executeUpdate(strSql);
		}catch(SQLException ex){
			System.out.println("�����쳣��at DBAccess.executeSql()");
			ex.printStackTrace();
			result=-1;
		}
		return result;
	}
	//����Ԥ����  ���
	public int excuteSql(PreparedStatement prpSql) {
		int result=0;
		try {
			this.prpSql=prpSql;
			result=this.prpSql.executeUpdate();	
		}catch(SQLException ex) {
			System.out.println("�����쳣��at DBAccess.executeSql()");
			ex.printStackTrace();
			result=-1;
		}
		return result;
	}
	//���ز�ѯ������
	public boolean executeSql(String[] sqls) {
		boolean breturn=false;
		try {
			conn.setAutoCommit(false);
			stmt=conn.createStatement();
			for(int i=0;i<sqls.length;i++) {
				if(sqls[i]!=null) {
					stmt.addBatch(sqls[i]);
				}
			}
			stmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			breturn=true;
		}catch(SQLException ex){
			System.out.println("�����쳣��at DBBAccess.executeSql()");
			ex.printStackTrace();
		}
		return breturn;
	}
	public void closeConnection() {
		try {
			if(rs!=null) {
				rs.close();
				rs=null;
			}
			if(stmt!=null) {
				stmt.close();
				stmt=null;
			}
			if(conn!=null) {
				conn.close();
				conn=null;
			}
			if(prpSql!=null) {
				prpSql.close();
				prpSql=null;
			}
		}catch(SQLException ex) {
			System.out.println("�����쳣:at DBAccess.closeConnection()");
			ex.printStackTrace();
		}
	}
	//��ȡϵͳʱ��
	public static String getSysDate() {
		DBAccess dba=new DBAccess();
		String sql="select sysdate() sysdate;";
		try {
			dba.getConnection();
			ResultSet rs=dba.query(sql);
			String currentDate=null;
			if(rs.next()) {
				currentDate=rs.getString("sysdate");
			}
			return currentDate;
		}catch(SQLException ex) {
			System.out.println("�����쳣 :at DBAccess,getSysDate()");
			ex.printStackTrace();
			return null;
		}finally {
			dba.closeConnection();
		}
	}
}
