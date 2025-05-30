//package BLL.Clases;
//
//import java.util.ArrayList;
//
//public class Deposito extends Usuario implements InterfaceDeposito{
//
//    private ArrayList<Mineral> Inventario;
//    private Camion camion;
//
//
//    public Deposito(String nombreEmpleado, String apellidoEmpleado, int edadEmpleado, String correoEmpleado) {
//        super(nombreEmpleado, apellidoEmpleado, edadEmpleado, correoEmpleado, 2);
//    }
//
//    public ArrayList<Mineral> getInventario() {
//        return Inventario;
//    }
//
//    public void setInventario(ArrayList<Mineral> inventario) {
//        Inventario = inventario;
//    }
//
//    public Camion getCamion() {
//        return camion;
//    }
//
//    public void setCamion(Camion camion) {
//        this.camion = camion;
//    }
//
//
//
//    @Override
//    public String toString() {
//        return "Deposito{" +
//                "Inventario=" + Inventario +
//                ", camion=" + camion +
//                '}';
//    }
//
//    @Override
//    public void registrarMineral(Mineral mineral) {
//
//    }
//
//    @Override
//    public void mostrarInventario() {
//
//    }
//
//    @Override
//    public void buscarMineralID(int mineralID) {
//
//    }
//
//    @Override
//    public void buscarMineralNombre(String nombreMineral) {
//
//    }
//
//    @Override
//    public void actualizarMineralID(int mineralID) {
//
//    }
//
//    @Override
//    public void eliminarMineralID(int mineralID) {
//
//    }
//}
