package BLLL.Clases;

public class Validaciones {

  static boolean validacionEmail(String mail) {

    String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

    /*

    Usuario: letras, números, puntos o guiones ([\w.-]+)

    Dominio: letras, números, puntos o guiones ([\w.-]+)

    Dominio de nivel superior (como .com, .ar, .org): al menos 2 letras ([a-zA-Z]{2,})

    */

    return mail.matches(regex);

  }

  static boolean validacionPassword(String pass) {

    String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-])[A-Za-z\\d@$!%*?&._-]{8,}$";

    /*
    Al menos 8 caracteres

    Al menos una letra mayúscula

    Al menos una letra minúscula

    Al menos un número

    Al menos un carácter especial (por ejemplo: !@#$%^&*())
    */

    return pass.matches(regex);

  }

  static boolean validacionRepeatedPassword(String pass, String repeatedPass) {
    if (pass.equals(repeatedPass)) {
      return true;
    } else {
      return false;
    }
  }

}
