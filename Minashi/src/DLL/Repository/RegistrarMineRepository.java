package DLL.Repository;

import BLL.Clases.Mineral;
import DLL.Conexion.Conexion;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;


public class RegistrarMineRepository {

	 private static Connection conn = Conexion.getInstance().getConnection();
	 
	
	 public static String registrarMineral(Mineral mineral) {
		    
		    try (PreparedStatement statement = (PreparedStatement) conn.prepareStatement("INSERT INTO `minerales`(`nombre`, `toneladas`, `pureza`,`precioTonelada`) VALUES (?,?,?,?)")) {
		        statement.setString(1, mineral.getNombre());
		        statement.setDouble(2, mineral.getToneladas());
		        statement.setDouble(3, mineral.getPureza());
		        statement.setDouble(4, mineral.getPrecioTonelada());
		       

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
