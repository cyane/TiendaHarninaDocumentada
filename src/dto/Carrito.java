package dto;

public class Carrito {
    private int IdModelo;
    private int IdCliente;
    private int cantidadPedida;
    private double actualPrecioModelo;
    private String nombreModelo;


    public Carrito(int idModelo, int idCliente, int cantidadPedida, double actualPrecioModelo) {
        IdModelo = idModelo;
        IdCliente = idCliente;
        this.cantidadPedida = cantidadPedida;
        this.actualPrecioModelo = actualPrecioModelo;
    }

    @Override
    public String toString() {
        return "Datos carrito: IdModelo: "+getIdModelo()+" IdCliente: "+getIdCliente()+" cantidad: "+cantidadPedida+" precio: "+actualPrecioModelo;
    }
    //constructor necesario para reflexion
    public Carrito() {
    }

    public int getIdModelo() {
        return IdModelo;
    }

    public void setIdModelo(int idModelo) {
        IdModelo = idModelo;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public int getCantidadPedida() {
        return cantidadPedida;
    }

    public void setCantidadPedida(int cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public double getActualPrecioModelo() {
        return actualPrecioModelo;
    }

    public void setActualPrecioModelo(double actualPrecioModelo) {
        this.actualPrecioModelo = actualPrecioModelo;
    }


}
