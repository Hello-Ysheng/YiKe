package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amaker.dao.OrderDao;
import com.amaker.entity.Order;
import com.amaker.entity.OrderDetail;
import com.amaker.entity.Table;
import com.amaker.util.DBUtil;

/**
 * @author ��ҫ�� ��͹���DAOʵ����
 */
public class OrderDaoImpl implements OrderDao {
	ResultSet res;

	// ��������Ϣ���Żض���ID
	/**
	 * �������� Ӧ���Ȳ�ѯordertbl���е����һ���ordertime�ֶΣ���
	 * ordertime�����Ƚϣ����С�ڽ��죬��remark�ֶ�Ϊ1��������ڽ��죬��remark�ֶ�Ϊ �ϸ�����remark��ֵ��1��
	 * Ȼ���⼸������д��ordertbl����
	 */
	public String saveOrder(Order o) {
		int orderId = 0;
		int lastId = 0;
		int id = 0;
		// ���жϸö���Ϊ���յĵڼ�������
		String sql3 = " select max(orderId) as orderId from orderTbl ";
		// ���ݿ����ӹ�����
		DBUtil util = new DBUtil();
		// �������
		Connection conn = util.openConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql3);
			if (rs.next()) {
				lastId = rs.getInt(1);
//				id = rs.getInt(2);
				
			}
			// �õ���������
			java.util.Date ctime = new java.util.Date();
			String rTime = "";
			java.text.SimpleDateFormat cf = new java.text.SimpleDateFormat(
					"yyMMdd");
			rTime = cf.format(ctime);
			if (lastId == 0) {
				orderId = Integer.parseInt(rTime + "001");
			} else {
				if (lastId > Integer.parseInt(rTime + "000")) {
					orderId = lastId + 1;
				} else {
					orderId = Integer.parseInt(rTime + "001");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// ���SQL���
		String sql = " insert into ordertbl(orderTime,userId,tableId,personNum,isPay,orderId)values(?,?,?,?,?,?) ";

		try {
			// ���Ԥ�������
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setString(1, o.getOrderTime());
			pstmt.setInt(2, o.getUserId());
			pstmt.setInt(3, o.getTableId());
			pstmt.setInt(4, o.getPersonNum());
			pstmt.setInt(5, 0);
			pstmt.setInt(6, orderId);
			// ִ�и���
			pstmt.executeUpdate();
			// ���ض������
			 String sql2 = " select max(id) as id  from orderTbl ";
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql2);
			 if (rs.next()) {
			 id = rs.getInt(1);
			 
			 }
			System.out.println(lastId + " " + id);
			return orderId + " " + id;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return "";// return 0 ;
	}

	// �������б�
	public String saveOrderDetail(OrderDetail od) {
		// ���SQL���
		String sql = " insert into orderdetailtbl(orderId,menuId,num,remark)values(?,?,?,?) ";
		String sql2 = "select filename , price from menutbl where id=?";
		// ���ݿ����ӹ�����
		DBUtil util = new DBUtil();
		// �������
		Connection conn = util.openConnection();
		try {
			// ���Ԥ�������
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setInt(1, od.getOrderId());
			pstmt.setInt(2, od.getMenuId());
			pstmt.setInt(3, od.getNum());
			pstmt.setString(4, od.getRemark());
			// ִ�и���
			pstmt.executeUpdate();
			// ���Ԥ������� ���ڷ��ز�Ʒ���ƺͼ۸�
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, od.getMenuId());

			// ִ�в�ѯ
			ResultSet rs = pstmt2.executeQuery();
			// �����ַ������ڴ洢������Ϣ //
			// ǰ��Ϊ ��Ʒ���� ����Ϊ�۸�
			rs.next();
			String result = rs.getString(1) + " " + rs.getString(2);

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return "";

	}

	// ��������״̬������
	public void updateTableStatus(int tableId, int personNum) {
		// ����SQL���
		String sql = " update tableTbl set flag=1 , num=" + personNum
				+ " where id = ? ";
		// ���ݿ����ӹ�����
		DBUtil util = new DBUtil();
		// �������
		Connection conn = util.openConnection();
		try {
			// ���Ԥ�������
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setInt(1, tableId);
			// ִ�и���
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}

	// ��������״̬������
	public void updateTableStatus2(int orderId) {
		// ����SQL���
		String sql = " update TableTbl set flag = 0 , num=0 , orderId = null where id = "
				+ " ( select tableId from OrderTbl where id = ?) ";
		// ���ݿ����ӹ�����
		DBUtil util = new DBUtil();
		// �������
		Connection conn = util.openConnection();
		try {
			// ���Ԥ�������
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setInt(1, orderId);
			// ִ�и���
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}

	// ��ѯ
	public String get(Table t) {
		String orderId = null;
		String longid = null;
		DBUtil util = new DBUtil();
		// �������

		String sql = "select orderId,longid from tabletbl where id=?";
		Connection conn = util.openConnection();
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, t.getId());

			res = psmt.executeQuery();
			while (res.next()) {
				orderId = res.getString("orderId");
				longid = res.getString("longid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}

		return longid+" "+orderId;
	}

	// ɾ��
	public void delete(int orderId) {

		Table t = new Table();
		DBUtil util = new DBUtil();
		// �������
		Connection conn = util.openConnection();
		try {
			// ���Ԥ�������
			PreparedStatement pstmt = conn
					.prepareStatement("delete from tabletbl where orderId=?");
			// ���ò���
			pstmt.setInt(1, orderId);

			// ִ�и���
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}

	}

	// ���
	public void insert(Table t) {

		String orderId = t.getOrderId() + "";
		String long_orderId = t.getLong_orderId() + "";
		String sql = " update tableTbl set orderId = " + orderId
				+ ", longid = " + long_orderId + " where id = ? ";
		// String sql = " update tableTbl set orderId = 12312 where id = 2 ";
		DBUtil util = new DBUtil();
		// �������
		Connection conn = util.openConnection();
		try {
			// ���Ԥ�������
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setInt(1, t.getId());

			// ִ�и���
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}

	}

	public void delete(Table t) {
		// TODO Auto-generated method stub

	}

}
