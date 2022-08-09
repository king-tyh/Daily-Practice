package Liaoxuefeng;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolAndConnectionPool {
    static final String URL = "jdbc:mysql://localhost:3306/learnjdbc?useSSL=false&characterEncoding=utf8";
    static final String USER = "root";
    static final String PASSWORD = "123456";
    static final HikariConfig config = new HikariConfig();    //配置连接池

    static {
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.addDataSourceProperty("connectionTimeout", "1000");
        config.addDataSourceProperty("idleTimeout", "60000");
        config.addDataSourceProperty("maximumPoolSize", "4");
    }

    static final DataSource cp = new HikariDataSource(config);   //创建连接池
    static final ExecutorService tp = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException, SQLException {
        Insert in = new Insert(cp);
        tp.submit(new InsertThread(in, "/insertData.txt"));
        tp.submit(new InsertThread(in, "/insertData2.txt"));
        tp.submit(new InsertThread(in, "/insertData3.txt"));
        tp.shutdown();
    }
}

class Insert extends Thread {
    final DataSource cp;

    Insert(DataSource cp) {
        this.cp = cp;
    }

    public void Query(String name, int gender) throws SQLException {
        String sql = "SELECT * FROM students";
        try (Connection conn = cp.getConnection()) {
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

    public int[] insert(List<String> datas) throws SQLException {
        //数据库操作
        try (Connection conn = cp.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO students (name,gender,grade,score) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS)) {//设置自增主键
                //循环传入的数据
                datas.remove(0);
                for (String data : datas) {
                    //得到一条数据的字段值
                    String[] attrs = data.split("\\s+");
                    System.out.println(Arrays.toString(attrs));
                    //设置字段值
                    ps.setObject(1, attrs[0]);
                    ps.setObject(2,Integer.parseInt(attrs[1]));
                    ps.setObject(3,Integer.parseInt(attrs[2]));
                    ps.setObject(4,Integer.parseInt(attrs[3]));
                    ps.addBatch();


                }
                int[] n = ps.executeBatch();
                try (ResultSet res = ps.getGeneratedKeys()) {
                    if (res.next()) {
                        Long id = res.getLong(1);
                        System.out.println("插入成功");
                    }
                    return n;
                }
            }
        }
    }

    public List<String> getDatas(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(
                new File(ThreadPoolAndConnectionPool.class.getResource(fileName).getFile()).toPath()), StandardCharsets.UTF_8))) {
            List<String> datas = new ArrayList<>();

            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                datas.add(s);
            }
            return datas;
        }

    }

}

class InsertThread extends Thread {
    final String fileName;
    final Insert insert;

    InsertThread(Insert insert, String fileName) {
        this.fileName = fileName;
        this.insert = insert;
    }

    @Override
    public void run() {
        try {
            insert.insert(insert.getDatas(this.fileName));
        } catch (Exception e) {
            System.out.println("Exception in run");
        }
    }
}