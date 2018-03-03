package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amaker.dao.FeedBackDao;
import com.amaker.entity.Order;
import com.amaker.util.DBUtil;

public class FeedBackDaoImpl implements FeedBackDao {

	public void saveFeedBack(String mark1, String mark2, String mark3,
			String mark4, String feedBack, String orderId, String userId,
			String orderTime) {

		// ���SQL���
		String sql = " insert into feedbacktbl"
				+ "(orderTime,userId,orderId,mark1,mark2,mark3,mark4,feedback)"
				+ "values(?,?,?,?,?,?,?,?) ";
		// ���ݿ����ӹ�����
		DBUtil util = new DBUtil();
		// �������
		Connection conn = util.openConnection();
		try {
			// ���Ԥ�������
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setString(1, orderTime);
			pstmt.setString(2, userId);
			pstmt.setString(3, orderId);
			pstmt.setString(4, mark1);
			pstmt.setString(5, mark2);
			pstmt.setString(6, mark3);
			pstmt.setString(7, mark4);
			pstmt.setString(8, feedBack);
			// ִ�и���
			System.out.println("ִ��");
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}

	}

}
