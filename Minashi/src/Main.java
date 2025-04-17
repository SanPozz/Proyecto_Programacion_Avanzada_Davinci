import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        String[] opciones = {"Log in", "Registrarse"};

        int seleccion = JOptionPane.showOptionDialog(null, "Bienvenido al Sistema Minashi", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        logIn();

    }


    static public void logIn(){


        String usuarioCorrecto = "Gamaliel";
        String contrasenaCorrecto = "1234";

        JOptionPane.showInputDialog("Ingrese su usuario");
        //TODO Validacion si el usuario existe en la base de datos

        JPasswordField password = new JPasswordField();

        JOptionPane.showConfirmDialog(null,password, "Menu", JOptionPane.OK_CANCEL_OPTION);
        //


    }

}