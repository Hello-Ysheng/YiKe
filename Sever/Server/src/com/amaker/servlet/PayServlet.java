package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.PayDao;
import com.amaker.dao.impl.PayDaoImpl;
import com.amaker.entity.Menu;
import com.amaker.entity.Print;
import com.amaker.entity.QueryOrder;
import com.amaker.entity.QueryOrderDetail;

/**
 * @author ��ҫ�� ���ݶ�����ţ���ѯ������ϸ��Ϣ
 */
public class PayServlet extends HttpServlet {

	// ������ʱ��ŵ����Ϣ ��ӡ��
	public static List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();

	// ���췽��
	public PayServlet() {
		super();
	}

	// ���ٷ���
	public void destroy() {
		super.destroy();
	}

	// ��ӦGet����
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		// ʵ����DAO
		PayDao dao = new PayDaoImpl();
		// ��ö���ID
		String result = request.getParameter("id");
		String[] res = result.split("aaa");
		String shortid = res[0];
		String longid = res[1];
		String isPrint = res[2];
		// ��ѯ������Ϣ
		QueryOrder qo = dao.getOrderById(Integer.parseInt(shortid));
		// ��ѯ������ϸ�б�
		List list = dao.getOrderDetailList(Integer.parseInt(shortid));

		// ƴXML��ʽ����
		out.println("<?xml version='1.0' encoding='UTF-8'?>");
		// ���ڵ�
		System.out.println(list.size());
		out.println("<disheslist>");
		for (int i = 0; i < list.size(); i++) {
			QueryOrderDetail qod = (QueryOrderDetail) list.get(i);
			String name = qod.getName();
			float price = qod.getPrice();
			int num = qod.getNum();
			float total = qod.getTotal();
			String remark = qod.getRemark();

			// ��ÿ����Ʒ�����뵽list��
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", total);
			map.put("price", price);
			map.put("name", name);
			map.put("num", num);
			orderList.add(map);

			// ��Ʒ�ڵ�
			out.println("<dishes>");
			// ��Ʒ����
			out.print("<name>");
			out.print(name);
			System.out.println(name);
			out.println("</name>");
			// ����
			out.print("<price>");
			out.print(price == 0 ? "" : "" + price);
			out.println("</price>");
			// ����
			out.print("<num>");
			out.print(num == 0 ? "" : num + "");
			out.println("</num>");
			// �ܼ�
			out.print("<total>");
			out.print(total);
			out.println("</total>");

			// ��ע
			out.print("<remark>");
			out.print(remark);
			out.println("</remark>");

			out.println("</dishes>");

		}

		// ������Ϣ�ڵ�
		out.println("<information>");
		// �������
		out.print("<id>");
		out.print(" " + longid);
		out.println("</id>");
		// �µ�ʱ��
		out.print("<time>");
		out.print(qo.getOrderTime());
		out.println("</time>");
		// ����Ա
		out.print("<personname>");
		out.print(qo.getName());
		out.println("</personname>");
		// ����
		out.print("<personnum>");
		out.print(qo.getPersonNum());
		out.println("</personnum>");
		// ����
		out.print("<tableid>");
		out.print(qo.getTableId());
		out.println("</tableid>");

		out.println("</information>");

		out.println("</disheslist>");

		out.flush();
		out.close();
// ��ӡ
		if (isPrint.equals("true")) {
			Print.payPrint(orderList, "" + longid, qo.getOrderTime(), qo.getName(),
					qo.getPersonNum() + "", qo.getTableId() + "");
		}
		orderList.clear();
	}

	// ��ӦPost����
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// ��ʼ������
	public void init() throws ServletException {
	}

}
