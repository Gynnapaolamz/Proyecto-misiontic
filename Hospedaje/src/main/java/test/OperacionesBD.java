package test;

import beans.Glamping;
import connection.DBConnection;
import java.sql.ResultSet;
import java.sql.Statement;

public class OperacionesBD {
    public static void main(String[] args) {
        
        actualizarGlamping(2, "Casa del Arbol");
        listadoGlamping();
        
    }
    
    public static void actualizarGlamping(int id_glamping, String nombre){
        DBConnection con = new DBConnection();
        String sql = "UPDATE glamping SET nombre = ' "+nombre+"'WHERE id_glamping = "+id_glamping;
        try {
           Statement st = con.getConnection().createStatement();
           st.executeUpdate(sql);
           
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally{
            con.desconectar();
        }
    }
    
    public static void listadoGlamping(){
        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM glamping";
        try {
           Statement st = con.getConnection().createStatement();
           ResultSet rs = st.executeQuery(sql);
           while(rs.next()){
              int id_glamping = rs.getInt("id_glamping");
              String nombre = rs.getString("nombre");
              String ubicacion = rs.getString("ubicacion");
              String descripcion = rs.getString("descripcion");
              double valor = rs.getDouble("valor");
              int cantidad = rs.getInt("cantidad");
              boolean estado = rs.getBoolean("estado");
              
              Glamping glamping = new Glamping(id_glamping,nombre,ubicacion,descripcion,valor,cantidad,estado);
               System.out.println(glamping.toString());
                      
           }
                   
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally{
            con.desconectar();
        }
    }
}
