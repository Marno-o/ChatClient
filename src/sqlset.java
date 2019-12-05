import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class sqlset {
    // JDBC 驱动名及数据库 URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://www.yz457.top:3306/chatsys?characterEncoding=utf8&useSSL=true";
    // 数据库的用户名与密码
    private static final String USER = "user1";
    private static final String PASS = "password";

    //好友信息
    String username = null;
    private Connection conn = null;
    private PreparedStatement stmt = null;

    public sqlset() {
        //创建语句
        try {
            // 注册 JDBC 驱动
            System.out.println("注册 JDBC 驱动...");
            Class.forName(JDBC_DRIVER);
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("succeed!!");
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
    }

   /* public void findfriends(String id) {
        System.out.println("开始读取好友列表。。");
        String sql = "select id from friends_" + id;
        //list = null;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int num = rsmd.getColumnCount();
            while (rs.next()) {
                Map rowData = new HashMap();//声明Map
                for (int i = 1; i <= num; i++) {
                    rowData.put(rsmd.getColumnName(i), rs.getObject(i));//获取键名及值
                    System.out.println(rsmd.getColumnName(i) + rs.getObject(i));
                }
                //list.add(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    //获取好友列表及信息
    public String[][] dataq(String id) {
        String[][] dataq= null;
        System.out.println("开始读取好友列表。。");
        String sql = "select count(id) from userinfo where not id ='" + id + "'";
        String sql2 = "select name,ip,online from userinfo where not id ='" + id + "'";
        try {
            ResultSet rset2 = stmt.executeQuery(sql);
            int num = 0;
            if (rset2.next()) {
                num =rset2.getInt(1);//获取好友个数
            }
            dataq = new String[num][3];//
            System.out.println("几行："+num);
            ResultSet rs = stmt.executeQuery(sql2);
            int i = 0;
            while (rs.next()) {
                dataq[i][0] = rs.getString(1);
                System.out.println(rs.getString(1));
                dataq[i][1] = rs.getString(2);
                System.out.println(rs.getString(2));
                dataq[i][2] = rs.getString(3);
                System.out.println(rs.getString(3));
                System.out.println(dataq[i][0] + dataq[i][1]+ dataq[i][2]);
                i++;
            }
            for (int a = 0; a < num; a++) {
                System.out.println(dataq[a][0] + dataq[a][1]);
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataq;
    }

    public String username(String id) {
        System.out.println("开始查询用户id：" + id);
        String sql = "select name from users where id ='" + id + "'";
        String name = null;
        try {
            System.out.println("执行查询语句...");
            stmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
}
