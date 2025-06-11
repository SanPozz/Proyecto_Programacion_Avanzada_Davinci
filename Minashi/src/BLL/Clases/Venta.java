package BLL.Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DLL.Conexion.Conexion;

public class Venta extends Usuario {


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
    public void finalizarVenta(int idOrden) {
        String query = "UPDATE ordenes SET estado = 'Finalizada' WHERE id = ? AND comprador = ?";

        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, idOrden);
            stmt.setInt(2, getIdUsuario());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Orden finalizada correctamente.");
            } else {
                System.out.println("No se encontró la orden o ya estaba finalizada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al finalizar la orden.");
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