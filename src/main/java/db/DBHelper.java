package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBHelper {
    private static final String url = "jdbc:mysql://localhost:3306/java4interface?useUnicode=true&characterEncoding=UTF-8";
    private static final String name = "com.mysql.jdbc.Driver";
    private static  final  String user = "root";
    private static  final  String password = "root";
    private Connection connection = null;
    public PreparedStatement preparedStatement = null;

    public DBHelper(String sql) {
        try{
            Class.forName(name);
            connection = DriverManager.getConnection(url,user,password);
            preparedStatement =  connection.prepareStatement(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void close(){
        try{
            this.connection.close();
            this.preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
