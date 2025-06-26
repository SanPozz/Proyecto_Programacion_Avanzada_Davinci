package DLL.Repository;

import DLL.Conexion.Conexion;

import BLLL.Clases.Cliente;
import BLLL.Clases.Mineral;
import BLLL.Clases.OrdenDeCompra;
import BLLL.Clases.Usuario;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class OrdenesRepository {

  private static Connection conn = Conexion.getInstance().getConnection();


  static public int crearOrdenDeCompra(int clienteId, double total, double peso, String estado) {
    try {
      String query = "INSERT INTO ordenes (comprador, total, peso, estado) VALUES (?, ?, ?, ?)";
      PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, clienteId);
      ps.setDouble(2, total);
      ps.setDouble(3, peso);
      ps.setString(4, estado);

        ps.executeUpdate();

        // Obtener el ID de la orden recién creada
      java.sql.ResultSet generatedKeys = ps.getGeneratedKeys();

        if (generatedKeys.next()) {
            int ordenId = generatedKeys.getInt(1);
            return ordenId; // Retornar el ID de la orden creada
        } else {
            System.out.println("No se pudo obtener el ID de la orden creada.");
            return -1; // Retornar un valor negativo para indicar error
        }


    } catch (Exception e) {
      e.printStackTrace();
      return -1; // Retornar un valor negativo para indicar error
    }
  }

  static public void insertarMineralEnOrden(int ordenId, int mineralId, double toneladas) {
    try {
      String query = "INSERT INTO minerales_ordenes (id_orden, id_mineral, toneladas) VALUES (?, ?, ?)";
      PreparedStatement ps = conn.prepareStatement(query);
      ps.setInt(1, ordenId);
      ps.setInt(2, mineralId);
      ps.setDouble(3, toneladas);

      ps.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static ArrayList<OrdenDeCompra> buscarOrdenesPorId(int id) {
    ArrayList<OrdenDeCompra> ordenes = new ArrayList<>();
    try {
      String query = "SELECT * FROM ordenes WHERE comprador = ?";
      PreparedStatement ps = conn.prepareStatement(query);
      ps.setInt(1, id);
      java.sql.ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        int idOrden = rs.getInt("id");
        int compradorId = rs.getInt("comprador");
        double total = rs.getDouble("total");
        String estado = rs.getString("estado");
        Timestamp fechaTimestamp = rs.getTimestamp("fecha");

        java.util.Date fecha = new java.util.Date(fechaTimestamp.getTime());

        Cliente destinatario = UsersRepository.encontrarClienteID(compradorId);

        OrdenDeCompra orden = new OrdenDeCompra(idOrden, destinatario, fecha, total, estado);

        ordenes.add(orden);

      }

      return ordenes;

    } catch (Exception e) {
      e.printStackTrace();
        return ordenes; // Retornar una lista vacía en caso de error
    }
  }
  public static ArrayList<OrdenDeCompra> buscarOrdenPorIdOrden(int idOrden) {
	    ArrayList<OrdenDeCompra> ordenes = new ArrayList<>();
	    try {
	        String query = "SELECT * FROM ordenes WHERE id = ?";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, idOrden);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            int compradorId = rs.getInt("comprador");
	            double total = rs.getDouble("total");
	            String estado = rs.getString("estado");
	            Timestamp fechaTimestamp = rs.getTimestamp("fecha");

	            Date fecha = new Date(fechaTimestamp.getTime());

	            Cliente destinatario = UsersRepository.encontrarClienteID(compradorId);

	            OrdenDeCompra orden = new OrdenDeCompra(idOrden, destinatario, fecha, total, estado);
	            ordenes.add(orden);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return ordenes;
	}

  static public ArrayList<Mineral> detalleOrden(int idOrden) {
    ArrayList<Mineral> mineralesOrden = new ArrayList<>();
    try {
      String query = "SELECT * FROM minerales_ordenes WHERE id_orden = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, idOrden);
        java.sql.ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int mineralId = rs.getInt("id_mineral");
            double toneladas = rs.getDouble("toneladas");

            Mineral mineral = MineralesRepository.encontrarMineralPorId(mineralId);

            if (mineral != null) {

                mineral.setToneladas(toneladas);
                mineralesOrden.add(mineral);

            } else {
                System.out.println("Mineral con ID " + mineralId + " no encontrado.");
            }

        }

        return mineralesOrden;


    } catch (Exception e) {
      e.printStackTrace();
    }
    return mineralesOrden;
  }

  
  public static ArrayList<OrdenDeCompra> obtenerTodas() {
	    ArrayList<OrdenDeCompra> ordenes = new ArrayList<>();
	    try {
	        String query = "SELECT * FROM ordenes";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            int idOrden = rs.getInt("id");
	            int compradorId = rs.getInt("comprador");
	            double total = rs.getDouble("total");
	            String estado = rs.getString("estado");
	            Timestamp fechaTimestamp = rs.getTimestamp("fecha");
	            java.util.Date fecha = new java.util.Date(fechaTimestamp.getTime());

	            Cliente destinatario = UsersRepository.encontrarClienteID(compradorId);

	            OrdenDeCompra orden = new OrdenDeCompra(idOrden, destinatario, fecha, total, estado);
	            ordenes.add(orden);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return ordenes;
	}
 

}
