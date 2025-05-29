package Clases;

import java.util.ArrayList;
import java.util.Date;

public class OrdenDeCompra {

    private Cliente destinatario;
    private ArrayList<Mineral> minerales;
    private Date fecha;
    private Double total;

    public Cliente getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Cliente destinatario) {
        this.destinatario = destinatario;
    }

    public ArrayList<Mineral> getMinerales() {
        return minerales;
    }

    public void setMinerales(ArrayList<Mineral> minerales) {
        this.minerales = minerales;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void emitirOrden(){}

    @Override
    public String toString() {
        return "OrdenDeCompra{" +
                "destinatario=" + destinatario +
                ", minerales=" + minerales +
                ", fecha=" + fecha +
                ", total=" + total +
                '}';
    }
}
