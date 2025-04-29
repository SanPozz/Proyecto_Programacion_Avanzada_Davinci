import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        iniciarMenu();
        
    }

    static public void iniciarMenu(){

            String[] opciones = {"Log in", "Registrarse"};

            int seleccion = JOptionPane.showOptionDialog(null, "Bienvenido al Sistema Minashi", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            switch(seleccion){
                case 0:
                    logIn();
                    break;
                case 1:
                    signIn();
                    break;
            }

    }

    static public void logIn(){

        String usuarioAdmin= "Admin";
        String contrasenaAdmin = "1234";

        String usuarioUser = "User";
        String contrasenaUser = "1234";

        String usuarioEmpleado = "Empleado";
        String contrasenaEmpleado = "1234";

        boolean reintentar = false;

        do {
            String user = JOptionPane.showInputDialog("Ingrese su usuario");
            //TODO Validacion si el usuario existe en la base de datos

            String pass = JOptionPane.showInputDialog("Ingresar Contraseña");
            //TODO Validacion de contraseña

            if ((user.equals(usuarioUser) && pass.equals(contrasenaUser)) || (user.equals(usuarioEmpleado) && pass.equals(contrasenaEmpleado))) {
                JOptionPane.showMessageDialog(null, "Los datos de inicio de sesion son correctos!");

                reintentar = false;

                if (user.equals(usuarioUser)) {
                    mostrarFuncionalidades("user");
                } else {
                    mostrarFuncionalidades("empleado");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Los datos de inicio de sesion son invalidos");
                reintentar = true;
            }

        } while(reintentar == true);
    }

    static public void signIn(){
        JOptionPane.showMessageDialog(null, "Funcionalidad no implementada");
    }

    static public void mostrarFuncionalidades(String rol){

        String[] opcionesEmpleado = {"Verificar Stock", "Solictar Stock", "Ver Órdenes", "Emitir Órdenes"};

        String[] opcionesUsuario = {"Ver Productos", "Ver Compras"};

        String [] opciones;

        if (rol.equalsIgnoreCase("empleado")){
            opciones = opcionesEmpleado;
        } else {
            opciones = opcionesUsuario;
        }

        int seleccion = JOptionPane.showOptionDialog(null, "Que quiere hacer hoy?", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        switch(seleccion){
            case 0:
                if (rol.equalsIgnoreCase("empleado")){
                    JOptionPane.showMessageDialog(null, "Minerales en stock: \nCarbon:900\nPlata:1800\nHierro:1000\nOro:400\nTopacio:200\nSafiro:200");
                } else {
                    JOptionPane.showMessageDialog(null, "Minerales: \nCarbon\nPlata\nHierro\nOro\nTopacio\nSafiro");
                }
                break;
            case 1:
                if (rol.equalsIgnoreCase("empleado")){
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

}