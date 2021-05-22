package model;

public class ItemBean {
	private String item_id;
	private String name;
	private int price;
	private int quantity;
	private int subtotal;

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

	public String getItem_id() {
		return item_id;
	}
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public int getSubtotal() {
		return subtotal;
	}
}
