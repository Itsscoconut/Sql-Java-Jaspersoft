import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;

public class Mysql implements ActionListener {
    BD db = new BD();
    JFrame ventana;
    DefaultListModel<String> listModel;
    JList<String> lst_cliente;
    JScrollPane sp_cliente;


    DefaultTableModel dtm_cliente;
    JTable jt_cliente;
    JScrollPane sp_cliente2;

    JButton btn_listar, btn_agregar, btn_buscar, btn_listar2, btn_escoger, btn_reporte;
    JLabel lbl_cedula, lbl_nombre, lbl_apellido, lbl_provincia;
    JTextField tf_cedula, tf_nombre, tf_apellido;

    JComboBox<String> provincia;
    Cliente cliente = new Cliente();
    Provincia pro = new Provincia();
    public static void main(String[] args) {
        new Mysql();
    }

    Mysql(){
        ventana = new JFrame("Mysql");
        ventana.setSize(700, 700);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);

        listModel = new DefaultListModel<String>();
        lst_cliente = new JList<String>(listModel);
        sp_cliente = new JScrollPane(lst_cliente);
        sp_cliente.setBounds(50, 50, 200, 200);
        ventana.add(sp_cliente);

        dtm_cliente = new DefaultTableModel();
        jt_cliente = new JTable(dtm_cliente);
        sp_cliente2 = new JScrollPane(jt_cliente);
        sp_cliente2.setBounds(50, 260, 200, 200);
        ventana.add(sp_cliente2);

        btn_listar = new JButton("listar");
        btn_listar.setBounds(260, 50, 80, 20);
        btn_listar.addActionListener(this);
        ventana.add(btn_listar);

        btn_listar2 = new JButton("listar2");
        btn_listar2.setBounds(260, 260, 80, 20);
        btn_listar2.addActionListener(this);
        ventana.add(btn_listar2);

        btn_escoger = new JButton("escoger");
        btn_escoger.setBounds(260, 285, 80, 20);
        btn_escoger.addActionListener(this);
        ventana.add(btn_escoger);

        lbl_cedula = new JLabel("Cedula: ");
        lbl_cedula.setBounds(350, 50, 80, 20);
        ventana.add(lbl_cedula);

        tf_cedula = new JTextField();
        tf_cedula.setBounds(435, 50, 80, 20);
        ventana.add(tf_cedula);

        lbl_nombre = new JLabel("Nombre: ");
        lbl_nombre.setBounds(350,75,80,20);
        ventana.add(lbl_nombre);

        tf_nombre = new JTextField();
        tf_nombre.setBounds(435, 75, 80, 20);
        ventana.add(tf_nombre);

        lbl_apellido = new JLabel("Apellido: ");
        lbl_apellido.setBounds(350,100,80,20);
        ventana.add(lbl_apellido);

        tf_apellido = new JTextField();
        tf_apellido.setBounds(435, 100, 80, 20);
        ventana.add(tf_apellido);

        lbl_provincia = new JLabel("Provincia");
        lbl_provincia.setBounds(350, 125, 80, 20);
        ventana.add(lbl_provincia);

        provincia = new JComboBox<String>();
        pro.cargar(provincia);
        provincia.setBounds(435, 125, 120, 20);
        ventana.add(provincia);

        btn_buscar = new JButton("buscar");
        btn_buscar.setBounds(520, 50, 80, 20);
        btn_buscar.addActionListener(this);
        ventana.add(btn_buscar);

        btn_agregar = new JButton("Agregar");
        btn_agregar.setBounds(520, 75, 80, 20);
        btn_agregar.addActionListener(this);
        ventana.add(btn_agregar);

        btn_reporte = new JButton("Reporte");
        btn_reporte.setBounds(520, 150, 100, 20);
        btn_reporte.addActionListener(this);
        ventana.add(btn_reporte);

        ventana.setVisible(true);
    }


    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btn_listar){
            listar();
        }
        if(e.getSource() == btn_agregar){
            agregar();
        }
        if(e.getSource() == btn_buscar){
            buscar();
        }
        if (e.getSource() == btn_listar2) {
            listar2();
        }
        if(e.getSource() == btn_escoger){
            escoger();
        }
        if(e.getSource() == btn_reporte){
            reporte();
        }
    }

    public void listar(){
        cliente.listar(listModel);
    }

    public void agregar(){
        cliente.setCedula(tf_cedula.getText());
        cliente.setNombre(tf_nombre.getText());
        cliente.setApellido(tf_apellido.getText());
        cliente.agregar();
    }


    public void buscar(){
        if(cliente.buscar(tf_cedula.getText())){
            btn_agregar.setEnabled(false);
        }else{
            btn_agregar.setEnabled(true);
        }

        tf_cedula.setText(cliente.getCedula());
        tf_nombre.setText(cliente.getNombre());
        tf_apellido.setText(cliente.getApellido());
        provincia.setSelectedItem(cliente.getNombreProvincia());
    }

    public void listar2(){
        cliente.listar(dtm_cliente);
    }

    public void escoger(){
        int fila;
        fila = jt_cliente.getSelectedRow();
        tf_cedula.setText(jt_cliente.getValueAt(fila,0).toString());
        buscar();
    }

    public void reporte(){
        try{
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\ProyectoFinal\\ProyectoFinal\\ProyectoFinal\\src\\Reporte1_utp.jasper",null,db.con());
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch(Exception e){
            System.out.println("ERROR"+e.toString());
        }
    }
}