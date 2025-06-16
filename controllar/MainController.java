package javaShoppingMall.controllar;

import javaShoppingMall.model.Database;
import javaShoppingMall.model.UserVO;
import javaShoppingMall.view.admin.AdminPage;
import javaShoppingMall.view.user.UserMenu;

import java.util.Scanner;

public class MainController {

    private final Scanner scan = new Scanner(System.in);
    private final Database db;

    public MainController(Database db) {
        this.db = db;
    }
    public void login() {
        int attempt = 0;
        while (attempt < 3) {
            UserVO loginUser = new UserVO();
            System.out.println("==== 로그인 ====");
            System.out.print("id : ");
            loginUser.setId(scan.nextLine());
            System.out.print("password : ");
            loginUser.setPwd(scan.nextLine());

            UserVO searchUser = db.userDAO.searchUser(loginUser);
            if (searchUser != null &&
                    loginUser.getId().equals(searchUser.getId()) &&
                    loginUser.getPwd().equals(searchUser.getPwd())) {

                if ("admin".equals(searchUser.getId())) {
                    System.out.println("관리자 페이지로 이동합니다.");
                    new AdminPage(db).adminPage();
                } else {
                    System.out.println(searchUser.getName() + "님 환영합니다.");
                    new UserMenu(db, searchUser).userMenu();
                }
                return;
            }

            System.out.println("로그인 실패, 아이디나 비밀번호가 틀렸습니다.");
            attempt++;
        }

        System.out.println("로그인 3회 실패, 초기화면으로 돌아갑니다.");
    }

    public void signUp() {
        UserVO userInfo = new UserVO();

        System.out.print("이름을 입력해주세요: ");
        userInfo.setName(scan.nextLine());

        String id;
        while (true) {
            System.out.print("사용하실 아이디(영문자+숫자)를 입력해주세요: ");
            id = scan.nextLine();
            if (id.matches("^[a-zA-Z0-9]+$")) {
                break;
            }
            System.out.println("유효한 값을 입력하세요.");
        }

        String pwd;
        while (true) {
            System.out.print("사용하실 비밀번호(4자리 이상 숫자)를 입력해주세요: ");
            pwd = scan.nextLine();
            if (pwd.matches("^[0-9]{4,}$")) {
                break;
            }
            System.out.println("유효한 값을 입력하세요.");
        }

        while (true) {
            System.out.print("비밀번호 확인(재입력): ");
            String checkingPwd = scan.nextLine();
            if (pwd.equals(checkingPwd)) {
                userInfo.setId(id);
                userInfo.setPwd(pwd);
                break;
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.");
            }
        }

        db.userDAO.addUser(userInfo);
        System.out.println("회원가입이 완료되었습니다.");
    }
}