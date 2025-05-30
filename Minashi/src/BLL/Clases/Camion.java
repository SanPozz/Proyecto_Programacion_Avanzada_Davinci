package BLL.Clases;

public class Camion {

    private String matricula;
    private double cantToneladas;
    private Boolean disponibilidad;
    private String ubicacion;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getCantToneladas() {
        return cantToneladas;
    }

    public void setCantToneladas(double cantToneladas) {
        this.cantToneladas = cantToneladas;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Camion{" +
                "matricula='" + matricula + '\'' +
                ", cantToneladas=" + cantToneladas +
                ", disponibilidad=" + disponibilidad +
                ", ubicacion='" + ubicacion + '\'' +
                '}';
    }
}
