package com.amaker.dao;

public interface FeedBackDao {
	/**
	 * 
	 * @param mark1 ��Ʒ��ζ
	 * @param mark2 ����̬��
	 * @param mark3 ���껷��
	 * @param mark4 ��������
	 * @param feedBack ������Ϣ
	 */
public void saveFeedBack(String mark1, String mark2, String mark3,
		String mark4, String feedBack,String orderId,String userId,String orderTime);
}
