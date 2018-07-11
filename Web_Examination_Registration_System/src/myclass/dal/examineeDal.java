package myclass.dal;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import myclass.bol.examinee;

public class examineeDal {
	//�ڿ����в���һ������(��ӿ���)
	public int CreateExaminee(String examID,String examName,String sex,String password,String examType,String school,String memo,String pic) {
		int result=0;
		System.out.println("examine�ϴ����ݿ�"+examID+" "+examName+" "+sex+" "+password+" "+examType+" "+ school+" "+memo);
		String createSql="insert into examinee(examID,examName,sex,password,examType,school,memo,pic)"+"values('"+examID+"','"+examName+"','"+sex+"','"+password+"','"+examType+"','"+school+"','"+memo+"','"+pic+"')";
		DBAccess dba=new DBAccess();
		
		try {
			if(dba.getConn()!=null) {
				result=dba.excuteSql(createSql);
			}
		}
		catch(Exception ne) {
			System.out.println("examineedal�������´���:<br>");
			System.out.println(ne);
		}
		finally {
			dba.closeConnection();
		}
		return result;
	}
	//��examID��ȡ��������
	public examinee getExamineeByID(String examID) {
		DBAccess dba = new DBAccess();
		examinee exam = null;
		try {
			if(dba.getConn()!=null&&examID!=null) {
				String str="select * from examinee where examid='"+examID+"'";
				ResultSet rst=dba.query(str);
				if(rst!=null&&rst.next()) {
					exam=new examinee(rst.getString(1),
									  rst.getString(2),
									  rst.getString(3),
									  rst.getString(4),
									  rst.getString(5),
									  rst.getString(6),
									  rst.getString(7),
									  rst.getString(8));
				}
			}
		}catch(Exception ne) {
			System.out.println("examineedal:getExamineeByID��������");
		}finally {
			dba.closeConnection();
		}
		return exam;
	}
	//���ض�������
	public ArrayList<examinee> getExamineeAll(){
		DBAccess dba=new DBAccess();
		ArrayList<examinee> examList=new ArrayList<examinee>();
		try {
			if(dba.getConn()!=null) {
				String str="select * from examinee";
				ResultSet rst=dba.query(str);
				while(rst!=null&&rst.next()) {
					if(rst.getString("examid")!=null) {
						examinee exam=new examinee(rst.getString(1),
								  rst.getString(2),
								  rst.getString(3),
								  rst.getString(4),
								  rst.getString(5),
								  rst.getString(6),
								  rst.getString(7),
								  rst.getString(8));
						examList.add(exam);
					}
				}
			}
		}catch(Exception ne) {
			System.out.println("examineedal.getExamineeall��������");
			ne.printStackTrace();
		}finally {
			dba.closeConnection();
		}
		return examList;
	}
	//�����˺����뷵�ؿ�������
	public examinee getExamineeByIdpwd(String examID,String password) {
		DBAccess dba=new DBAccess();
		examinee exam=null;
		try {
			if(dba.getConn()!=null&&examID!=null&&password!=null) {
				System.out.println("���ݿ��ѽ�������");
				System.out.println("�ж������Ƿ���ȷ"+examID+password);
				String str="select * from examinee where examId=? and password=?";
				PreparedStatement prpSql;
				prpSql=dba.getConn().prepareStatement(str);
				prpSql.setString(1, examID);
				prpSql.setString(2, password);
				ResultSet rst=dba.query(prpSql);
				if(rst!=null&&rst.next()) {
					exam=new examinee(rst.getString(1),
							  rst.getString(2),
							  rst.getString(3),
							  rst.getString(4),
							  rst.getString(5),
							  rst.getString(6),
							  rst.getString(7),
							  rst.getString(8));
				}
				if(prpSql!=null) {
					prpSql.close();
					prpSql=null;
				}
			}
		}catch(Exception ne) {
			System.out.println("examineedal:getexamineebyidpwd��������");
		}finally {
			dba.closeConnection();
		}
		return exam;
	}
	//�޸�����
	public int setExaminePwd(String examID,String newPassword) {
		DBAccess dba=new DBAccess();
		int result=0;
		try {
			if(dba.getConn()!=null) {
				PreparedStatement prpSql;
				String strSql="Update examinee set password=? where examid=?";
				prpSql=dba.getConn().prepareStatement(strSql);
				prpSql.setString(1, newPassword);
				prpSql.setString(2, examID);
				result=dba.excuteSql(prpSql);
				if(prpSql!=null) {
					prpSql.close();
					prpSql=null;
				}
			}
		}catch(Exception ne) {
			System.out.println("examinrrdal:setPassword��������");
			result=-1;
		}finally {
			dba.closeConnection();
		}
		return result;
	}
	//�޸Ŀ�����Ϣ
	public int updateExamineeByID(String examID,String examName,String sex,String examType,String school,String memo) {
		System.out.println("ִ�и������");
			DBAccess dba=new DBAccess();
			int result=0;
			try {
				if(dba.getConn()!=null) {
					PreparedStatement prpSql;
					String strSql="update examinee set examName=?,sex=?,examType=?,school=?,memo=? where examID=?";
					prpSql=dba.getConn().prepareStatement(strSql);
					prpSql.setString(1, examName);
					prpSql.setString(2, sex);
					prpSql.setString(3, examType);
					prpSql.setString(4, school);
					prpSql.setString(5, memo);
					prpSql.setString(6, examID);
					result=prpSql.executeUpdate();
					if(prpSql!=null) {
						prpSql.close();
						prpSql=null;
					}
				}
			}catch(Exception ne) {
				ne.printStackTrace();
				System.out.println(ne.toString());
				return -1;
			}
			finally {
				dba.closeConnection();
			}
			return result;
	}
	//����ͼƬ����
	public int setExamineePic(String examID,String picStr) {
		DBAccess dba=new DBAccess();
		int result=0;
		try {
			if(dba.getConn()!=null) {
				String strSql="update examinee set pic='"+picStr+"' where examID='"+examID+"'";
				result=dba.excuteSql(strSql);
			}
		}catch(Exception ne){
			System.out.println("�����쳣");
			ne.printStackTrace();
		}finally {
			dba.closeConnection();
		}
		return result;
	}
	//�����֤ɾ������
	public int deleteExmineeByID(String examID) {
		DBAccess dba=new DBAccess();
		int result=0;
		try {
			if(dba.getConn()!=null) {
				PreparedStatement prpSql;
				String strSql="delete from examinee where examid=?";
				prpSql=dba.getConn().prepareStatement(strSql);
				prpSql.setString(1, examID);
				result=prpSql.executeUpdate();
				if(prpSql!=null) {
					prpSql.close();
					prpSql=null;
				}
			}
		}catch(Exception ne) {
			ne.printStackTrace();
			System.out.println(ne.toString());
			return -1;
		}finally {
			dba.closeConnection();
		}
		return result;
	}
}
