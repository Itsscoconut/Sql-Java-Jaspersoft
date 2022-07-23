import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BD {
    String URL="", user="", pass="";
    Connection con;
    Statement stmt;
    ResultSet rs;

    BD(){
        URL = "jdbc:mysql://127.0.0.1/utp?userSSL=false"; //direccion del servidor
        user = "root"; //usuario de la base de dato
        pass = "pardo1234"; //contrasena de la base de dato
    }

    public void abrir(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, user, pass);
            stmt = con.createStatement();
        }catch(Exception e){
            System.out.println("ERROR"+e.toString());
        }
    }

    public void cerrar(){
        try {
            rs.close();
            stmt.close();
            con.close();
        }catch(Exception e){
            System.out.println("ERROR"+e.toString());
        }
    }

    public ResultSet executeQuery(String sql){
        try{
            abrir();
            rs = stmt.executeQuery(sql);
        }catch(Exception e){
            System.out.println("ERROR"+e.toString());
        }
        return rs;
    }

    public void executeUpdate(String sql){
        try{
            abrir();
            stmt.executeUpdate(sql);
            cerrar();
        }catch(Exception e){
            System.out.println("ERROR"+e.toString());
        }
    }

    public Connection con(){
        abrir();
        return con;
    }
}
