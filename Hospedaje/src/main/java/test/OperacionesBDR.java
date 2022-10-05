package test;

import beans.Reserva;
import connection.DBConnection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class OperacionesBDR {
    
    public static void main(String[] args) {
        
        listadoReserva();
        actualizarReserva(1);        
        
    }
    
    public static void actualizarReserva(int id_reserva){
        DBConnection con = new DBConnection();
        String sql = "UPDATE reserva SET id_reserva= "+id_reserva;
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
    
    public static void listadoReserva(){
        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM reserva";
        try {
           Statement st = con.getConnection().createStatement();
           ResultSet rs = st.executeQuery(sql);
           while(rs.next()){
              int id_reserva = rs.getInt("id_reserva");
              String username = rs.getString("username");
              int id_glamping = rs.getInt("id_glamping");
              Date fecha_ingreso = rs.getDate("fecha_ingreso");
              Date fecha_salida = rs.getDate("fecha_salida");
              
              
              Reserva reserva = new Reserva(id_reserva, username, id_glamping, fecha_ingreso, fecha_salida);
               System.out.println(reserva.toString());
                      
           }
                   
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally{
            con.desconectar();
        }
    }
}
