package BLL.Clases;

import DLL.Repository.OrdenesRepository;

import java.util.ArrayList;
import java.util.Date;

public class OrdenDeCompra {

    private int idOrden;
    private Cliente destinatario;
    private ArrayList<Mineral> minerales;
    private Date fecha;
    private Double total;
    private Double peso;
    private String estado;

    public OrdenDeCompra(int idOrden, Cliente destinatario, ArrayList<Mineral> minerales, Date fecha, Double total, String estado) {
        this.idOrden = idOrden;
        this.destinatario = destinatario;
        this.minerales = minerales;
        this.fecha = fecha;
        this.peso = minerales.stream().mapToDouble(Mineral::getToneladas).sum();
        this.total = total;
        this.estado = estado;
    }

    public OrdenDeCompra(int idOrden, Cliente destinatario, Date fecha, Double total, String estado) {
        this.idOrden = idOrden;
        this.destinatario = destinatario;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }

    public OrdenDeCompra(Cliente destinatario, ArrayList<Mineral> minerales, Date fecha, Double total, String estado) {
        this.destinatario = destinatario;
        this.minerales = minerales;
        this.fecha = fecha;
        this.peso = minerales.stream().mapToDouble(Mineral::getToneladas).sum();
        this.total = total;
        this.estado = estado;
    }



    public static ArrayList<OrdenDeCompra> buscarPedidosPorId(int id) {
        ArrayList<OrdenDeCompra> ordenes = OrdenesRepository.buscarOrdenesPorId(id);
        return ordenes;
    }

    public static ArrayList<OrdenDeCompra> buscarPorId(int id) {
        ArrayList<OrdenDeCompra> ordenes = OrdenesRepository.buscarOrdenesPorId(id);
        return ordenes;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public void emitirOrden(){

        int clienteId = getDestinatario().getIdUsuario();
        Double total = getTotal();
        Double peso = getPeso();
        String estado = getEstado();

        ArrayList<Mineral> minerales = getMinerales();

         int ordenID = OrdenesRepository.crearOrdenDeCompra(clienteId, total, peso, estado);

        for (Mineral mineral : minerales) {

            int mineralId = mineral.getIdMineral();
            Double toneladas = mineral.getToneladas();

            OrdenesRepository.insertarMineralEnOrden(ordenID, mineralId, toneladas);
        }

    }

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
