package example.spmvc.finalexam;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class OrderPeriod {
	@DateTimeFormat(pattern = "yyyyMMdd")
	private LocalDate orderDate1;

	@DateTimeFormat(pattern = "yyyyMMdd")
	private LocalDate orderDate2;

	public LocalDate getOrderDate1() {
		return orderDate1;
	}

	public LocalDate getOrderDate2() {
		return orderDate2;
	}

	public void setOrderDate1(LocalDate orderDate1) {
		this.orderDate1 = orderDate1;
	}

	public void setOrderDate2(LocalDate orderDate2) {
		this.orderDate2 = orderDate2;
	}
}
