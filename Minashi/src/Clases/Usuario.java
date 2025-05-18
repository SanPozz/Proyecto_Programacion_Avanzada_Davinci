package Clases;

import Conexion.Conexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Clases.Menu.iniciarMenu;

public class Usuario {

  private String nombre;
  private String apellido;
  private int edad;
  private String correo;
  private int rol;
  private static Connection conn = Conexion.getInstance().getConnection();

  public Usuario(String nombre, String apellido, int edad, String correo, int rol) {

    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
    this.correo = correo;
    this.rol = rol;

  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  static public Usuario logIn(String mail, String pass) {

    try{

      PreparedStatement ps = conn.prepareStatement("SELECT * FROM Usuarios WHERE correo = ?");
      ps.setString(1, mail);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {

        if (rs.getString("correo").equals(mail) && rs.getString("password").equals(pass)) {

          JOptionPane.showMessageDialog(null, "Iniciaste sesion" + "\nBienvenido " + rs.getString("nombre") + " " + rs.getString("apellido"));

          Usuario user = crearUserPorRol(rs);

          return user;


        }

      }

    } catch (SQLException e) {
      return null;
    }
    return null;
  }


  static public boolean signUp(String nombre, String apellido, String password, int edad, String correo) throws SQLException {

    try {

      PreparedStatement ps = conn.prepareStatement("INSERT INTO usuarios (nombre, apellido, edad, correo, rol, password) VALUES (?,?,?,?,?,?)");
      ps.setString(1, nombre);
      ps.setString(2, apellido);
      ps.setInt(3, edad);
      ps.setString(4, correo);
      ps.setInt(5, 1);
      ps.setString(6, password);

      int filas = ps.executeUpdate();

      iniciarMenu();

      return filas > 0;

    } catch (SQLException e){
      System.out.println("Error: " + e.getMessage() );
      JOptionPane.showMessageDialog(null, "Error al registrarse");
      iniciarMenu();
      return false;
    }
  }

  static public Usuario crearUserPorRol(ResultSet rs) throws SQLException {

    Usuario user = null;

    switch (rs.getInt("rol")) {
      case 1:
        user = new Cliente(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("edad"), rs.getString("correo"), rs.getInt("rol"));
        break;

      case 2:
        break;
        case 3:
          break;
          case 4:
            break;
            case 5:
              break;
              default:
                break;
    }

    return user;
  };


  public int getRol() {
    return this.rol;
  }

  @Override
  public String toString() {
    return "Usuario{" +
            "nombre='" + nombre + '\'' +
            ", apellido='" + apellido + '\'' +
            ", edad=" + edad +
            ", correo='" + correo + '\'' +
            ", rol=" + rol +
            '}';
  }
}
