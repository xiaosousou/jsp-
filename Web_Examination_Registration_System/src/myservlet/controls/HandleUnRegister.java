package myservlet.controls;

import mybean.data.*;
import java.io.*;
import myclass.bll.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HandleUnRegister extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession(true);
		loginBean log=(loginBean)session.getAttribute("login");
		if(log!=null) {
			boolean ok=log.isSuccess();
			if(ok==false) {
				resp.sendRedirect("login.jsp");
			}else {
				String loginName=log.getLoginName();
				unRegister(loginName,req,resp);
			}
		}else {
			resp.sendRedirect("login.jsp");
		}
	}
	public void unRegister(String loginName,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession(true);
		String password=req.getParameter("password");
		unregisterBean unreg=new unregisterBean();
		req.setAttribute("unregister", unreg);
		unreg.setLoginName(loginName);
		int result=0;
		String backMessage=null;
		if(password==null) {
			password="";
		}
		examineeBll exambll=new examineeBll();
		try {
			result=exambll.examineeLogin(loginName, password);
			if(result==1) {
				if(exambll.deleteExamineeByID(loginName)==1) {
					backMessage="ע�����Գɹ�";
					unreg.setBackMessage(backMessage);
					unreg.setFlag(true);
					session.invalidate();
				}else {
					backMessage="Ϊɾ���ÿ�����ע��ʧ��";
					unreg.setBackMessage(backMessage);
					unreg.setFlag(false);
				}
			}else {
				backMessage="�������벻��ȷ��ע��ʧ��";
				unreg.setBackMessage(backMessage);
				unreg.setFlag(false);
			}
		}catch(Exception exp) {
			unreg.setBackMessage("�����쳣"+exp);
			unreg.setFlag(false);
		}
		RequestDispatcher dispatcher=req.getRequestDispatcher("showUnRegister.jsp");
		dispatcher.forward(req, resp);
	}
}
