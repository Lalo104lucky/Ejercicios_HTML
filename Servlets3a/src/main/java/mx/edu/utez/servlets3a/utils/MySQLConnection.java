package mx.edu.utez.servlets3a.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    final String HOST = "localhost", PORT = "3306", DBNAME = "utex", USERNAME = "root", PASSWORD = "root", TIMEZONE = "America/Mexico_City", USESSL = "false", PUBLICKEY = "true", DDLAUTO = "true";

    public Connection getConnection() {
        String dataSource = String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s&serverTimezone=%s&useSSL=%s&allowPublicKeyRetrieval=%s&createDatabaseIfNotExist=%s", HOST, PORT, DBNAME, USERNAME, PASSWORD, TIMEZONE, USESSL, PUBLICKEY, DDLAUTO);
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            return DriverManager.getConnection(dataSource);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            Connection conn = new MySQLConnection().getConnection();
            if (conn != null){
                System.out.println("Conexion exitosa :3");
                conn.close();
            }else {
                System.out.printf("Chale :( el profe no explica bien");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}