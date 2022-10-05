package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.google.gson.Gson;

import beans.Reserva;
import connection.DBConnection;

public class ReservaController implements IReservaController {

    @Override
    public String listaReservas(String username) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();

        String sql = "Select l.id_glamping, l.nombre, l.ubicacion, l.estado, r.fecha from glamping l "
                + "inner join reserva r on l.id_glamping = r.id_reserva inner join usuario u on r.username = u.username "
                + "where r.username = '" + username + "'";

        List<String> reservas = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id_glamping = rs.getInt("id_glamping");
                String nombre = rs.getString("nombre");
                String ubicacion = rs.getString("ubicacion");
                boolean estado = rs.getBoolean("estado");
                Date fechaAlquiler = rs.getDate("fecha_ingreso");

                Reserva reserva = new Reserva(id_glamping, nombre, fechaAlquiler, estado, ubicacion);

                reservas.add(gson.toJson(reserva));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return gson.toJson(reservas);
    }
}
