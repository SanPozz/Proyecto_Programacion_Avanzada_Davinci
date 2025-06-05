package DLL.Repository;

import BLL.Clases.Mineral;
import DLL.Conexion.Conexion;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ActualizarMineRepository {

private static Connection conn = Conexion.getInstance().getConnection();
	 
 
public static String editarIdMineral(Mineral mineral) {
    
    try (PreparedStatement statement = (PreparedStatement) conn.prepareStatement("UPDATE `minerales` SET `nombre`= ?, `toneladas`= ?, `pureza`= ?, `precioTonelada`= ? WHERE id = ?")) {
        statement.setString(1, mineral.getNombre());
        statement.setDouble(2, mineral.getToneladas());
        statement.setDouble(3, mineral.getPureza());
        statement.setDouble(4, mineral.getPrecioTonelada());
        statement.setInt(5, mineral.getIdMineral());

        int filas = statement.executeUpdate();
        if (filas > 0) {
        	 return "Filas actualizadas: " + filas;
		}
       
    } catch (Exception e) {
        e.printStackTrace();
        
    }
    return "error";
}
 
 
 
 
	 
	  static public ArrayList<Mineral> encontrarMineralesPorId(int idMineral){
		    ArrayList<Mineral> minerales = new ArrayList<>();

		    try {
		      ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `minerales` WHERE id = ?");

		      while(rs.next()){
		        minerales.add(new Mineral(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("pureza"), rs.getDouble("toneladas"),rs.getDouble("precioTonelada")));
		      }

		      return minerales;

		    } catch (SQLException e) {
		      e.printStackTrace();
		      return null;
		    }
		  }
	  

}
