import java.sql.*;
import javax.swing.*;

public class Provincia {
    String codigo, descripcion, nombre;

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion(String cod){
        String sql;
        ResultSet rs;

        try{
            BD db = new BD();
            sql = "select * from provincia where codigo='"+cod+"'";
            rs = db.executeQuery(sql);

            if(rs.next()){
                codigo = rs.getString("codigo");
                descripcion = rs.getString("descripcion");
            }
            db.cerrar();
        }catch(Exception e){
            System.out.println("ERROR"+e.toString());
        }
        return descripcion;

    }


    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public void cargar(JComboBox<String> cb){
        String sql;
        ResultSet rs; //lectura
        try{
            BD db = new BD();
            sql = "select * from provincia order by codigo";
            rs = db.executeQuery(sql);

            while(rs.next()){
                cb.addItem(rs.getString(("descripcion")));
            }
            db.cerrar();
        }catch(Exception e){
            System.out.println("ERROR"+e.toString());
        }
    }
}
