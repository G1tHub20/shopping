package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dao.UserDAO;
/**
 * アカウント登録を行うロジックモデル
 */
public class RegisterLogic {
	// アカウント登録
	public UserBean execute(String userName, String pass) {
		// パスワードをハッシュ化
		String hashedPass = null;
		try {
			hashedPass = build(pass);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// DBに登録
		UserBean user = new UserBean(userName, hashedPass);
		UserDAO dao = new UserDAO();
		UserBean loginUser = null;
		// 登録後、DBからアカウントを取得
		if (dao.registerUser(user)) {
			loginUser = dao.findUser(user);
		}
		return loginUser;
	}

	// ■ハッシュ化 // TODO:LoginLogicでも使用するので別クラスに作るべきかも
	public static String build (String pass) throws NoSuchAlgorithmException {
		StringBuilder buff = new StringBuilder();
		if (pass != null && !pass.isEmpty()) {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(pass.getBytes());
			byte[] digest = md.digest();

			for (byte d : digest) {
				buff.append((int)d&0xFF);
			}
		}
		// ハッシュ化したパスワードを返す
		return buff.toString();
		}

	// ■入力チェック ※register.jspで入力指定
	public static boolean isValidInput(String str) {
		return str.matches("^(?=.*[a-z|A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,15}$"); //正規表現（肯定の先読み）
	}
}
