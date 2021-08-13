package model;

import dao.ItemDAO;

public class AdminLogic {
<<<<<<< HEAD
  public boolean execute1(ItemBean newItem) {
    System.out.println("\r''''''''''''''''''''AdminLogic''''''''''''''''''''");
    // DAOクラスをインスタンス化
    ItemDAO dao = new ItemDAO();
    // ■itemテーブルに商品の注文を反映
    boolean isNew = dao.newItemInfo(newItem);
    if (!isNew)
      return false;
    return true;
  }

  public int execute2(ItemBean changeItem) {
    System.out.println("\r''''''''''''''''''''AdminLogic''''''''''''''''''''");
    // DAOクラスをインスタンス化
    ItemDAO dao = new ItemDAO();
    // ■itemテーブルに商品の注文を反映
    int isChange = dao.changeItemInfo(changeItem);
    return isChange;
  }

  public boolean execute3(ItemBean changeItem) {
    System.out.println("\r''''''''''''''''''''AdminLogic''''''''''''''''''''");
    // DAOクラスをインスタンス化
    ItemDAO dao = new ItemDAO();
    // ■itemテーブルに商品の注文を反映
    boolean isChange = dao.deleteItemInfo(changeItem);
    if (!isChange)
      return false;
    return true;
  }
=======
	public boolean execute1(ItemBean newItem) {
		System.out.println("\r''''''''''''''''''''AdminLogic''''''''''''''''''''");
        // DAOクラスをインスタンス化
		ItemDAO dao = new ItemDAO();
		// ■itemテーブルに商品の注文を反映
		boolean isNew = dao.newItemInfo(newItem);

		if (isNew == false) {
			return false;
		}
		return true;
	}


	public boolean execute2(ItemBean changeItem) {
		System.out.println("\r''''''''''''''''''''AdminLogic''''''''''''''''''''");
        // DAOクラスをインスタンス化
		ItemDAO dao = new ItemDAO();
		// ■itemテーブルに商品の注文を反映
		boolean isChange = dao.changeItemInfo(changeItem);

		if (isChange == false) {
			return false;
		}
		return true;
	}

	public boolean execute3(ItemBean changeItem) {
		System.out.println("\r''''''''''''''''''''AdminLogic''''''''''''''''''''");
        // DAOクラスをインスタンス化
		ItemDAO dao = new ItemDAO();
		// ■itemテーブルに商品の注文を反映
		boolean isChange = dao.deleteItemInfo(changeItem);

		if (isChange == false) {
			return false;
		}
		return true;
	}

>>>>>>> branch 'master' of https://github.com/G1tHub20/shopping.git
}
