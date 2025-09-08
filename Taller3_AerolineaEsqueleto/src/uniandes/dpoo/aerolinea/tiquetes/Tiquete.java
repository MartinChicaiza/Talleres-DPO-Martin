package uniandes.dpoo.aerolinea.tiquetes;

import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

public class Tiquete {

	private String codigo;
	private int tarifa;
	private boolean usado;
	private Cliente cliente;
	private Vuelo vuelo;
	
	
	public String getCodigo() {
		return codigo;
	}

	public int getTarifa() {
		return tarifa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Vuelo getVuelo() {
		return vuelo;
	}
	
	public void marcarComoUsado() 
	{
		this.usado = true;
	}
	
	public boolean esUsado() 
	{
		return this.usado;
	}
	
	public Tiquete(String codigo, Vuelo vuelo, Cliente clienteComprador, int tarifa) {
		super();
		this.codigo = codigo;
		this.tarifa = tarifa;
		this.cliente = clienteComprador;
		this.vuelo = vuelo;
	}
	
	
	
	
}
