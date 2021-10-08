package model;

import java.io.Serializable;

public class CategoryItemBean implements Serializable {
 private String category;

  private String name;

  private int count_num;

  private String item_id;

  public CategoryItemBean() {}

  public CategoryItemBean(String category, String name, int count_num, String item_id) {
    this.category = category;
    this.name = name;
    this.count_num = count_num;
    this.item_id = item_id;
  }
  public CategoryItemBean(String category, String name, String item_id) {
	    this.category = category;
	    this.name = name;
	    this.item_id = item_id;
  }

  public String getCategory() {
	  return this.category;
  }
  public String getName() {
	  return this.name;
  }
  public int getCount_num() {
	  return this.count_num;
  }
  public String getItem_id() {
	  return this.item_id;
  }
}
