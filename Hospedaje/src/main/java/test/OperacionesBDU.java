package test;

import beans.Usuario;
import connection.DBConnection;
import java.sql.ResultSet;
import java.sql.Statement;

public class OperacionesBDU {
    
     public static void main(String[] args) {
        
        actualizarUsuario("Andrea","AAAAAAAA");
        
        
    }
    
    public static void actualizarUsuario(String username, String nombre){
        DBConnection con = new DBConnection();
        String sql = "UPDATE usuario SET nombre= ' "+nombre+"' WHERE username= "+username;
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
    
    public static void listadoUsuario(){
        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM usuario";
        try {
           Statement st = con.getConnection().createStatement();
           ResultSet rs = st.executeQuery(sql);
           while(rs.next()){
              String username= rs.getString("username");
              String contrasena = rs.getString("contrasena");
              String nombre = rs.getString("nombre");
              String apellido = rs.getString("apellido");
              String email = rs.getString("email");
              String telefono = rs.getString("telefono");
              double saldo = rs.getDouble("saldo");
              boolean premium = rs.getBoolean("premium");
              
              Usuario usuario = new Usuario(username,contrasena,nombre,apellido,email,telefono,saldo,premium);
               System.out.println(usuario.toString());
                      
           }
                   
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally{
            con.desconectar();
        }
    }

}
