package myservlet.controls;

import mybean.data.*;
import java.io.*;
import myclass.bll.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HandleLogin extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	public String handleString(String s) {
		try {
			byte bb[]=s.getBytes("iso-8859-1");
			s=new String(bb,"utf-8");
		}catch(Exception ee) {
			System.out.println(ee.toString());
		}
		return s;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		loginBean log=null;
		String backmessage="";
		HttpSession session = req.getSession(true);
		try {
			log=(loginBean)session.getAttribute("login");
			if(log==null) {
				log=new loginBean();
				session.setAttribute("login", log);
			}
		}catch(Exception ee) {
			log=new loginBean();
			session.setAttribute("login", log);
		}
		String loginName=req.getParameter("loginName").trim(),
				password=req.getParameter("password").trim();
		
		loginName=handleString(loginName);
		password=handleString(password);
		
		boolean ok=log.isSuccess();
		
		if(ok==true&&loginName.equals(log.getLoginName())) {
			backmessage="�Ѿ���¼��";
			log.setBackMessage(backmessage);
		}else {
			examineeBll exambll = new examineeBll();
			int result=exambll.examineeLogin(loginName, password);
			if(result==1) {
				backmessage="��¼�ɹ�";
				log.setBackMessage(backmessage);
				log.setSuccess(true);
				log.setLoginName(loginName);
			}else if(result==0) {
				backmessage="����������֤��������벻����Ҫ��";
				log.setBackMessage(backmessage);
				log.setSuccess(false);
				log.setLoginName(loginName);
				log.setPassword(password);
			}else {
				backmessage="����������֤���벻���ڻ������벻��ȷ";
				log.setBackMessage(backmessage);
				log.setSuccess(false);
				log.setLoginName(loginName);
				log.setPassword(password);
			}
		}
		RequestDispatcher dispatcher=req.getRequestDispatcher("showLoginMess.jsp");
		dispatcher.forward(req, resp);
	}
}
