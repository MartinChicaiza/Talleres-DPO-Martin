package uniandes.dpoo.aerolinea.modelo.cliente;

/** Cliente de tipo natural (persona). */
public class ClienteNatural extends Cliente {

    // Constante del UML
    public static final String NATURAL = "Natural";

    // Atributos
    private final String nombre;

    // Constructor
    public ClienteNatural(String nombre) {
        super();
        this.nombre = nombre;
    }

    @Override
    public String getTipoCliente() {
        return NATURAL;
    }

    @Override
    public String getIdentificador() {
        return this.nombre;
    }
}