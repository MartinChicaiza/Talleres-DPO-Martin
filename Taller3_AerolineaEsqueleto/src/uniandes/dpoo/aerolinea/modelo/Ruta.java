package uniandes.dpoo.aerolinea.modelo;

/**
 * Esta clase tiene la información de una ruta entre dos aeropuertos que cubre una aerolínea.
 */
public class Ruta
{
	// TODO completar atributos
	private String	codigoRuta;
	private String horaSalida;
	private String horaLlegada;
	private Aeropuerto destino;
	private Aeropuerto origen;
	
	
	
	
    // TODO completar metodos
	
	public String getCodigoRuta()
	{
		return this.codigoRuta;
	}
	
	public Aeropuerto getOrigen() 
	{
		return this.origen;
	}
	
	public Aeropuerto getDestino() 
	{
		return this.destino;
	}
	
	public String getHoraSalida() 
	{
		return this.horaSalida;
	}
	
	public String getHoraLlegada() 
	{
		return this.horaLlegada;
	}
	
	public int getDuracion() 
	{
		int horasLlegada = getHoras(this.horaLlegada);
		int minutosLlegada = getMinutos(this.horaLlegada);
		
		int horasSalida = getHoras(this.horaSalida);
		int minutosSalida = getMinutos(this.horaSalida);
		
		if (horasSalida > horasLlegada) {
			horasLlegada = horasLlegada + 24;
		}
		
		int duracionHoras =  horasLlegada - horasSalida;
		
		
		
		int duracionMinutos =  minutosLlegada - minutosSalida;
		
		int duracionTotal = (duracionHoras * 60) + duracionMinutos;
		
		return duracionTotal;
	}

    /**
     * Dada una cadena con una hora y minutos, retorna los minutos.
     * 
     * Por ejemplo, para la cadena '715' retorna 15.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de minutos entre 0 y 59
     */
    public static int getMinutos( String horaCompleta )
    {
        int minutos = Integer.parseInt( horaCompleta ) % 100;
        return minutos;
    }

    /**
     * Dada una cadena con una hora y minutos, retorna las horas.
     * 
     * Por ejemplo, para la cadena '715' retorna 7.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de horas entre 0 y 23
     */
    public static int getHoras( String horaCompleta )
    {
        int horas = Integer.parseInt( horaCompleta ) / 100;
        return horas;
    }
    
    // TODO completar Constructor
    
    public Ruta(String	codigoRuta, String horaSalida, String horaLlegada, Aeropuerto destino, Aeropuerto origen) 
    {
    	this.codigoRuta = codigoRuta;
    	this.horaSalida = horaSalida;
    	this.horaLlegada = horaLlegada;
    	this.destino = destino;
    	this.origen = origen;
    }

    
}
