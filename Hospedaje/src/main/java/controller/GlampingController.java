package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import beans.Glamping;
import connection.DBConnection;

public class GlampingController implements IGlampingController {

    @Override
    public String listar(boolean ordenar, String orden) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Select * from glamping";

        if (ordenar == true) {
            sql += " order by ubicacion " + orden;
        }

        List<String> glampings = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                int id_glamping = rs.getInt("id_glamping");
                String nombre = rs.getString("nombre");
                String ubicacion = rs.getString("ubicacion");
                String descripcion = rs.getString("descripcion");
                double valor = rs.getDouble("valor");
                int cantidad = rs.getInt("cantidad");
                boolean estado = rs.getBoolean("estado");

                Glamping glamping = new Glamping(id_glamping, nombre,ubicacion, descripcion, valor, cantidad, estado);

                glampings.add(gson.toJson(glamping));

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return gson.toJson(glampings);

    }
    
     @Override
    public String devolver(int id_reserva, String username) {

        DBConnection con = new DBConnection();
        String sql = "Delete from reserva where id_reserva= " + id_reserva + " and username = '" 
                + username + "' limit 1";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeQuery(sql);

            this.sumarCantidad(id_reserva);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";
    }

    @Override
    public String sumarCantidad(int id_glamping) {

        DBConnection con = new DBConnection();

        String sql = "Update glamping set cantidad = (Select cantidad from glamping where id_glamping = " 
                + id_glamping + ") + 1 where id_glamping = " + id_glamping;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";

    }
    
    
    @Override
    public String reserva(int id_reserva, String username) {

        Timestamp fecha_ingreso = new Timestamp(new Date().getTime());
        DBConnection con = new DBConnection();
        String sql = "Insert into reserva values ('" + id_reserva + "', '" + username + "', '" + fecha_ingreso + "')";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            String modificar = modificar(id_reserva);

            if (modificar.equals("true")) {
                return "true";
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }
        return "false";
    }

     @Override
    public String modificar(int id_glamping) {

        DBConnection con = new DBConnection();
        String sql = "Update glamping set cantidad = (cantidad - 1) where id_glamping = " + id_glamping;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";

    }
}