package com.amaker.dao;

import com.amaker.entity.Order;
import com.amaker.entity.OrderDetail;
import com.amaker.entity.Table;
public interface OrderDao {
	// ���濪����Ϣ
	public String saveOrder(Order o );
	// �������б���Ϣ
	public String saveOrderDetail(OrderDetail od);
	
	// ��������״̬������
	public void updateTableStatus(int tableId,int personNum);
	// ��������״̬����λ
	public void updateTableStatus2(int orderId);
	// ����λ�붩����
	public void insert(Table t);
	//�õ�����λ��ʱ�Ķ�����
	public String get(Table t);
	//ɾ������λ������
	public void delete(Table t);
	
}
