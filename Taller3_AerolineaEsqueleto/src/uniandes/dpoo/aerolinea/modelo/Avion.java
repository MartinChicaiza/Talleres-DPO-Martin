package uniandes.dpoo.aerolinea.modelo;

public class Avion {

	//Atributos
	private String nombre;
	private int capacidad;
	
	//Metodos
	public String getNombre() {
		return this.nombre;
	}
	public int getCapacidad() {
		return this.capacidad;
	}
	
	//Constructores
	public Avion(String nombre, int capacidad) 
	{
		this.nombre = nombre;
		this.capacidad = capacidad;
	}
}
