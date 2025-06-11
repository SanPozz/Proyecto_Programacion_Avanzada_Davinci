package BLL.Clases;

import java.util.ArrayList;

public class Cliente extends Usuario{

    private ArrayList<Mineral> carrito = new ArrayList<>();

    public Cliente(int idUsuario,String nombre, String apellido, int edad, String correo, int rol) {
        super(idUsuario, nombre, apellido, edad, correo, rol);

    }

    public void anadirAlCarrito(Mineral mineral) {
        carrito.add(mineral);
    }

    public void eliminarDelCarrito(int idMineral) {
        carrito.removeIf(mineral -> mineral.getIdMineral() == idMineral);
    }

    public ArrayList<Mineral> getCarrito() {
        return carrito;
    }

    public void setCarrito(ArrayList<Mineral> carrito) {
        this.carrito = carrito;
    }

    public double calcularTotalCarrito() {
        double total = 0;
        for (Mineral mineral : carrito) {
            total += mineral.getPrecioTonelada() * mineral.getToneladas() * (mineral.getPureza() / 100);
        }
        return total;
    }

    public ArrayList<OrdenDeCompra> encontrarOrdenes() {
        ArrayList<OrdenDeCompra> ordenes = OrdenDeCompra.buscarPorId(getIdUsuario());
        return ordenes;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "carrito=" + carrito +
                ", idUsuario=" + getIdUsuario() +
                ", nombre='" + getNombre() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", edad=" + getEdad() +
                ", correo='" + getCorreo() + '\'' +
                ", rol=" + getRol() +
                '}';
    }



}
