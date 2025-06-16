package javaShoppingMall;

import javaShoppingMall.model.CategoryVO;
import javaShoppingMall.model.CouponVO;
import javaShoppingMall.model.UserVO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ProcessManager {
//	static Scanner scan = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
	private static ProcessManager instance = new ProcessManager();

	private ProcessManager() {
	}

	public static ProcessManager getInstance() {
		return instance;
	}



	public int selectNo() {
		Scanner scan = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		int choice = 0;
		while (true) {
			System.out.print("번호 선택>");
			String input = scan.nextLine();
			boolean isInputCheck = Pattern.matches("^(1[0-2]|[1-9])$", input);
			if (isInputCheck) {
				choice = Integer.parseInt(input);
				break;
			}
			System.out.println("유효한 번호를 입력해주세요");
		}
		return choice;
	}



	public UserVO login(ArrayList<UserVO> userInfoList) throws IOException {
		Scanner scan = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		boolean stopFlag = false;
		while (!stopFlag) {
			System.out.print("번호 선택(1.login/2.sign up/3.end)>>");
			int no = selectNo();
			switch (no) {
			case 1:
				for(UserVO i : userInfoList) {
					System.out.println(i);
				}
				int attempt = 0;
				while (attempt < 3) {
					System.out.println("==== 로그인 ====");
					System.out.print("id :");
					String id = scan.nextLine();
					System.out.print("password :");
					String pwd = scan.nextLine();
					for (UserVO userInfo : userInfoList) {
						if (userInfo.getId().equals(id) && userInfo.getPwd().equals(pwd)) {
							System.out.println(userInfo.getName()+"님 환영합니다.");
							return userInfo;
						}
					}
					System.out.println("로그인 실패. 아이디나 패스워드가 틀렸습니다.");
					attempt++;
				}
				if (attempt >= 3) {
					System.out.println("로그인 3회 실패, 초기화면으로 돌아갑니다.");
				}

				break;
			case 2:

				System.out.print("이름을 입력해주세요.");
				String name = scan.nextLine();
				System.out.println(name);
				String id = null;
				UserVO userInfo = null;

				while (true) {
					System.out.print("사용하실 아이디(영문자+숫자)를 입력해주세요.");
					id = scan.nextLine();
					boolean isIdInputCheck = id.matches("^[a-zA-Z0-9]+$");
					if (!isIdInputCheck) {
						System.out.println("유효한 값을 입력하세요.");
						continue;
					}
					break;

				}

				String pwd = null;
				while (true) {
					System.out.print("사용하실 비밀번호(4자리 이상 숫자)를 입력해주세요.");
					pwd = scan.nextLine();
					boolean isPwdInputCheck = pwd.matches("^[0-9]{4,}$");
					if (!isPwdInputCheck) {
						System.out.println("유효한 값을 입력하세요.");
						continue;
					}
					break;
				}
				System.out.print("비밀번호 확인(재입력)>");
				String chekingdPwd = scan.nextLine();

				if (pwd.equals(chekingdPwd)) {
					System.out.println(name);
					userInfo = new UserVO(id, chekingdPwd, name);
					userInfoList.add(userInfo);
					stopFlag = false;
					break;
				} else {
					System.out.println("비밀번호가 일치하지 않습니다.");
				} 
				FileOutputStream fo = new FileOutputStream("res/userInfo.txt", true);
				OutputStreamWriter o = new OutputStreamWriter(fo, "UTF-8");
				BufferedWriter bw = new BufferedWriter(o);

				try {
					bw.write("\n"+userInfo.getId()+","+userInfo.getPwd()+","+userInfo.getName());
				} catch (IOException e) {
					System.out.println("error");
				}
				bw.close();
				o.close();
				try {
					fo.close();
				} catch (IOException e) {
				}
			
				break;
			case 3:
				stopFlag = true;
				break;
			}
		}
		return null;
	}

	public void adminAddProduct(ArrayList<CategoryVO> shoppingList) {
		Scanner scan = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		System.out.print("추가하실 옷을 입력하세요.");
		String name = scan.nextLine();
		System.out.print("추가하실 옷의 분류를 입력해주세요.");
		char category = scan.nextLine().charAt(0);
		System.out.print("추가하실 옷의 착용성별(M,W,U)을 입력해주세요.");
		char clothegender = scan.nextLine().charAt(0);
		System.out.print("추가하실 옷의 사이즈(S,M,L,Free)를 입력해주세요.");
		String size = scan.nextLine();
		System.out.print("추가하실 옷의 가격을 입력해주세요.");
		int price = Integer.parseInt(scan.nextLine());
		CategoryVO s = new CategoryVO(name, category, clothegender, size, price);
		shoppingList.add(s);

	}

	public void adminRemoveProduct(ArrayList<CategoryVO> shoppingList, ArrayList<CategoryVO> cart) {
		Scanner scan = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		boolean removeFlag = false;
		System.out.print("장바구니에서 삭제할 제품의 이름을 입력>");
		String removeData = scan.nextLine();
		for (int i = 0; i < shoppingList.size(); i++) {
			if (cart.get(i).getName().equals(removeData)) {
				System.out.printf("%s의 상품정보를 삭제합니다.\n", removeData);
				shoppingList.remove(i);
				removeFlag = true;
			}
		}
		if (removeFlag == false) {
			System.out.printf("%s의 상품정보를 찾지 못했습니다.\n", removeData);

		}

	}

	public void cartProductDelete(ArrayList<CategoryVO> cart) {
		Scanner scan = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		boolean removeFlag = false;
		System.out.print("장바구니에서 삭제할 제품의 이름을 입력>");
		String removeData = scan.nextLine();
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getName().equals(removeData)) {
				System.out.printf("%s의 상품정보를 삭제합니다.\n", removeData);
				cart.remove(i);
				removeFlag = true;
			}
		}
		if (removeFlag == false) {
			System.out.printf("%s의 상품정보를 찾지 못했습니다.\n", removeData);
		}
	}

	public void DiscountCouponApply(ArrayList<CategoryVO> cart, ArrayList<CouponVO> couponList) {
		Scanner scan = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		for (int i = 0; i < cart.size(); i++) {
			System.out.println(cart.get(i).toString());
		}
		System.out.print("할인쿠폰을 적용할 상품을 선택해주세요.");
		String selectedItemName = scan.nextLine();
		for (int i = 0; i < couponList.size(); i++) {
			System.out.println(couponList.get(i).toString());
		}
		System.out.print("적용할 할인쿠폰을 선택해주세요.");
		String selectedCouponName = scan.nextLine();

		CouponVO selectedCoupon = null;
		CategoryVO selectedItem = null;

		for (CouponVO c : couponList) {
//			if (c.getName().equals(selectedCouponName)) {
//				selectedCoupon = c;
//				break;
//			}
		}
		for (CategoryVO s : cart) {
			if (s.getName().equals(selectedItemName)) {
				selectedItem = s;
				break;
			}
		}
		if (selectedCoupon != null && selectedItem != null) {
		} else {
			System.out.println("해당 쿠폰이나 상품을 찾지 못했습니다.");
		}
	}

	public void cartPayMent(ArrayList<CategoryVO> cart) {
		Scanner scan = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		System.out.print("결제 진행하시겠습니까?(yes/no)");
		String response = scan.nextLine();
		if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("Yes")) {
			for (int i = 0; i < cart.size(); i++) {
				int paymentPrice = 0;
				paymentPrice = paymentPrice + cart.get(i).getPrice();
				System.out.printf("합산금액 %d원을 결제합니다.\n", paymentPrice);
			}
		} else if (response.equalsIgnoreCase("no") || response.equalsIgnoreCase("No")) {
			return;
		} else {
			System.out.println("유효한 값을 입력해주세요.");
		}

	}

	public void cartItemAdd(ArrayList<CategoryVO> shoppingList, ArrayList<CategoryVO> cart) {
		Scanner scan = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		for (int i = 0; i < shoppingList.size(); i++) {
			System.out.println(shoppingList.get(i).toString());
		}
		System.out.print("장바구니에 추가할 항목을 입력해주세요.");
		String addItem = scan.nextLine();
		boolean foundFlag = false;
		for (CategoryVO shopping : shoppingList) {
			if (shopping.getName().equals(addItem)) {
				cart.add(shopping);
				foundFlag = true;
			}
		}
		if (foundFlag == false) {
			System.out.println("해당 상품은 List에 없습니다.");
		}

	}

	public void clear() {
		Scanner scan = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		System.out.print("continue>");
		scan.nextLine();
		try {
			String operatingSystem = System.getProperty("os.name");
			if (operatingSystem.contains("Windows")) {
				ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
				Process startProcess = pb.inheritIO().start();
				startProcess.waitFor();
			} else {
				ProcessBuilder pb = new ProcessBuilder("clear");
				Process startProcess = pb.inheritIO().start();
				startProcess.waitFor();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void productListView(ArrayList<CategoryVO> shoppingList) {
		Scanner scan = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		int page = 1;
		int pageSize = 5;
		while (true) {
			int totalPage = shoppingList.size() / pageSize;
			int remainValue = shoppingList.size() % 5;
			if (remainValue != 0) {
				totalPage += 1;
			}
			// 해당되는 페이지 시작위치, 끝위치
			int start = pageSize * (page - 1);
			int stop = pageSize * (page - 1) + pageSize;

			// 마지막 페이지일때 나머지값이 있을때 끝위치 1~4증가
			if (page == totalPage && remainValue != 0) {
				stop = pageSize * (page - 1) + remainValue;
			}

			System.out.printf("전체 %dpage/현재 %dpage \n", totalPage, page);
			for (int i = start; i < stop; i++) {
				System.out.println(shoppingList.get(i).toString());
			}
			System.out.printf("(-1:exit)페이지선택");
			page = Integer.parseInt(scan.nextLine());
			if (page == -1) {
				break;
			}
		}

	}
}
