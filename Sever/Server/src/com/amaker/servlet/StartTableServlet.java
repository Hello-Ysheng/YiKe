package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.OrderDao;
import com.amaker.dao.impl.OrderDaoImpl;
import com.amaker.entity.Order;
import com.amaker.entity.Table;

/**
 * @author ��ҫ��
 * ����Servlet��������Ա�������Ϣ���浽OrderTbl����
 */
public class StartTableServlet extends HttpServlet {
	// ���췽��
	public StartTableServlet() {
		super();
	}

	// ���ٷ���
	public void destroy() {
		super.destroy(); 
	}

	// ��ӦGet����
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// ���ʱ��
		String orderTime = request.getParameter("orderTime");
		// ����Ա���
		String userId = request.getParameter("userId");
		// ����
		String tableId = request.getParameter("tableId");
		// ����
		String personNum = request.getParameter("personNum");
		// ���DAO�ӿ�
		OrderDao dao = new OrderDaoImpl();
		// ʵ����������
		Order o = new Order();
		// ���ö�������
		o.setOrderTime(orderTime);
		o.setPersonNum(Integer.parseInt(personNum));
		o.setUserId(Integer.parseInt(userId));
		o.setTableId(Integer.parseInt(tableId));
		// ���ض���ID
		String result = dao.saveOrder(o);
		//���ص�Ϊ��������+�̶�����
		String orderId[] = result.split(" ");
		
		// ʵ����������
		Table t = new Table();
		// ���ö�������
		t.setId(Integer.parseInt(tableId));
		t.setOrderId(Integer.parseInt(orderId[1]));
		t.setLong_orderId(Integer.parseInt(orderId[0]));
		dao.insert(t);
		
		
		// ���²���״̬Ϊ ����
		
		dao.updateTableStatus(Integer.parseInt(tableId),Integer.parseInt(personNum));
		
		// ����ID
		out.print(result);
		out.flush();
		out.close();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	/*  Ԫʱ����
	 * 	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// ���ʱ��
		String orderTime = request.getParameter("orderTime");
		// ����Ա���
		String userId = request.getParameter("userId");
		// ����
		String tableId = request.getParameter("tableId");
		// ����
		String personNum = request.getParameter("personNum");
		// ���DAO�ӿ�
		OrderDao dao = new OrderDaoImpl();
		// ʵ����������
		Order o = new Order();
		// ���ö�������
		o.setOrderTime(orderTime);
		o.setPersonNum(Integer.parseInt(personNum));
		o.setUserId(Integer.parseInt(userId));
		o.setTableId(Integer.parseInt(tableId));
		// ���ض���ID
		int id = dao.saveOrder(o);
		
		// ���²���״̬Ϊ ����
		dao.updateTableStatus(Integer.parseInt(tableId));
		// ����ID
		out.print(id);
		out.flush();
		out.close();
		*/
	}

	// ��ӦPost����
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		        // ��ȡ���Ͳ���
				String type = request.getParameter("type");
				// ��ȡ��λ��
				int tableId = Integer.parseInt(request.getParameter("tableId"));
				// ���DAO�ӿ�
				OrderDao dao = new OrderDaoImpl();
				// ʵ����������
				Table t = new Table();
				// ���ö�������
				t.setId(tableId);
				
				if(type.equals("1")){
					//����
					doGet(request, response);

				}else if(type.equals("2")){
					//��ѯ
					String orderId = dao.get(t);
					// ����ID
					out.print(orderId);
					out.flush();
					out.close();
				}else if(type.equals("3")){
					//ɾ��
					dao.delete(t);
				}
		
		
		
		
//		doGet(request,response);
	}

	// ��ʼ��
	public void init() throws ServletException {
	}

}
