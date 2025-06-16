package javaShoppingMall.model;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        // JDBC 드라이버 로드
        Class.forName("oracle.jdbc.driver.OracleDriver" );
        System.out.println("드라이버 적재 성공"); // DB 연결
        String filePath = "/Users/sp3ctrum/Documents/javastudy/KH/KH/src/javaShoppingMall/config/db.properties";
        Properties p = new Properties();
        p.load(new FileReader(filePath));
        System.out.println("파일로드 성공");
        String url = p.getProperty("url");
        String id = p.getProperty("id");
        String pwd = p.getProperty("pwd");

        return DriverManager.getConnection(url, id, pwd);
    }
}
