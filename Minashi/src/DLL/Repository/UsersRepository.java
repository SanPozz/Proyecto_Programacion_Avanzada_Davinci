package DLL.Repository;

import DLL.Conexion.Conexion;

import BLLL.Clases.Cliente;
import BLLL.Clases.Usuario;

import static BLLL.Clases.Usuario.crearUserPorRol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UsersRepository {

  private static Connection conn = Conexion.getInstance().getConnection();


  static public ArrayList<Usuario> encontrarUsuarios() throws SQLException {

    ArrayList<Usuario> usuarios = new ArrayList<>();

    try{
      ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM usuarios");

      while(rs.next()){
        Usuario user = crearUserPorRol(rs);
        usuarios.add(user);
      }

      return usuarios;
    } catch (SQLException e){
      return null;
    }
  }

  static public Usuario encontrarUsuarioID() {
    try {
      PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
      ps.setInt(1, 1); // Cambiar el ID según sea necesario
      ResultSet rs = ps.executeQuery();

      if(rs.next()){
        return crearUserPorRol(rs);
      } else {
        return null;
      }

    } catch (SQLException e) {
      return null;
    }
  }

  static public ResultSet encontrarUsuarioMail(String mailSearch){
    try{
      PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuarios WHERE correo = ?");
      ps.setString(1, mailSearch);
      return ps.executeQuery();

    } catch (SQLException e) {
      return null;
    }
  }

  static public boolean agregarUsuario(Usuario usuario) {
    return false;
  }

  static public boolean eliminarUsuarioID(int id) {
    try {
      PreparedStatement ps = conn.prepareStatement("DELETE FROM usuarios WHERE id = ?");
      ps.setInt(1, id);

      return ps.executeUpdate() > 0;

    } catch(SQLException e) {
      return false;
    }
  }

  static public boolean actualizarUsuario(Usuario usuario) {
    return false;
  }

  static public ArrayList<Usuario> encontrarEmpleados(){

    ArrayList<Usuario> empleados = new ArrayList<>();

    try {

      PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuarios WHERE rol = ? OR rol = ?");

      ps.setString(1, "2");
      ps.setString(2, "3");

      ResultSet rs = ps.executeQuery();

      while(rs.next()){
        empleados.add(new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("edad"), rs.getString("correo"), rs.getInt("rol")));
      }

      return empleados;

    } catch(SQLException e){
      return null;

    }
  }

  static public ArrayList<Usuario> encontrarClientes() {
    ArrayList<Usuario> clientes = new ArrayList<>();

    try {

      PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuarios WHERE rol = ?");

      ps.setString(1, "1");

      ResultSet rs = ps.executeQuery();
      while(rs.next()){
        clientes.add(crearUserPorRol(rs));
      }

      return clientes;

    } catch (SQLException e) {
      return null;
    }

  }

  public static Cliente encontrarClienteID(int id) {
    try {
      PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuarios WHERE id = ? AND rol = ?");
      ps.setInt(1, id);
        ps.setString(2, "1");
      ResultSet rs = ps.executeQuery();

      if(rs.next()){
        return new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("edad"), rs.getString("correo"), rs.getInt("rol"));
      } else {
        return null;
      }

    } catch (SQLException e) {
      return null;
    }
  }
}
