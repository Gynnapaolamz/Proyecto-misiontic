package beans;

public class Glamping {
    private int id_glamping;
    private String nombre;
    private String ubicacion;
    private String descripcion;
    private double valor;
    private int cantidad;
    private boolean estado;

    public Glamping(int id_glamping, String nombre, String ubicacion, String descripcion, double valor, int cantidad, boolean estado) {
        this.id_glamping = id_glamping;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.valor = valor;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public int getId_glamping() {
        return id_glamping;
    }

    public void setId_glamping(int id_glamping) {
        this.id_glamping = id_glamping;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Glamping{" + "id_glamping=" + id_glamping + ", nombre=" + nombre + ", ubicacion=" + ubicacion + ", descripcion=" + descripcion + ", valor=" + valor + ", cantidad=" + cantidad + ", estado=" + estado + '}';
    }
    
    
    
}
