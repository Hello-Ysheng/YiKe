package com.amaker.entity;

public class QueryOrderDetail {
	// ������
	private String name;
	// �۸�
	private float price;
	// ����
	private int num;
	// �ܼ�
	private float total;
	// ��ע
	private String remark;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
