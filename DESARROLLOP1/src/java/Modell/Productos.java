package Modell;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Evelyn
 */
public class Productos {
    private int idProducto, idMarca, existencia;
    private float precio_costo, precio_venta;
    private String producto, descripcion;
    private Conexion conec;

    public Productos(){
    }
    
    public Productos(int idProducto, int idMarca, int existencia, float precio_costo, float precio_venta, String producto, String descripcion) {
        this.idProducto = idProducto;
        this.idMarca = idMarca;
        this.existencia = existencia;
        this.precio_costo = precio_costo;
        this.precio_venta = precio_venta;
        this.producto = producto;
        this.descripcion = descripcion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public float getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(float precio_costo) {
        this.precio_costo = precio_costo;
    }

    public float getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(float precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            conec = new Conexion();
            conec.abrir_conexion();
            String query = "SELECT p.idProductos as id, p.producto, m.marca, p.Descripcion, p.precio_costo, p.precio_venta, p.existencia, m.idmarcas FROM productos as p inner join marcas as m on m.idmarcas = p.idMarca;";
            ResultSet consulta = conec.conexionBD.createStatement().executeQuery(query);
            String encabezado[]= {"id","producto","marca","Descripcion","precio_costo","precio_venta","existencia","idmarcas"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[8];
            while (consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("producto");
                datos[2] = consulta.getString("marca");
                datos[3] = consulta.getString("Descripcion");
                datos[4] = consulta.getString("precio_costo");
                datos[5] = consulta.getString("precio_venta");
                datos[6] = consulta.getString("existencia");
                datos[7] = consulta.getString("idmarcas");
                tabla.addRow(datos);                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            conec.cerrar_conexion();
            return tabla;
        }
    }
    
    public int agregar(){
        int retorno = 0;
        try{
            PreparedStatement parametro;
            conec = new Conexion();
            String query = "INSERT INTO productos (producto,idMarca,Descripcion,precio_costo,precio_venta,existencia) values (?,?,?,?,?,?);";
            conec.abrir_conexion();
            parametro = (PreparedStatement)conec.conexionBD.prepareStatement(query);
            parametro.setString(1, getProducto());
            parametro.setInt(2, getIdMarca());
            parametro.setString(3, getDescripcion());
            parametro.setFloat(4, getPrecio_costo());
            parametro.setFloat(5, getPrecio_venta());
            parametro.setInt(6, getExistencia());
            
            retorno = parametro.executeUpdate();
            conec.cerrar_conexion();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            retorno = 0;
        }finally{
            conec.cerrar_conexion();
            return retorno;
        }
    }
    
    public int modificar(){
        int retorno = 0;
        try{
            PreparedStatement parametro;
            conec = new Conexion();
            String query = "UPDATE productos set producto=?,idMarca=?,Descripcion=?,precio_costo=?,precio_venta=?,existencia=? WHERE idProductos = ?;";
            conec.abrir_conexion();
            parametro = (PreparedStatement)conec.conexionBD.prepareStatement(query);
            parametro.setString(1, getProducto());
            parametro.setInt(2, getIdMarca());
            parametro.setString(3, getDescripcion());
            parametro.setFloat(4, getPrecio_costo());
            parametro.setFloat(5, getPrecio_venta());
            parametro.setInt(6, getExistencia());
            parametro.setInt(7, getIdProducto());
            
            retorno = parametro.executeUpdate();
            conec.cerrar_conexion();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            retorno = 0;
        }finally{
            conec.cerrar_conexion();
            return retorno;
        }
    }
    
    public int eliminar(){
        int retorno = 0;
        try{
            PreparedStatement parametro;
            conec = new Conexion();
            String query = "DELETE FROM productos WHERE idProductos = ?;";
            conec.abrir_conexion();
            parametro = (PreparedStatement)conec.conexionBD.prepareStatement(query);
            parametro.setInt(1, getIdProducto());
            
            retorno = parametro.executeUpdate();
            conec.cerrar_conexion();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            retorno = 0;
        }finally{
            conec.cerrar_conexion();
            return retorno;
        }
    }
}
