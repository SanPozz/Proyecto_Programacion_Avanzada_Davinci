package BLL.Clases;

public interface InterfaceDeposito {

  void registrarMineral(Mineral mineral);

  void mostrarInventario();

  void buscarMineralID(int mineralID);
  void buscarMineralNombre(String nombreMineral);

  void actualizarMineralID(int mineralID);

  void eliminarMineralID(int mineralID);


}
