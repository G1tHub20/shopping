package model;

import servlet.LoginServlet;

public class LoginLogicTest {
	public static void main(String[] args) {
		testExecute1();
	}

	public static void testExecute1() {
		LoginServlet login = new LoginServlet("tanaka", "tanaka20");
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(login);
		if(result) {
			System.out.println("testExecute：成功しました");
		} else {
			System.out.println("testExecute：しっぱい");
		}
	}

}
