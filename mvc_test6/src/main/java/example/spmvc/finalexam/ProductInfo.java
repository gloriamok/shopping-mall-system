package example.spmvc.finalexam;

public class ProductInfo {
	private int productNum;
	private String productName;
	private int price;
	private String size;
	private String info;

	ProductInfo() {
	}

	ProductInfo(int productNum, String productName, int price, String size, String info) {
		this.productNum = productNum;
		this.productName = productName;
		this.price = price;
		this.size = size;
		this.info = info;
	}

	public int getProductNum() {
		return productNum;
	}

	public String getProductName() {
		return productName;
	}

	public int getPrice() {
		return price;
	}

	public String getSize() {
		return size;
	}

	public String getInfo() {
		return info;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String printProductInfo() {
		String result = "<I>" + info + "</I><br>";
		result += "제품번호 : " + productNum + "<br>";
		result += "제품명 : " + productName + "<br>";
		result += "가격 : " + price + "원<br>";
		result += "사이즈 : " + size + "<br>";
		return result;
	}

}
