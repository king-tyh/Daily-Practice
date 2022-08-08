package Liaoxuefeng;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class JDBC {
    static String URL = "jdbc:mysql://localhost:3306/learnjdbc?useSSL=false&characterEncoding=utf8";
    static String USER = "root";
    static String PASSWORD = "123456";

    static HikariConfig config = new HikariConfig();    //配置连接池
    static{
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.addDataSourceProperty("connectionTimeout","1000");
        config.addDataSourceProperty("idleTimeout","60000");
        config.addDataSourceProperty("maximumPoolSize","10");
    }

    public static void main(String[] args) throws SQLException {
        String[] name = {"大李", "大李", "大李", "大李", "大李"};
        int[] gender = {1, 1, 1, 1, 1};
        int[] grade = {1, 1, 1, 1, 1};
        int[] score = {80, 81, 82, 83, 84};
        Query("小%", 1);
        Insert(name,gender,grade,score);
        Query("%",1);
        Delete("大李");
        Query("%",1);
    }
    static DataSource ds = new HikariDataSource(config);   //创建连接池

    public static void Query(String name, int gender) throws SQLException {
        String sql = "SELECT * FROM students WHERE name like '" + name + "' AND gender='" + gender + "'";
        try (Connection conn = ds.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet res = stmt.executeQuery(sql)) {
                    while (res.next()) {
                        int stuId = res.getInt("id");
                        String stuName = res.getString("name");
                        int stuGender = res.getInt("gender");
                        int stuScore = res.getInt("score");
                        System.out.printf("Id:%d, Name:%s, Gender:%d, Score:%d\n", stuId, stuName, stuGender, stuScore);
                    }
                }
            }
        }
    }

    //使用Batch插入
    public static void Insert(String[] name, int[] gender, int[] grade, int[] score) throws SQLException {
        String sql = "INSERT INTO students (name, gender, grade, score) VALUES (?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                for(int i=0;i<name.length;i++){
                    stmt.setObject(1,name[i]);
                    stmt.setObject(2,gender[i]);
                    stmt.setObject(3,grade[i]);
                    stmt.setObject(4,score[i]);
                    stmt.addBatch();
                }
                int[] n=stmt.executeBatch();
                try (ResultSet res = stmt.getGeneratedKeys()) {
                    if(res.next()){
                        Long id = res.getLong(1);
                        System.out.println("插入成功");
                    }
                }
            }
        }
    }

    public static void Delete(String name) throws SQLException {
        try(Connection conn = ds.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE name=?")){
                ps.setObject(1,name);
                int n = ps.executeUpdate();
                System.out.printf("成功删除%d条数据\n", n);
            }
        }
    }

}



