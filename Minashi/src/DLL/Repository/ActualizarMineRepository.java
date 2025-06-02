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
	 
	 static public ArrayList<Mineral> actualizarMineral(Mineral mineral){
		    ArrayList<Mineral> actualizarMineral = new ArrayList<>();
		    try{
		      ResultSet rs = conn.createStatement().executeQuery("UPDATE `minerales` SET `tipo`= ?,`toneladas`= ?,`pureza`= ? WHERE id = ?");

		      while(rs.next()){
		    	  actualizarMineral.add(new Mineral(rs.getString("tipo"), rs.getDouble("pureza"), rs.getDouble("toneladas"),rs.getInt(mineral.getIdMineral())));
		      }
             
		      
		      return actualizarMineral;

		    } catch (SQLException e){

		      e.printStackTrace();
		      return null;
		    }

		  }
	 
	  static public ArrayList<Mineral> encontrarMineralesPorId(int idMineral){
		    ArrayList<Mineral> minerales = new ArrayList<>();

		    try {
		      ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `minerales` WHERE id = ?");

		      while(rs.next()){
		        minerales.add(new Mineral(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("pureza"), rs.getDouble("toneladas")));
		      }

		      return minerales;

		    } catch (SQLException e) {
		      e.printStackTrace();
		      return null;
		    }
		  }
	  
	 
}
