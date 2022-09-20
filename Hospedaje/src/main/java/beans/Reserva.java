package beans;

import com.mysql.fabric.xmlrpc.base.Data;
import java.sql.Date;

public class Reserva {
    private int id_reserva;
    private String username;
    private int id_glamping;
    private Date fecha_ingreso;
    private Date fecha_salida;

    public Reserva(int id_reserva, String username, int id_glamping, Date fecha_ingreso, Date fecha_salida) {
        this.id_reserva = id_reserva;
        this.username = username;
        this.id_glamping = id_glamping;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_salida = fecha_salida;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId_glamping() {
        return id_glamping;
    }

    public void setId_glamping(int id_glamping) {
        this.id_glamping = id_glamping;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    @Override
    public String toString() {
        return "Reserva{" + "id_reserva=" + id_reserva + ", username=" + username + ", id_glamping=" + id_glamping + ", fecha_ingreso=" + fecha_ingreso + ", fecha_salida=" + fecha_salida + '}';
    }
    
    
            
}
