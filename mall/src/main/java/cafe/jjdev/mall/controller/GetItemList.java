package cafe.jjdev.mall.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cafe.jjdev.mall.service.Item;
import cafe.jjdev.mall.service.ItemDao;


@WebServlet("/GetItemList")
public class GetItemList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemDao itemDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int currnetPage = 1;
		int rowPerPage = 10;
		ArrayList<Item> list = itemDao.selectItemList(currnetPage, rowPerPage);
		request.setAttribute("list", list);
		//forward itemList.jsp
	}

}
