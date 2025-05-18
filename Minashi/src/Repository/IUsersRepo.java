package Repository;

import Clases.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IUsersRepo {

  ArrayList<Usuario> encontrarUsuarios() throws SQLException;

  Usuario encontrarUsuarioID();

  boolean agregarUsuario(Usuario usuario);

  boolean eliminarUsuario(Usuario usuario);

  boolean actualizarUsuario(Usuario usuario);

}
