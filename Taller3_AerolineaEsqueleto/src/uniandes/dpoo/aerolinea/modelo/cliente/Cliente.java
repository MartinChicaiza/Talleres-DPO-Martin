package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {

    
    protected List<Tiquete> tiquetesSinUsar;
    protected List<Tiquete> tiquetesUsados;

    public Cliente() {
        this.tiquetesSinUsar = new ArrayList<>();
        this.tiquetesUsados  = new ArrayList<>();
    }

    public abstract String getTipoCliente();
    public abstract String getIdentificador();

    public void agregarTiquete(Tiquete tiquete) {
        if (tiquete != null) {
            this.tiquetesSinUsar.add(tiquete);
        }
    }

    public int calcularValorTotalTiquetes() {
        int suma = 0;
        for (Tiquete t : this.tiquetesSinUsar) {
            if (t != null) {
                suma += t.getTarifa();
            }
        }
        return suma;
    }

    
    public void usarTiquetes(Vuelo vuelo) {
        if (vuelo == null) return;

        for (int i = tiquetesSinUsar.size() - 1; i >= 0; i--) {
            Tiquete t = tiquetesSinUsar.get(i);
            if (t != null) {
                boolean esDeEsteCliente = (t.getCliente() == this);
                boolean esDelVuelo      = (t.getVuelo() != null && t.getVuelo().equals(vuelo));
                boolean noUsado         = !t.esUsado();

                if (esDeEsteCliente && esDelVuelo && noUsado) {
                    t.marcarComoUsado();
                    tiquetesSinUsar.remove(i);   
                    tiquetesUsados.add(t);       
                }
            }
        }
    }
}
