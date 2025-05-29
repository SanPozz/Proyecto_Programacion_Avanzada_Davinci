package Clases;

public class Mineral {

    private int idMineral;
    private String tipo;
    private double pureza;
    private Mina mina;

    public Mineral(int idMineral, String tipo, double pureza) {
        this.idMineral = idMineral;
        this.tipo = tipo;
        this.pureza = pureza;
        
    }

    public int getIdMineral() {
        return idMineral;
    }

    public void setIdMineral(int idMineral) {
        this.idMineral = idMineral;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPureza() {
        return pureza;
    }

    public void setPureza(double pureza) {
        this.pureza = pureza;
    }

    public Mina getMina() {
        return mina;
    }

    public void setMina(Mina mina) {
        this.mina = mina;
    }

    @Override
    public String toString() {
        return "Mineral{" +
                "idMineral=" + idMineral +
                ", tipo='" + tipo + '\'' +
                ", pureza=" + pureza +
                ", mina=" + mina +
                '}';
    }
}
