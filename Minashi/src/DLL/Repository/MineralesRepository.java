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

  static public ArrayList<Mineral> encontrarMinerales(){
    ArrayList<Mineral> minerales = new ArrayList<>();
    try{
      ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM minerales");

      while(rs.next()){
        minerales.add(new Mineral(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("pureza"), rs.getDouble("toneladas"),rs.getDouble("precioTonelada")));
      }

      return minerales;

    } catch (SQLException e){

      e.printStackTrace();
      return null;
    }

  }

  static public ArrayList<Mineral> encontrarMineralesPorNombre(String nombre){
    ArrayList<Mineral> minerales = new ArrayList<>();

    try {
      ResultSet rs = conn.createStatement().executeQuery("SElECT * FROM minerales WHERE nombre LIKE '%" + nombre + "%'");

      while(rs.next()){
        minerales.add(new Mineral(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("pureza"), rs.getDouble("toneladas"),rs.getDouble("precioTonelada")));
      }

      return minerales;

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  public static String registrarMineral(Mineral mineral) {
	    
	    try (PreparedStatement statement = (PreparedStatement) conn.prepareStatement("INSERT INTO `minerales`(`nombre`, `toneladas`, `pureza`,`precioTonelada`) VALUES (?,?,?,?)")) {
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
