package BLL.Clases;

public class Venta extends Usuario{

    int cantVentas;

    public Venta(int idUsuario,String nombre, String apellido, int edad, String correo, int rol, int cantVentas) {
        super( idUsuario, nombre, apellido, edad, correo, 3);
        this.cantVentas = cantVentas;
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
