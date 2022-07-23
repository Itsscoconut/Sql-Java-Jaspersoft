import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
public class Cliente {
    private String cedula, nombre, apellido, provincia;
    private String sql;
    BD db = new BD();
    Provincia prov = new Provincia();

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void inicializar(){
        cedula="";
        nombre="";
        apellido="";
    }

    public void listar(DefaultListModel<String> listModel){
        sql="";

        try{
            sql = "select * from cliente";
            ResultSet rs = db.executeQuery(sql);
            listModel.clear();

            while(rs.next()){
                cedula = rs.getString("cedula");
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                listModel.addElement(cedula+" "+nombre+" "+apellido);
            }

            //cerrar todos lo elemnto de la base de datos
            db.cerrar();

        }catch(Exception e){
            System.out.println("ERROR"+e.toString());
        }
    }

    public void listar(DefaultTableModel dtm){
        sql="";
        dtm.setColumnCount(0);
        dtm.setRowCount(0);

        dtm.addColumn("cedula");
        dtm.addColumn("nombre");
        dtm.addColumn("apellido");
        dtm.addColumn("provincia");

        Object [] fila = new Object[4];

        try{
            sql = "select * from cliente, provincia where cliente.provincia = provincia.codigo";
            ResultSet rs = db.executeQuery(sql);

            while(rs.next()){
                fila[0] = rs.getString("cedula");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido");
                fila[3] = rs.getString("descripcion");
                dtm.addRow(fila);
            }

            //cerrar todos lo elemnto de la base de datos
            db.cerrar();

        }catch(Exception e){
            System.out.println("ERROR"+e.toString());
        }
    }

    public boolean buscar(String cedula){
        boolean encontrado = false;
        inicializar();

        try{
            sql = "select * from cliente where cedula='"+cedula+"'";
            ResultSet rs = db.executeQuery(sql);
            this.cedula = cedula;

            if (rs.next()){
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                provincia = rs.getString("provincia");
                encontrado = true;
            }

            db.cerrar();

        }catch(Exception e){
            System.out.println("ERROR"+e.toString());
        }
        return encontrado;
    }

    public void agregar(){
        try{
            sql = "insert into cliente (cedula, nombre, apellido) values ('";
            sql = sql + cedula +"','"+nombre +"','";
            sql = sql + apellido+"')";
            System.out.println(sql);
            db.executeUpdate(sql);

        }catch(Exception e){
            System.out.println("ERROR"+e.toString());
        }
    }

    public String getNombreProvincia(){
        return prov.getDescripcion(provincia);
    }
}
