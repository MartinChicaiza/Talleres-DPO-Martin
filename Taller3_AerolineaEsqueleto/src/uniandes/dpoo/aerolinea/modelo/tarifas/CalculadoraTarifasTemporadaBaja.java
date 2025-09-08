package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;


public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {

   
    protected static final int COSTO_POR_KM_NATURAL = 600;
    protected static final int COSTO_POR_KM_CORPORATIVO = 900;
    protected static final double DESCUENTO_PEQ = 0.02; 
    protected static final double DESCUENTO_MEDIANAS = 0.10; 
    protected static final double DESCUENTO_GRANDES = 0.20; 

    public CalculadoraTarifasTemporadaBaja() {}

    @Override
    protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        int km = (vuelo == null) ? 0 : calcularDistanciaVuelo(vuelo.getRuta());
        boolean esCorporativo = cliente instanceof ClienteCorporativo;
        int costoPorKm = esCorporativo ? COSTO_POR_KM_CORPORATIVO : COSTO_POR_KM_NATURAL;
        long base = (long) km * (long) costoPorKm;
        return (int) Math.max(0L, base);
    }

    @Override
    protected double calcularPorcentajeDescuento(Cliente cliente) {
        if (!(cliente instanceof ClienteCorporativo)) {
            return 0.0; 
        }
        ClienteCorporativo corp = (ClienteCorporativo) cliente;
        int tam = corp.getTamanoEmpresa(); 

        
        double descuento = 0.0;
        if (tam == ClienteCorporativo.GRANDE) {
            descuento = DESCUENTO_GRANDES;
        } else if (tam == ClienteCorporativo.MEDIANA) {
            descuento = DESCUENTO_MEDIANAS;
        } else if (tam == ClienteCorporativo.PEQUENA) {
            descuento = DESCUENTO_PEQ;
        }
        return descuento;
    }
}