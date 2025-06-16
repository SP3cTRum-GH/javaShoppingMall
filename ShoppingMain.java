package javaShoppingMall;

import javaShoppingMall.controllar.MainController;
import javaShoppingMall.model.Database;

import java.util.Scanner;

public class ShoppingMain {
	public static void main(String[] args) {
		Database db = new Database();
		Scanner s = new Scanner(System.in);
		MainController mainController = new MainController(db);
		boolean flag = false;
		while (!flag) {
			Menu.memberManagerMenu();
			int choice = Integer.parseInt(s.nextLine());

			switch (choice) {
				case 1:
					mainController.login();
					break;
				case 2:
					mainController.signUp();
					break;
				case 3:
					flag = true;
					break;
				default:
					System.out.println("유효하지 않은 입력입니다.");
			}
		}
	}
}