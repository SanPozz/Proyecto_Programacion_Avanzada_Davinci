package DLL.Repository;

import DLL.Conexion.Conexion;
import BLLL.Clases.Mineral;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ActualizarMineRepository {

private static Connection conn = Conexion.getInstance().getConnection();
	 
 
public static String editarIdMineral(Mineral mineral) {
    try (PreparedStatement statement = (PreparedStatement) conn.prepareStatement(
            "UPDATE minerales SET nombre = ?, toneladas = ?, pureza = ?, precioTonelada = ?, estado = 'disponible' WHERE id = ?")) {
        
        statement.setString(1, mineral.getNombre());
        statement.setDouble(2, mineral.getToneladas());
        statement.setDouble(3, mineral.getPureza());
        statement.setDouble(4, mineral.getPrecioTonelada());
        statement.setInt(5, mineral.getIdMineral());

        int filas = statement.executeUpdate();
        if (filas > 0) {
            return "Se actualizó el mineral correctamente. Filas actualizadas: " + filas;
        } else {
            return "No se realizaron cambios";
        }

    } catch (Exception e) {
        e.printStackTrace();
        return "Error al editar mineral: " + e.getMessage();
    }
}



 
static public ArrayList<Mineral> encontrarMineralesPorId(int idMineral) {
    ArrayList<Mineral> minerales = new ArrayList<>();

    try (PreparedStatement ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM minerales WHERE id = ?")) {
        ps.setInt(1, idMineral);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            minerales.add(new Mineral(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getDouble("pureza"),
                rs.getDouble("toneladas"),
                rs.getDouble("precioTonelada"),
                rs.getString("estado")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
        
    }
    return minerales;
}

	  public static String eliminarMineral(Mineral mineral) {
		  int respuesta = JOptionPane.showConfirmDialog(null, "Esta seguro que quiere eliminar este mineral? ","Confirmar Eliminaci�n", JOptionPane.YES_NO_OPTION);
		  
		  if (respuesta == JOptionPane.YES_OPTION) {
			 
			  try (PreparedStatement statement = (PreparedStatement) conn.prepareStatement("UPDATE minerales SET estado = 'eliminado' WHERE id = ?")) {
			 
			        statement.setInt(1, mineral.getIdMineral());

			        int filas = statement.executeUpdate();
			        if (filas > 0) {
			        	 return "Se borro el mineral";
					}else {
						return "El mineral no se encontro o no se puede eliminar";
					}
			       
			    } catch (SQLException e) {
			    	 e.printStackTrace(); 
		                return "Error al intentar borrar el mineral: " + e.getMessage();
			        
			    }
		} else if (respuesta == JOptionPane.NO_OPTION)  {
            return "No se borro el mineral";
		}
		 
		    return "error";
		}
		  
	  }
	  


