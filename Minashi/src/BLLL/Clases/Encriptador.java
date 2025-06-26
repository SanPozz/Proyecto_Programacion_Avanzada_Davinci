package BLLL.Clases;

import org.mindrot.jbcrypt.BCrypt;

public class Encriptador {

  public static String encriptarPass(String pass){
    return BCrypt.hashpw(pass, BCrypt.gensalt());
  }

  public static Boolean verificarPass(String pass, String hashPass){
    return BCrypt.checkpw(pass, hashPass);
  }

}