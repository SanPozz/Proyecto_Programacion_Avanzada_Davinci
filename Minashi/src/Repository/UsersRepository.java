package Repository;

import Clases.Usuario;
import Conexion.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Clases.Usuario.crearUserPorRol;


public class UsersRepository implements IUsersRepo {

  private static Connection conn = Conexion.getInstance().getConnection();



  @Override
  public ArrayList<Usuario> encontrarUsuarios() throws SQLException {

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

  @Override
  public Usuario encontrarUsuarioID() {
    return null;
  }

  @Override
  public boolean agregarUsuario(Usuario usuario) {
    return false;
  }

  @Override
  public boolean eliminarUsuario(Usuario usuario) {
    return false;
  }

  @Override
  public boolean actualizarUsuario(Usuario usuario) {
    return false;
  }
}
