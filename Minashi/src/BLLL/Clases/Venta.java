package BLLL.Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DLL.Conexion.Conexion;

public class Venta extends Usuario {

	private static Connection conn = Conexion.getInstance().getConnection();
    public Venta(int idUsuario, String nombre, String apellido, int edad, String correo, int rol) {
        super(idUsuario, nombre, apellido, edad, correo, 3);
    }

    // Método para ver órdenes del usuario
    public void verOrdenes() {
        String query = "SELECT id, total, peso, fecha, estado FROM ordenes WHERE comprador = ?";

        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, getIdUsuario());
            ResultSet rs = stmt.executeQuery();

            System.out.println("Órdenes de usuario:");
            while (rs.next()) {
                System.out.println("ID Orden: " + rs.getInt("id") +
                                   ", Total: " + rs.getInt("total") +
                                   ", Peso: " + rs.getInt("peso") +
                                   ", Fecha: " + rs.getTimestamp("fecha") +
                                   ", Estado: " + rs.getString("estado"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener las órdenes.");
        }
    }

    // Método para solicitar stock de minerales
    public void solicitarStock(int idMineral, double toneladas) {
        String query = "INSERT INTO minerales_ordenes (id_mineral, toneladas) VALUES (?, ?)";

        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, idMineral);
            stmt.setDouble(2, toneladas);
            
            stmt.executeUpdate();
            System.out.println("Stock solicitado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al solicitar stock.");
        }
    }

    // Método para finalizar una orden

    public static String finalizarVenta(OrdenDeCompra orden) {
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que quiere finalizar esta orden de compra?", "Confirmar Finalización", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try (PreparedStatement statement = conn.prepareStatement("UPDATE ordenes SET estado = 'Finalizado' WHERE id = ?")) {
                statement.setInt(1, orden.getIdOrden());
                int filas = statement.executeUpdate();

                if (filas > 0) {
                    return "Se finalizó la orden de compra";
                } else {
                    return "Error al finalizar la orden";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error al intentar finalizar la orden: " + e.getMessage();
            }
        } else {
            return "No se finalizó la orden";
        }
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idUsuario=" + getIdUsuario() +
                ", nombre='" + getNombre() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", edad=" + getEdad() +
                ", correo='" + getCorreo() + '\'' +
                ", rol=" + getRol() +
                '}';
    }
}