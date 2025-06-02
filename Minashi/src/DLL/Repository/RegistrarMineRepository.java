package DLL.Repository;

import BLL.Clases.Mineral;
import DLL.Conexion.Conexion;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistrarMineRepository {

	 private static Connection conn = Conexion.getInstance().getConnection();
	 
	 static public ArrayList<Mineral> registrarMineral(){
		    ArrayList<Mineral> registrarMineral = new ArrayList<>();
		    try{
		      ResultSet rs = conn.createStatement().executeQuery("INSERT INTO `minerales`(`nombre`, `toneladas`, `pureza`) VALUES (?,?,?)");

		      while(rs.next()){
		        registrarMineral.add(new Mineral(rs.getString("nombre"), rs.getDouble("pureza"), rs.getDouble("toneladas")));
		      }

		      return registrarMineral;

		    } catch (SQLException e){

		      e.printStackTrace();
		      return null;
		    }

		  }
	 
	 
}
