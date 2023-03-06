package example.spmvc.finalexam;

import java.time.LocalDate;

public class OrderHistory {
	private int orderNum;
	private String userid;
	private String productName;
	private int price;
	private LocalDate orderDate;

	OrderHistory() {
	}

	OrderHistory(int orderNum, String userid, String productName, int price, LocalDate orderDate) {
		this.orderNum = orderNum;
		this.userid = userid;
		this.productName = productName;
		this.price = price;
		this.orderDate = orderDate;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public String getUserid() {
		return userid;
	}

	public String getProductName() {
		return productName;
	}

	public int getPrice() {
		return price;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String printOrderHistory() {
		String result = orderNum + "\t\t" + productName + "\t\t" + price + "\t\t" + orderDate + "<br>";
		return result;
	}
}
