package Clases;

public class Venta extends Empleado {

    int cantVentas;

    public Venta(int idEmpleado, String nombreEmpleado, String apellidoEmpleado) {
        super( nombreEmpleado, apellidoEmpleado, 3);
        this.cantVentas = 0;
    }

    public int getCantVentas() {
        return cantVentas;
    }

    public void setCantVentas(int cantVentas) {
        this.cantVentas = cantVentas;
    }

    public void finalizarVenta() {}

    public void asignarOrden(){}

    @Override
    public String toString() {
        return "Venta{" +
                "cantVentas=" + cantVentas +
                '}';
    }
}
