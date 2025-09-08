package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {

	
    protected static final int COSTO_POR_KM = 1000;

    public CalculadoraTarifasTemporadaAlta() {}

    @Override
    protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        int km = (vuelo == null) ? 0 : calcularDistanciaVuelo(vuelo.getRuta());
        long base = (long) km * (long) COSTO_POR_KM;
        return (int) Math.max(0L, base);
    }

    @Override
    protected double calcularPorcentajeDescuento(Cliente cliente) {
        return 0.0;
    }
}