package model;

public class SearchForItemBean {
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
}
