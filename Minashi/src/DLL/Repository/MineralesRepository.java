package DLL.Repository;

import BLL.Clases.Mineral;
import DLL.Conexion.Conexion;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MineralesRepository {

  private static Connection conn = Conexion.getInstance().getConnection();

  static public String actualizarStock(Mineral mineral) {
    try {

        PreparedStatement ps = (PreparedStatement) conn.prepareStatement("UPDATE minerales SET toneladas = ? WHERE id = ?");

        Mineral findMineral = encontrarMineralPorId(mineral.getIdMineral());
        Double cantActualizada = findMineral.getToneladas() - mineral.getToneladas();
        ps.setDouble(1, cantActualizada);
        ps.setInt(2, mineral.getIdMineral());

        return ps.executeUpdate() > 0 ? "Stock de minerales actualizado correctamente" : "No se pudo actualizar el stock de minerales";

    } catch (SQLException e){
        e.printStackTrace();
        return "Error al actualizar el stock de minerales";
    }
  }

  static public ArrayList<Mineral> encontrarMinerales(){
    ArrayList<Mineral> minerales = new ArrayList<>();
    try{
      ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM minerales WHERE estado != 'eliminado'");

      while(rs.next()){

        minerales.add(new Mineral(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("pureza"), rs.getDouble("toneladas"), rs.getDouble("precioTonelada")));

      }

      return minerales;

    } catch (SQLException e){

      e.printStackTrace();
      return null;
    }

  }

  static public Mineral encontrarMineralPorId(int id){
    try {
      PreparedStatement ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM minerales WHERE id = ?");
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();

      if(rs.next()){
        return new Mineral(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("pureza"), rs.getDouble("toneladas"), rs.getDouble("precioTonelada"));
      } else {
        return null;
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  static public ArrayList<Mineral> encontrarMineralesPorNombre(String nombre){
    ArrayList<Mineral> minerales = new ArrayList<>();

    try {
      ResultSet rs = conn.createStatement().executeQuery("SElECT * FROM minerales WHERE nombre LIKE '%" + nombre + "%'");

      while(rs.next()){

        minerales.add(new Mineral(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("pureza"), rs.getDouble("toneladas"), rs.getDouble("precioTonelada")));

      }

      return minerales;

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  public static String registrarMineral(Mineral mineral) {
	    
	    try (PreparedStatement statement = (PreparedStatement) conn.prepareStatement("INSERT INTO `minerales`(`nombre`, `toneladas`, `pureza`,`precioTonelada`, `estado`) VALUES (?,?,?,?, 'disponible')")) {
	        statement.setString(1, mineral.getNombre());
	        statement.setDouble(2, mineral.getToneladas());
	        statement.setDouble(3, mineral.getPureza());
	        statement.setDouble(4, mineral.getPrecioTonelada());
	       
	        System.out.println("Nombre del mineral: " + mineral.getNombre());
	        int filas = statement.executeUpdate();
	        if (filas > 0) {
	        	 return "Se registro mineral correctamente";
			}
	       
	    } catch (Exception e) {
	        e.printStackTrace();
	        
	    }
	    return "error al registrar mineral";
	}

}
