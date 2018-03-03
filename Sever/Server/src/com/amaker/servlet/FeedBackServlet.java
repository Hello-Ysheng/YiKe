package com.amaker.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.FeedBackDao;
import com.amaker.dao.impl.FeedBackDaoImpl;

public class FeedBackServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
System.out.println("doGet");
		// ���ʱ��
		String orderTime = request.getParameter("time");
		// ����Ա���
		String userId = request.getParameter("userId");
		// ��Ʒ��ζ
		String mark1 = request.getParameter("mark1");
		// ����̬��
		String mark2 = request.getParameter("mark2");
		// ���껷��
		String mark3 = request.getParameter("mark3");
		// ��������
		String mark4 = request.getParameter("mark4");
		// ��������
		String feedBack = request.getParameter("feedback");
		// ������
		String orderId = request.getParameter("orderId");

		// ���DAO�ӿ�
		FeedBackDao dao = new FeedBackDaoImpl();
		dao.saveFeedBack(mark1, mark2, mark3, mark4, feedBack, orderId, userId, orderTime);

		
	//super.doGet(request, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("doPost");
		doGet(req, resp);

	}

}
