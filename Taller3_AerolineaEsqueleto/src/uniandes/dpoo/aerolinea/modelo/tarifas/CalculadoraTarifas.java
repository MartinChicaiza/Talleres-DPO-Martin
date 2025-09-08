package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;



public abstract class CalculadoraTarifas {

	
	
    public static final double IMPUESTO = 0.28;

    public CalculadoraTarifas() {}

    
    
    protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);

    
    
    protected int calcularDistanciaVuelo(Ruta ruta) {
        if (ruta == null) return 0;
        Aeropuerto origen = ruta.getOrigen();
        Aeropuerto destino = ruta.getDestino();
        if (origen == null || destino == null) return 0;
        return Aeropuerto.calcularDistancia(origen, destino);
    }

    
    
    protected abstract double calcularPorcentajeDescuento(Cliente cliente);
    
    
    protected int calcularValorImpuestos(int costoBase) {
        if (costoBase <= 0) return 0;
        long impuestos = Math.round(costoBase * IMPUESTO);
        return (int) Math.max(0L, impuestos);
    }

    
    public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
        int costoBase = Math.max(0, calcularCostoBase(vuelo, cliente));
        double pDesc = calcularPorcentajeDescuento(cliente);
        if (pDesc < 0.0) pDesc = 0.0;
        if (pDesc > 1.0) pDesc = 1.0;

        double conDescuento = costoBase * (1.0 - pDesc);
        int impuestos = calcularValorImpuestos(costoBase);

        long total = Math.round(conDescuento) + (long) impuestos;
        return (int) Math.max(0L, total);
    }
}