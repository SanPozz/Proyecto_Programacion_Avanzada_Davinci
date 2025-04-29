package Clases;

import java.util.ArrayList;

public class Mina {

    int idMina;
    String ubicacion;
    ArrayList<Camion> camiones;

    public int getIdMina() {
        return idMina;
    }

    public void setIdMina(int idMina) {
        this.idMina = idMina;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ArrayList<Camion> getCamiones() {
        return camiones;
    }

    public void setCamiones(ArrayList<Camion> camiones) {
        this.camiones = camiones;
    }

    @Override
    public String toString() {
        return "Mina{" +
                "idMina=" + idMina +
                ", ubicacion='" + ubicacion + '\'' +
                ", camiones=" + camiones +
                '}';
    }
}
