package dto;

import java.util.ArrayList;

public class FacturaCabecera {

    private int numeroFactura;
    private String nombreEmpresaFactura;
    private String domicilioEmpresaFactura;
    private String cifEmpresaFactura;
    private String nombreClienteFactura;
    private String domicilioClienteFactura;
    private String dniClienteFactura;
    private int iva;
    private ArrayList<FacturaDetalle> lineasFactura;
    private String fechaFacturacion;

    //constructor necesario para reflexion
    public FacturaCabecera() {
    }

    public String getFechaFacturacion() {
        return fechaFacturacion;
    }

    @Override
    public String toString() {
        return this.cifEmpresaFactura + this.dniClienteFactura + this.domicilioClienteFactura + this.domicilioEmpresaFactura +
                this.fechaFacturacion + this.nombreClienteFactura + this.nombreEmpresaFactura + this.iva  +
                this.numeroFactura;
    }

    public void setFechaFacturacion(String fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public ArrayList<FacturaDetalle> getLineasFactura() {
        return lineasFactura;
    }

    public void setLineasFactura(ArrayList<FacturaDetalle> lineasFactura) {
        this.lineasFactura = lineasFactura;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getNombreEmpresaFactura() {
        return nombreEmpresaFactura;
    }

    public void setNombreEmpresaFactura(String nombreEmpresaFactura) {
        this.nombreEmpresaFactura = nombreEmpresaFactura;
    }

    public String getDomicilioEmpresaFactura() {
        return domicilioEmpresaFactura;
    }

    public void setDomicilioEmpresaFactura(String domicilioEmpresaFactura) {
        this.domicilioEmpresaFactura = domicilioEmpresaFactura;
    }

    public String getCifEmpresaFactura() {
        return cifEmpresaFactura;
    }

    public void setCifEmpresaFactura(String cifEmpresaFactura) {
        this.cifEmpresaFactura = cifEmpresaFactura;
    }

    public String getNombreClienteFactura() {
        return nombreClienteFactura;
    }

    public void setNombreClienteFactura(String nombreClienteFactura) {
        this.nombreClienteFactura = nombreClienteFactura;
    }

    public String getDomicilioClienteFactura() {
        return domicilioClienteFactura;
    }

    public void setDomicilioClienteFactura(String domicilioClienteFactura) {
        this.domicilioClienteFactura = domicilioClienteFactura;
    }

    public String getDniClienteFactura() {
        return dniClienteFactura;
    }

    public void setDniClienteFactura(String dniClienteFactura) {
        this.dniClienteFactura = dniClienteFactura;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }
}
