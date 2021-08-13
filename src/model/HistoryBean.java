package model;

public class HistoryBean {
<<<<<<< HEAD
  private int orderId;
  
  private String purchaseDate;
  
  private int userId;
  
  private String itemId;
  
  private int purchaseNum;
  
  private String name;
  
  private int price;
  
  private int subtotal;
  
  public HistoryBean() {}
  
  public HistoryBean(int orderId, String purchaseDate, int userId, String itemId, int purchaseNum) {
    this.orderId = orderId;
    this.purchaseDate = purchaseDate;
    this.userId = userId;
    this.itemId = itemId;
    this.purchaseNum = purchaseNum;
  }
  
  public HistoryBean(int orderId, String purchaseDate, String name, int price, int subtotal, int userId, String itemId, int purchaseNum) {
    this.orderId = orderId;
    this.purchaseDate = purchaseDate;
    this.name = name;
    this.price = price;
    this.subtotal = subtotal;
    this.userId = userId;
    this.itemId = itemId;
    this.purchaseNum = purchaseNum;
  }
  
  public int getOrderId() {
    return this.orderId;
  }
  
  public String getPurchaseDate() {
    return this.purchaseDate;
  }
  
  public int getUserId() {
    return this.userId;
  }
  
  public String getItemId() {
    return this.itemId;
  }
  
  public int getPurchaseNum() {
    return this.purchaseNum;
  }
  
  public String getName() {
    return this.name;
  }
  
  public int getPrice() {
    return this.price;
  }
  
  public int getSubtotal() {
    return this.subtotal;
  }
=======
	private int orderId;
	private String purchaseDate;
	private int userId;
	private String itemId;
	private int purchaseNum;

	private String name;
	private int price;
	private int subtotal;

	public HistoryBean() {}


	public HistoryBean(int orderId, String purchaseDate, int userId, String itemId, int purchaseNum) {
		this.orderId = orderId;
		this.purchaseDate = purchaseDate;
		this.userId = userId;
		this.itemId = itemId;
		this.purchaseNum = purchaseNum;
	}

	public HistoryBean(int orderId, String purchaseDate, String name, int price, int subtotal, int userId, String itemId, int purchaseNum) {
		this.orderId = orderId;
		this.purchaseDate = purchaseDate;

		this.name = name;
		this.price = price;
		this.subtotal = subtotal;

		this.userId = userId;
		this.itemId = itemId;
		this.purchaseNum = purchaseNum;
	}

	public int getOrderId() {
		return orderId;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public int getUserId() {
		return userId;
	}
	public String getItemId() {
		return itemId;
	}
	public int getPurchaseNum() {
		return purchaseNum;
	}

	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	public int getSubtotal() {
		return subtotal;
	}
>>>>>>> branch 'master' of https://github.com/G1tHub20/shopping.git
}
