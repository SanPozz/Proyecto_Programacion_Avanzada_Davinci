package Clases;


import Conexion.Conexion;
import Repository.UsersRepository;
import com.mysql.jdbc.Connection;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class Menu {

  static Connection conn = Conexion.getInstance().getConnection();


  static public void iniciarMenu() throws SQLException {

    String[] opciones = {"Log in", "Registrarse"};

    int seleccion = JOptionPane.showOptionDialog(null, "Bienvenido al Sistema Minashi", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

    switch (seleccion) {
      case 0:

        JTextField mailField = new JTextField(20);
        JTextField passwordField = new JTextField(20);

        JPanel panelLogIn = new JPanel(new GridLayout(0, 1, 5, 5));

        panelLogIn.add(new JLabel("Mail:"));
        panelLogIn.add(mailField);
        panelLogIn.add(new JLabel("Password:"));
        panelLogIn.add(passwordField);

        boolean flagLogin = false;
        while (flagLogin == false) {

          int sendForm = JOptionPane.showConfirmDialog(null, panelLogIn, "Iniciar sesion", JOptionPane.OK_CANCEL_OPTION);

          if (sendForm == JOptionPane.OK_OPTION) {

            Usuario user = Usuario.logIn(mailField.getText(), passwordField.getText());

            if (user != null) {
              flagLogin = true;
              mostrarFuncionalidades(user.getRol());

            } else {
              JOptionPane.showMessageDialog(null, "Las credenciales introducidas son invalidas");

            }

          } else{

            flagLogin = true;
//            JOptionPane.showMessageDialog(null, "Cierre de sistema");

          }

        }

        break;

      case 1:

        JTextField nombreField = new JTextField(10);
        JTextField apellidoField = new JTextField(10);
        JTextField passField = new JTextField(15);
        JTextField edadField = new JTextField(5);
        JTextField correoField = new JTextField(15);

        JPanel panelSignIn = new JPanel(new GridLayout(0, 1, 5, 5));

        panelSignIn.add(new JLabel("Nombre:"));
        panelSignIn.add(nombreField);
        panelSignIn.add(new JLabel("Apellido:"));
        panelSignIn.add(apellidoField);
        panelSignIn.add(new JLabel("Edad:"));
        panelSignIn.add(edadField);
        panelSignIn.add(new JLabel("Correo:"));
        panelSignIn.add(correoField);
        panelSignIn.add(new JLabel("Password:"));
        panelSignIn.add(passField);

        boolean datosValidos = false;

        while (!datosValidos) {

          int result = JOptionPane.showConfirmDialog(null, panelSignIn,
                  "Registro de Usuario", JOptionPane.OK_CANCEL_OPTION);

          if (result == JOptionPane.OK_OPTION) {

            boolean flag = Usuario.signUp(nombreField.getText(), apellidoField.getText(), passField.getText(), Integer.parseInt(edadField.getText()), correoField.getText());
            if (flag) {
              datosValidos = true;
            }

          } else {
            break;
          }

        }

    }
  }

    static public void mostrarFuncionalidades ( int rol) throws SQLException {

      String[] opcionesFunc = asignarOpcionesMenu(rol);

      int seleccionFunc = JOptionPane.showOptionDialog(null, "Que quiere hacer hoy?", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionesFunc, opcionesFunc[0]);

        switch (rol) {
          case 1:
            return;
          case 4:
            if (seleccionFunc == 0) {

              ArrayList<Usuario> clientes = UsersRepository.encontrarClientes();

              StringBuilder sb = new StringBuilder();

              for (Usuario cliente : clientes) {
                sb.append(cliente.getCorreo() + " " + cliente.getNombre() + " " + cliente.getApellido() + "\n");
              }

              JOptionPane.showMessageDialog(null,"Clientes:\n" + sb);

              mostrarFuncionalidades(rol);


            } else if (seleccionFunc == 1) {

              ArrayList<Usuario> empleados = UsersRepository.encontrarEmpleados();

              StringBuilder sb = new StringBuilder();

              for(Usuario empleado : empleados) {
                sb.append(empleado.getCorreo() + " " + empleado.getNombre() + " " + empleado.getApellido() + "\n");
              }

              JOptionPane.showMessageDialog(null,"Empleados:\n" + sb);

              mostrarFuncionalidades(rol);

            } else if (seleccionFunc == 2) {

              JTextField nombreField = new JTextField(10);
              JTextField apellidoField = new JTextField(10);
              JTextField passField = new JTextField(15);
              JTextField edadField = new JTextField(5);
              JTextField correoField = new JTextField(15);

              String[] rolesPosibles = {"2", "3"}; //Ventas, Deposito
              JComboBox<String> rolBox = new JComboBox<>(rolesPosibles);

              JPanel panelSignIn = new JPanel(new GridLayout(0, 1, 5, 5));

              panelSignIn.add(new JLabel("Nombre:"));
              panelSignIn.add(nombreField);
              panelSignIn.add(new JLabel("Apellido:"));
              panelSignIn.add(apellidoField);
              panelSignIn.add(new JLabel("Edad:"));
              panelSignIn.add(edadField);
              panelSignIn.add(new JLabel("Correo:"));
              panelSignIn.add(correoField);
              panelSignIn.add(new JLabel("Password:"));
              panelSignIn.add(passField);
              panelSignIn.add(new JLabel("Rol:"));
              panelSignIn.add(rolBox);

              boolean datosValidos = false;

              while (!datosValidos) {

                int result = JOptionPane.showConfirmDialog(null, panelSignIn,
                        "Registro de Usuario", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {

                  PreparedStatement ps = conn.prepareStatement("INSERT INTO usuarios (nombre, apellido, edad, correo, rol, password) VALUES (?,?,?,?,?,?)");
                  ps.setString(1, nombreField.getText());
                  ps.setString(2, apellidoField.getText());
                  ps.setInt(3, Integer.parseInt(edadField.getText()));
                  ps.setString(4, correoField.getText());
                  ps.setString(5, rolBox.getSelectedItem().toString());
                  ps.setString(6, Encriptador.encriptarPass(passField.getText()));

                  int filasAfectadas = ps.executeUpdate();

                  if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(null,"El Empleado ha sido registrado");
                    datosValidos = true;

                  } else {
                    JOptionPane.showMessageDialog(null, "No se pudo registrar el Empleado");
                    datosValidos = true;
                  }

                  mostrarFuncionalidades(rol);

                } else {
                  break;
                }

              }
            } else if (seleccionFunc == 3) {
              String id = JOptionPane.showInputDialog(null, "Insertar ID del usuario a eliminar", "Menu", JOptionPane.DEFAULT_OPTION);

              boolean flag = UsersRepository.eliminarUsuarioID(Integer.parseInt(id));

              if (flag) {
                JOptionPane.showMessageDialog(null, "Usuario eliminado");
                mostrarFuncionalidades(rol);
              } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el usuario");
                mostrarFuncionalidades(rol);
              }



            }
        }
    }

    static public String[] asignarOpcionesMenu(int rol){

      String[] opcionesVenta = {"Emitir Orden", "Ver Órdenes"};
      String[] opcionesCliente = {"Ver Productos", "Ver Compras"};
      String[] opcionesDeposito = {"Registrar Mineral", "Mostrar Inventario", "Buscar Prodcuto Por ID", "Eliminar Producto", "Actualizar Producto por ID"};
      String[] opcionesAdmin = {"Mostrar Clientes", "Mostrar Empleados", "Registrar Empleado", "Eliminar Empleado por ID"};

      //TODO "Mostrar Pedidos", "Mostrar Minerales", "Eliminar MineraLes" (ADMIN)

      switch (rol){
        case 1: return opcionesCliente;
        case 2: return opcionesDeposito;
        case 3: return opcionesVenta;
        case 4: return opcionesAdmin;
        default:
          break;
      }
    return null;
    }

}
