package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    
    private static String  host = "85.136.228.2";  
    private String db = "clinica";
    private String user = "oreo";
    private String password = "oreo";
    private String url = "jdbc:mysql://"+host+"/"+db;
    private Connection conn = null;
    
    public conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(this.url, this.user , this.password);    
            System.out.println("Conexion MySQL realizada con exito.");
        }catch(SQLException e){
            System.out.println("Conexion NO realizada con exito por error de SQL.");
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            System.out.println("Conexion NO realizada con exito por error de Class.");
            e.printStackTrace();
        }
    }

    public Connection getConexion() {
        return this.conn;
    }
}