package model;

import java.io.Serializable;
/**
 * 注文履歴の情報を保持するデータモデル
 */
public class HistoryBean implements Serializable {
	private int id;
	private int user_id;
	private int item_id;
	private int item_price;    // 注文時の金額
	private int order_num;
	private String order_date;
	private String item_name;  // 注文時の商品名 ※商品名変更時用
	private int sum_price;

	public HistoryBean() {}

	// historyテーブル
	public HistoryBean(int id, int user_id, int item_id, int item_price, int order_num, String order_date) {
		this.id = id;
		this.user_id = user_id;
		this.item_id = item_id;
		this.item_price = item_price;
		this.order_num = order_num;
		this.order_date = order_date;
	}

	// 注文履歴表示
	public HistoryBean(int id, int user_id, int item_id, int item_price, int order_num, String order_date, String item_name, int sum_price) {
		this.id = id;
		this.user_id = user_id;
		this.item_id = item_id;
		this.item_price = item_price;
		this.order_num = order_num;
		this.order_date = order_date;
		this.item_name = item_name;   //
		this.sum_price = sum_price;   //
	}

	public int getId() {
		return id;
	}
	public int getUser_id() {
		return user_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public int getItem_price() {
		return item_price;
	}
	public int getOrder_num() {
		return order_num;
	}
	public String getOrder_date() {
		return order_date;
	}
	public String getItem_name() {
		return item_name;
	}
	public int getSum_price() {
		return sum_price;
	}
}
