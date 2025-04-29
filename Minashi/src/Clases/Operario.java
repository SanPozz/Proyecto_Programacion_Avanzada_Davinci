package Clases;

public class Operario extends Empleado{

    private String localizacion;

    public Operario(int idEmpleado, String nombreEmpleado, String apellidoEmpleado, String localizacion) {
        super(idEmpleado, nombreEmpleado, apellidoEmpleado, "Operario");
        this.localizacion = localizacion;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }


    public void registrarMineral(Mineral mineral){}


    @Override
    public String toString() {
        return "Operario{" +
                "localizacion='" + localizacion + '\'' +
                '}';
    }
}
