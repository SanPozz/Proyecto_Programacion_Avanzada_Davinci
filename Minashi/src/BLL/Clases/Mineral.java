package BLL.Clases;

import DLL.Repository.MineralesRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Mineral {

    private int idMineral;
    private String tipo;
    private double pureza;
    private double toneladas;

    public Mineral(int idMineral, String tipo, double pureza, double toneladas) {
        this.idMineral = idMineral;
        this.tipo = tipo;
        this.pureza = pureza;
        this.toneladas = toneladas;
    }
    public Mineral( String tipo, double pureza, double toneladas) {
        this.tipo = tipo;
        this.pureza = pureza;
        this.toneladas = toneladas;
    }

    public Mineral(String string, double double1, double double2, int idMineral) {
		
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

    public double getToneladas() {
        return toneladas;
    }

    public void setToneladas(double toneladas) {
        this.toneladas = toneladas;
    }

    static public ArrayList<Mineral> mineralesEnStock() {
            ArrayList<Mineral> minerales = MineralesRepository.encontrarMinerales();
            ArrayList<Mineral> mineralesEnStock = new ArrayList<Mineral>();
            for (Mineral mineral : minerales) {
                if (mineral.getToneladas() > 0) {
                    mineralesEnStock.add(mineral);
                }
            }
            return mineralesEnStock;
    }

    static public ArrayList<Mineral> mineralesPorNombre(String nombre) {
        ArrayList<Mineral> minerales = MineralesRepository.encontrarMineralesPorNombre(nombre);

        if (minerales.isEmpty()) {
            return null;
        }

        return minerales;
    }

    static public ArrayList<Mineral> ordenarMinerales(ArrayList<Mineral> minerales, String tipo) {
        ArrayList<Mineral> mineralesOrdenados = null;
        if (tipo.equalsIgnoreCase("tipo")) {
            mineralesOrdenados = minerales.stream()
                    .sorted(Comparator.comparing(Mineral::getTipo))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        if (tipo.equalsIgnoreCase("Pureza")) {
            mineralesOrdenados = minerales.stream()
                    .sorted(Comparator.comparing(Mineral::getPureza).reversed())
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        if (tipo.equalsIgnoreCase("Toneladas")) {
            mineralesOrdenados = minerales.stream()
                    .sorted(Comparator.comparing(Mineral::getToneladas).reversed())
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        return mineralesOrdenados;
    }


    @Override
    public String toString() {
        return "Mineral{" +
                "idMineral=" + idMineral +
                ", tipo='" + tipo + '\'' +
                ", pureza=" + pureza +
                '}';
    }
}
