package com.amaker.entity;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public final class Print {

	// web���ô�ӡ
	public static void doPrint(String bf) {
		try {
			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			// ʹ��Ĭ�ϴ�ӡ�������Ĭ�ϴ�ӡ������POS��ӡ������ͨ�����Ʋ��ҡ�
			PrintService printer = PrintServiceLookup
					.lookupDefaultPrintService();
			// job
			DocPrintJob job = printer.createPrintJob();
			byte[] buf = bf.getBytes();
			InputStream stream = new ByteArrayInputStream(buf);
			Doc doc = new SimpleDoc(stream, flavor, null);

			// print
			job.print(doc, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��˴�ӡ
	 * 
	 * @param list
	 *            ��Ʒ��Ϣ
	 * @param orderId
	 *            ������
	 * @param tableName
	 *            ��λ����
	 */
	public static void orderPrint(List<Map<String, Object>> list,
			String orderId, String tableName) {
		StringBuffer bf = new StringBuffer();
		bf.append("          �׵����ߵ�� \n");
		bf.append("            ���СƱ \n");
		bf.append("--------------------------------\n");
		bf.append("��λ:" + tableName + "     ������:" + orderId + "\n");
		bf.append("--------------------------------\n");
		bf.append("��Ʒ����            ����\n");
		bf.append("--------------------------------\n");
		for (int i = 0; i < list.size(); i++) {
			// ������е��map
			Map listItem = (Map) list.get(i);
			String listItem_num = listItem.get("num") + "";
			String listItem_name = listItem.get("name") + "";
			// һ�����ִ�Сռ�������ո� �˴���Ҫ�жϲ�������
			if (listItem_name.length() == 4) {
				bf.append(listItem_name + "             " + listItem_num
						+ "\n");
			} else if (listItem_name.length() == 3) {
				bf.append(listItem_name + "               "
						+ listItem_num + "\n");
			} else if (listItem_name.length() == 2) {
				bf.append(listItem_name + "                 "
						+ listItem_num + "\n");
			} else if (listItem_name.length() == 1) {
				bf.append(listItem_name + "                   "
						+ listItem_num + "\n");
			} else if (listItem_name.length() == 5) {
				bf.append(listItem_name + "           " + listItem_num
						+ "\n");
			} else if (listItem_name.length() == 6) {
				bf.append(listItem_name + "         " + listItem_num
						+ "\n");
			}
		}
		bf.append("--------------------------------\n");
		// �������ڸ�ʽ
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		bf.append("���ʱ�䣺" + df.format(new Date()) + "\n");
		bf.append("\n\n\n\n\n\n\n");
		doPrint(bf.toString());
	}

	/**
	 * ֧����ӡ
	 * 
	 * @param list
	 *            ��Ʒ�嵥
	 * @param orderId
	 *            ������
	 * @param orderTime
	 *            ����ʱ��
	 * @param serverName
	 *            ����Ա����
	 * @param personNum
	 *            ����
	 * @param tableId
	 *            ��λid
	 */
	public static void payPrint(List<Map<String, Object>> list, String orderId,
			String orderTime, String serverName, String personNum,
			String tableId) {
		int totalNum = 0;
		float totalPrice = 0;
		StringBuffer bf = new StringBuffer();
		bf.append("          �׵����ߵ�� \n");
		bf.append("            ��Ʒ�嵥 \n");
		bf.append("--------------------------------\n");
		bf.append("������:" + orderId + "  ����Ա:" + serverName + "\n");
		bf.append("��λ��:" + tableId + "          ����:" + personNum + "\n");
		bf.append("--------------------------------\n");
		bf.append("��Ʒ����       ����       ����\n");// 11
		bf.append("--------------------------------\n");
		for (int i = 0; i < list.size(); i++) {
			// ������е��map
			Map listItem = (Map) list.get(i);
			String listItem_num = listItem.get("num") + "";
			String listItem_name = listItem.get("name") + "";
			String listItem_price = listItem.get("price") + "";
			String listItem_total = listItem.get("total") + "";
			totalNum += Integer.parseInt(listItem_num);
			totalPrice += Float.parseFloat(listItem_total);
			// һ�����ִ�Сռ�������ո� �˴���Ҫ�жϲ�������
			if (listItem_name.length() == 4) {
				bf.append(listItem_name + "        " + listItem_num
						+ "       ��" + listItem_price + "\n");
			} else if (listItem_name.length() == 3) {
				bf.append(listItem_name + "          " + listItem_num
						+ "       ��" + listItem_price + "\n");
			} else if (listItem_name.length() == 2) {
				bf.append(listItem_name + "            " + listItem_num
						+ "       ��" + listItem_price + "\n");
			} else if (listItem_name.length() == 1) {
				bf.append(listItem_name + "              " + listItem_num
						+ "       ��" + listItem_price + "\n");
			} else if (listItem_name.length() == 5) {
				bf.append(listItem_name + "      " + listItem_num + "       ��"
						+ listItem_price + "\n");
			} else if (listItem_name.length() == 6) {
				bf.append(listItem_name + "    " + listItem_num + "       ��"
						+ listItem_price + "\n");
			}
		}
		bf.append("--------------------------------\n");
		// �������ڸ�ʽ
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		bf.append("����ʱ�䣺" + df.format(new Date()) + "\n");
		bf.append("��Ʒ������" + totalNum + "\n");
		bf.append("�ܼƣ���" + totalPrice + "\n");
		bf.append("\n\n\n\n\n\n\n");
		doPrint(bf.toString());

	}
	// public static void main(String[] args) {
	// czPrint(1, "0000", "8888", "12.25", "100", "10", "90");
	// }
}
