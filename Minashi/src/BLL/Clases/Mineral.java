package BLL.Clases;

import DLL.Repository.ActualizarMineRepository;
import DLL.Repository.MineralesRepository;
import DLL.Repository.RegistrarMineRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Mineral {

    private int idMineral;
    private String nombre;
    private double pureza;
    private double toneladas;
    private double precioTonelada;

    public Mineral() {
    	
    }
    
    public Mineral(int idMineral, String nombre, double pureza, double toneladas,double precioTonelada) {
        this.idMineral = idMineral;
        this.nombre = nombre;
        this.pureza = pureza;
        this.toneladas = toneladas;
        this.precioTonelada=precioTonelada;
    }
    public Mineral( String nombre, double pureza, double toneladas,double precioTonelada) {
        this.nombre = nombre;
        this.pureza = pureza;
        this.toneladas = toneladas;
        this.precioTonelada=precioTonelada;
    }

    public Mineral(String nombre, double pureza, double toneladas,double precioTonelada, int idMineral) {
    	 this.nombre = nombre;
         this.pureza = pureza;
         this.toneladas = toneladas;
         this.precioTonelada=precioTonelada;
         
	}
	public int getIdMineral() {
        return idMineral;
    }

    public void setIdMineral(int idMineral) {
        this.idMineral = idMineral;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String tipo) {
        this.nombre = tipo;
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

    public double getPrecioTonelada() {
		return precioTonelada;
	}
	public void setPrecioTonelada(double precioTonelada) {
		this.precioTonelada = precioTonelada;
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

    static public ArrayList<Mineral> ordenarMinerales(ArrayList<Mineral> minerales, String nombre) {
        ArrayList<Mineral> mineralesOrdenados = null;
        if (nombre.equalsIgnoreCase("nombre")) {
            mineralesOrdenados = minerales.stream()
                    .sorted(Comparator.comparing(Mineral::getNombre))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        if (nombre.equalsIgnoreCase("Pureza")) {
            mineralesOrdenados = minerales.stream()
                    .sorted(Comparator.comparing(Mineral::getPureza).reversed())
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        if (nombre.equalsIgnoreCase("Toneladas")) {
            mineralesOrdenados = minerales.stream()
                    .sorted(Comparator.comparing(Mineral::getToneladas).reversed())
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        return mineralesOrdenados;
    }
    
    public static String EditarMineral(Mineral mineral) {
    	if (mineral.getNombre().isEmpty()||mineral.getPureza()<=0||mineral.getToneladas()<=0||mineral.getPrecioTonelada()<=0) {
			return "No se pudo editar";
		} else {
          return ActualizarMineRepository.editarIdMineral(mineral);
		}
    }
    
    public static String RegisMineral(Mineral mineral) {
        if (mineral == null ||
            mineral.getNombre() == null || mineral.getNombre().isEmpty() ||mineral.getPureza() <= 0 || mineral.getToneladas() <= 0 ||mineral.getPrecioTonelada() <= 0) {
            return RegistrarMineRepository.registrarMineral(mineral);
        }

        return RegistrarMineRepository.registrarMineral(mineral); 
    }
	@Override
	public String toString() {
		return "Mineral [idMineral=" + idMineral + ", nombre=" + nombre + ", pureza=" + pureza + ", toneladas="
				+ toneladas + ", precioTonelada=" + precioTonelada + "]";
	}


    
}
