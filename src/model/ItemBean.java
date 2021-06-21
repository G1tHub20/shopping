package model;

public class ItemBean {
	private String item_id;
	private String name;
	private int price;
	private int quantity;
	private int subtotal;
	private int user_id;

	public ItemBean() {}

	public ItemBean(String item_id, String name, int price, int quantity, int subtotal) {
		this.item_id = item_id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	public ItemBean(String item_id, String name, int price, int quantity) {
		this.item_id = item_id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}


	public ItemBean(String item_id, String name) {
		this.item_id = item_id;
		this.name = name;
	}

	public ItemBean(String item_id, int quantity) {
		this.item_id = item_id;
		this.quantity = quantity;
	}

	public ItemBean(int price, int quantity, String item_id) { // 引数の重複を避けるため…
		this.price = price;
		this.quantity = quantity;
		this.item_id = item_id;
	}

	public ItemBean(int user_id, String item_id, int price, int quantity) {
		this.user_id = user_id;
		this.item_id = item_id;
		this.price = price;
		this.quantity = quantity;
	}
	public ItemBean(String item_id, int quantity, int user_id) {
		this.item_id = item_id;
		this.quantity = quantity;
		this.user_id = user_id;
	}

	public String getItem_id() {
		return item_id;
	}
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	public int getQuantity() { // getPurchaseNum()も兼用
		return quantity;
	}
	public int getSubtotal() {
		return subtotal;
	}
	public int getUser_id() {
		return user_id;
	}
}
