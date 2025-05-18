package Clases;


import Repository.UsersRepository;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Menu {


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
            JOptionPane.showMessageDialog(null, "Cierre de sistema");

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

            Usuario.signUp(nombreField.getText(), apellidoField.getText(), passField.getText(), Integer.parseInt(edadField.getText()), correoField.getText());
            datosValidos = true;

          } else {
            break;
          }

        }

    }
  }

    static public void mostrarFuncionalidades ( int rol) throws SQLException {

      String[] opcionesFunc = asignarOpcionesMenu(rol);

      int seleccionFunc = JOptionPane.showOptionDialog(null, "Que quiere hacer hoy?", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionesFunc, opcionesFunc[0]);

      switch (seleccionFunc) {
        case 0:
          if (rol == 1) {
            UsersRepository usersRepository = new UsersRepository();
            final ArrayList<Usuario> users = usersRepository.encontrarUsuarios();
            StringBuilder sb = new StringBuilder();

            for(Usuario usuario : users) {
              sb.append(usuario.toString()).append("\n");
            }

            JOptionPane.showMessageDialog(null, sb.toString());
          } else {
            JOptionPane.showMessageDialog(null, "Minerales: \nCarbon\nPlata\nHierro\nOro\nTopacio\nSafiro");
          }
          break;
        case 1:
          if (rol == 2) {
            JOptionPane.showMessageDialog(null, "Solicitar Minerales");
          } else {
            JOptionPane.showMessageDialog(null, "Compras: \n22/01/25: Compra de minerales, productos: ");
          }

          break;
        case 2:
          JOptionPane.showMessageDialog(null, "Órdenes en curso");
          break;
        case 3:
          JOptionPane.showMessageDialog(null, "Finalizar proceso de venta");
          break;
        default:
          break;
      }
    }

    static public String[] asignarOpcionesMenu(int rol){

      String[] opcionesVenta = {"Emitir Orden", "Ver Órdenes"};
      String[] opcionesCliente = {"Ver Productos", "Ver Compras"};
      String[] opcionesDeposito = {"Registrar Mineral", "Mostrar Inventario", "Buscar Prodcuto Por ID", "Eliminar Producto", "Actualizar Producto por ID"};
      String[] opcionesAdmin = {"Mostrar Empleados", "Mostrar Pedidos", "Mostrar Clientes", "Mostrar Minerales", "Eliminar Empleados", "Eliminar MineraLes"};

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
