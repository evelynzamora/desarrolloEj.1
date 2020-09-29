package Modell;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Evelyn
 */
public class Marcas {
    private int idMarca;
    private String marca;
    private Conexion conec;

    public Marcas(){
    }
    
    public Marcas(int idMarca, String marca) {
        this.idMarca = idMarca;
        this.marca = marca;
    }    
    
    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public HashMap drop_marca(){
        HashMap <String,String> drop = new HashMap();
        try{
            String query ="SELECT idmarcas as id, marca FROM marcas;";
            conec = new Conexion();
            conec.abrir_conexion();
            ResultSet consulta = conec.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("marca"));
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            conec.cerrar_conexion();
        }
        return drop;
    }
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            conec = new Conexion();
            conec.abrir_conexion();
            String query = "SELECT idmarcas as id, marca from marcas;";
            ResultSet consulta = conec.conexionBD.createStatement().executeQuery(query);
            String encabezado[]= {"id","marca"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[2];
            while (consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("marca");
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
            String query = "INSERT INTO marcas (marca) values (?);";
            conec.abrir_conexion();
            parametro = (PreparedStatement)conec.conexionBD.prepareStatement(query);
            parametro.setString(1, getMarca());
            
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
            String query = "UPDATE marcas set marca=? WHERE idmarcas = ?;";
            conec.abrir_conexion();
            parametro = (PreparedStatement)conec.conexionBD.prepareStatement(query);
            parametro.setString(1, getMarca());
            parametro.setInt(2, getIdMarca());
            
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
            String query = "DELETE FROM marcas WHERE idmarcas = ?;";
            conec.abrir_conexion();
            parametro = (PreparedStatement)conec.conexionBD.prepareStatement(query);
            parametro.setInt(1, getIdMarca());
            
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
