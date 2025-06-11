package BLL.Clases;

import java.util.ArrayList;

public class Cliente extends Usuario{

    private ArrayList<Mineral> minerales = new ArrayList<>();

    public Cliente(int idUsuario,String nombre, String apellido, int edad, String correo, int rol) {
        super(idUsuario, nombre, apellido, edad, correo, rol);

    }

    public void anadirMineral(Mineral mineral) {
        minerales.add(mineral);
    }





}
