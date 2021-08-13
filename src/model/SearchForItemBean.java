package model;

public class SearchForItemBean {
<<<<<<< HEAD
  private String category;
  
  private String itemName;
  
  public SearchForItemBean() {}
  
  public SearchForItemBean(String category, String itemName) {
    this.category = category;
    this.itemName = itemName;
  }
  
  public String getCategory() {
    return this.category;
  }
  
  public String getCodeORname() {
    return this.itemName;
  }
=======
	private String category;
	private String itemName;

	public SearchForItemBean() {}

	public SearchForItemBean(String category, String itemName) {
		this.category = category;
		this.itemName = itemName;
	}

	public String getCategory() {
		return category;
	}
	public String getCodeORname() {
		return itemName;
	}
>>>>>>> branch 'master' of https://github.com/G1tHub20/shopping.git
}
