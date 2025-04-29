package Clases;

import java.util.ArrayList;

public class Deposito extends Empleado{

    private ArrayList<Mineral> Inventario;
    private Camion camion;


    public Deposito(int idEmpleado, String nombreEmpleado, String apellidoEmpleado) {
        super(idEmpleado, nombreEmpleado, apellidoEmpleado, "Deposito");
    }

    public ArrayList<Mineral> getInventario() {
        return Inventario;
    }

    public void setInventario(ArrayList<Mineral> inventario) {
        Inventario = inventario;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    @Override
    public String toString() {
        return "Deposito{" +
                "Inventario=" + Inventario +
                ", camion=" + camion +
                '}';
    }
}
