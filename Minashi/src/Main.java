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
            }

    }

    static public void logIn(){

        String usuarioCorrecto = "Admin";
        String contrasenaCorrecta = "1234";
        boolean reintentar = false;

        do {

            String user = JOptionPane.showInputDialog("Ingrese su usuario");

            //TODO Validacion si el usuario existe en la base de datos

            String pass = JOptionPane.showInputDialog("Ingresar Contraseña");

            if (user.equals(usuarioCorrecto) && pass.equals(contrasenaCorrecta)) {
                JOptionPane.showMessageDialog(null, "Los datos de inicio de sesion son correctos!");

                mostrarFuncionalidades();


            } else {
                JOptionPane.showMessageDialog(null, "Los datos de inicio de sesion son invalidos");
                reintentar = true;
            }

        } while(reintentar == true);
    }

    static public void signIn(){
        JOptionPane.showMessageDialog(null, "Funcionalidad no implementada");
    }

    static public void mostrarFuncionalidades(){

        String[] opciones = {"opcion1", "opcion2", "opcion3", "opcion4"};

        int seleccion = JOptionPane.showOptionDialog(null, "Que quiere hacer?", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        switch(seleccion){
            case 0:
                JOptionPane.showMessageDialog(null, "Funcionalidad no implementada");
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Funcionalidad no implementada");
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Funcionalidad no implementada");
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Funcionalidad no implementada");
                break;
            default:
                break;
        }

    }

}