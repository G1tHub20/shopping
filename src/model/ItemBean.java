package model;

import java.io.Serializable;
/**
 * 商品の情報を持つJabaBeans
 */
public class ItemBean implements Serializable {
	private int id;
	private String name;
	private String type;  // 商品カテゴリ
	private int price;    // 単価
	private int stock;    // 在庫数
	private String image; // 商品画像のファイル名
	private int state;    // 販売状態 0（販売中止）, 1（販売中）
	private String created_at;

	public ItemBean() {}

	// itemテーブル
	public ItemBean(int id, String name, String type, int price, int stock, String image, int state, String created_at) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.stock = stock;
		this.image = image;
		this.state = state;
		this.created_at = created_at;
	}
	// 商品一覧取得時のデバッグ表示用
	public String toString() {
		return "id:" + this.id + ",name:" + this.name + ",type:" + this.type + ",price:" + this.price
				+ ",stock:" + this.stock + ",image:" + this.image + ",state:" + this.state + ",created_at:" + this.created_at;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public int getPrice() {
		return price;
	}
	public int getStock() {
		return stock;
	}
	public String getImage() {
		return image;
	}
	public int getState() {
		return state;
	}
	public String getCreated_at() {
		return created_at;
	}
}
