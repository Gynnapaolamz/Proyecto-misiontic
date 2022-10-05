package controller;

public interface IGlampingController {

    public String listar(boolean ordenar, String orden);

    public String devolver(int id_reserva, String username);

    public String sumarCantidad(int id_glamping);

    public String reserva(int id_reserva, String username);

    public String modificar(int id_glamping);

}
