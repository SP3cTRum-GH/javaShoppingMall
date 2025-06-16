package javaShoppingMall.model;

import java.util.List;

public interface UserDAO {
    List<UserVO> selectAllUsers();
    UserVO searchUser(UserVO user);
    void addUser(UserVO addUser);
}
