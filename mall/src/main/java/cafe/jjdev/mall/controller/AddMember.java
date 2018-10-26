package cafe.jjdev.mall.controller;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.*;
import java.sql.*;
import com.sun.corba.se.pept.transport.Connection;

import cafe.jjdev.mall.service.Member;
import cafe.jjdev.mall.service.MemberDao;
@WebServlet("/AddMember")
public class AddMember extends HttpServlet{

	private static final long serialVersionUID = 1L;
	MemberDao memberDao;
	//ȸ������ ��
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/addMember.jsp").forward(request, response);
	}
	//ȸ������ �׼�
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		memberDao = new MemberDao();
		Member member = new Member();
		String id =  request.getParameter("id");
		String pw = request.getParameter("pw");
		int level = Integer.parseInt(request.getParameter("level"));
		member.setId(id);
		member.setPw(pw);
		member.setLevel(level);
		int row = memberDao.insertMember(member);
		response.sendRedirect(request.getContextPath()+"/index");
	}
}