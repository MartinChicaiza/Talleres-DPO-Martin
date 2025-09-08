package uniandes.dpoo.aerolinea.modelo;

import java.util.Map;
import java.util.Collection;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import java.util.HashMap;

public class Vuelo {
	
	//Attributos
	private String fecha;
	private Avion avion;
	private Ruta ruta;
	private Map <String,Tiquete> Tiquetes = new HashMap<String, Tiquete>( );
	
	//Metodos
	public String getFecha() 
	{
		return this.fecha;
	}
	public Avion getAvion() 
	{
		return this.avion;
	}
	public Ruta getRuta() 
	{
		return this.ruta;
	}
	public Collection<Tiquete> getTiquetes() 
	{
		return this.Tiquetes.values();
	}
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad)
            throws VueloSobrevendidoException
    {
        if (cliente == null || calculadora == null || cantidad <= 0) {
            return 0;
        }

        int capacidad = (this.avion == null) ? 0 : this.avion.getCapacidad();
        int yaVendidos = this.Tiquetes.size();
        int espacioDisponible = capacidad - yaVendidos;

        if (espacioDisponible < cantidad) {
            throw new VueloSobrevendidoException(this);
        }

        int total = 0;
        int i = 0;
        while (i < cantidad) {
            int tarifa = calculadora.calcularTarifa(this, cliente);
            Tiquete nuevo = GeneradorTiquetes.generarTiquete(this, cliente, tarifa);

            
            this.Tiquetes.put(nuevo.getCodigo(), nuevo);
            cliente.agregarTiquete(nuevo);

            total = total + tarifa;
            i = i + 1;
        }
        return total;
    }
	
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Vuelo other = (Vuelo) obj;

        
        String f1 = this.fecha;
        String f2 = other.fecha;
        if (f1 == null ? f2 != null : !f1.equals(f2)) return false;

        
        String c1 = (this.ruta == null) ? null : this.ruta.getCodigoRuta();
        String c2 = (other.ruta == null) ? null : other.ruta.getCodigoRuta();
        return (c1 == null) ? (c2 == null) : c1.equals(c2);
    }
	
	
	public Vuelo (String fecha, Avion avion, Ruta ruta) 
	{
		this.avion = avion;
		this.fecha = fecha;
		this.ruta = ruta;
	}
	
}
