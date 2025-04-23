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

<<<<<<< Updated upstream
=======
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

        String[] opciones = {"Verificar Stock", "Solictar Stock", "Ver Órdenes", "Emitir Órdenes"};

        int seleccion = JOptionPane.showOptionDialog(null, "Que quiere hacer hoy?", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        switch(seleccion){
            case 0:
                JOptionPane.showMessageDialog(null, "Minerales en stock");
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Solicitar Minerales");
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
>>>>>>> Stashed changes

}