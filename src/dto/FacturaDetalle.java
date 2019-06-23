package dto;

public class FacturaDetalle {

    private int numeroFactura;
    private int idModelo;
    private String nombreModelo;
    private int cantidadComprada;
    private int precioModelo;


    //constructor necesario para reflexion
    public FacturaDetalle() {
    }

    @Override
    public String toString() {
        return this.nombreModelo + " "+this.cantidadComprada +" "+ this.idModelo +" "+ this.numeroFactura + " "+this.precioModelo;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public int getCantidadComprada() {
        return cantidadComprada;
    }

    public void setCantidadComprada(int cantidadComprada) {
        this.cantidadComprada = cantidadComprada;
    }

    public int getPrecioModelo() {
        return precioModelo;
    }

    public void setPrecioModelo(int precioModelo) {
        this.precioModelo = precioModelo;
    }
}