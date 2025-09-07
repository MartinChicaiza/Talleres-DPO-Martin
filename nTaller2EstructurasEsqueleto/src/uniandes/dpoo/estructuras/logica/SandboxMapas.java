package uniandes.dpoo.estructuras.logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre mapas.
 *
 * Todos los métodos deben operar sobre el atributo mapaCadenas que se declara como un Map.
 * 
 * En este mapa, las llaves serán cadenas y los valores serán también cadenas. La relación entre los dos será que cada llave será igual a la cadena del valor, pero invertida.
 * 
 * El objetivo de usar el tipo Map es que sólo puedan usarse métodos de esa interfaz y no métodos adicionales provistos por la implementación concreta (HashMap).
 * 
 * No pueden agregarse nuevos atributos.
 */
public class SandboxMapas
{
    /**
     * Un mapa de cadenas para realizar varias de las siguientes operaciones.
     * 
     * Las llaves del mapa son cadenas, así como los valores.
     * 
     * Las llaves corresponden a invertir la cadena que aparece asociada a cada llave.
     */
    private Map<String, String> mapaCadenas;

    /**
     * Crea una nueva instancia de la clase con las dos listas inicializadas pero vacías
     */
    public SandboxMapas( )
    {
        mapaCadenas = new HashMap<String, String>( );
    }

    /**
     * Retorna una lista con las cadenas del mapa (los valores) ordenadas lexicográficamente
     * @return Una lista ordenada con las cadenas que conforman los valores del mapa
     */
    public List<String> getValoresComoLista( )
    {
    	 List<String> valores = new ArrayList<>(mapaCadenas.values());
         for (int i = 0; i < valores.size(); i++) {
             for (int j = i + 1; j < valores.size(); j++) {
                 if (valores.get(i).compareTo(valores.get(j)) > 0) {
                     String temp = valores.get(i);
                     valores.set(i, valores.get(j));
                     valores.set(j, temp);
                 }
             }
         }
         return valores;
    }

    /**
     * Retorna una lista con las llaves del mapa ordenadas lexicográficamente de mayor a menor
     * @return Una lista ordenada con las cadenas que conforman las llaves del mapa
     */
    public List<String> getLlavesComoListaInvertida( )
    {
    	 List<String> llaves = new ArrayList<>(mapaCadenas.keySet());
         for (int i = 0; i < llaves.size(); i++) {
             for (int j = i + 1; j < llaves.size(); j++) {
                 if (llaves.get(i).compareTo(llaves.get(j)) < 0) {
                     String temp = llaves.get(i);
                     llaves.set(i, llaves.get(j));
                     llaves.set(j, temp);
                 }
             }
         }
         return llaves;
    }

    /**
     * Retorna la cadena que sea lexicográficamente menor dentro de las llaves del mapa .
     * 
     * Si el mapa está vacío, debe retornar null.
     * @return
     */
    public String getPrimera( )
    {
    	if (mapaCadenas.isEmpty()) {
            return null;
        }
        String menor = null;
        for (String llave : mapaCadenas.keySet()) {
            if (menor == null || llave.compareTo(menor) < 0) {
                menor = llave;
            }
        }
        return menor;
    }

    /**
     * Retorna la cadena que sea lexicográficamente mayor dentro de los valores del mapa
     * 
     * Si el conjunto está vacío, debe retornar null.
     * @return
     */
    public String getUltima( )
    {
    	if (mapaCadenas.isEmpty()) {
            return null;
        }
        String mayor = null;
        for (String valor : mapaCadenas.values()) {
            if (mayor == null || valor.compareTo(mayor) > 0) {
                mayor = valor;
            }
        }
        return mayor;
    }

    /**
     * Retorna una colección con las llaves del mapa, convertidas a mayúsculas.
     * 
     * El orden de las llaves retornadas no importa.
     * @return Una lista de cadenas donde todas las cadenas están en mayúsculas
     */
    public Collection<String> getLlaves( )
    {
    	List<String> mayusculas = new ArrayList<>();
        for (String llave : mapaCadenas.keySet()) {
            mayusculas.add(llave.toUpperCase());
        }
        return mayusculas;
    }

    /**
     * Retorna la cantidad de *valores* diferentes en el mapa
     * @return
     */
    public int getCantidadCadenasDiferentes( )
    {
    	List<String> diferentes = new ArrayList<>();
        for (String valor : mapaCadenas.values()) {
            if (!diferentes.contains(valor)) {
                diferentes.add(valor);
            }
        }
        return diferentes.size();
    }

    /**
     * Agrega un nuevo valor al mapa de cadenas: el valor será el recibido por parámetro, y la llave será la cadena invertida
     * 
     * Este método podría o no aumentar el tamaño del mapa, dependiendo de si ya existía la cadena en el mapa
     * 
     * @param cadena La cadena que se va a agregar al mapa
     */
    public void agregarCadena( String cadena )
    {
    	String invertida = "";
        for (int i = cadena.length() - 1; i >= 0; i--) {
            invertida += cadena.charAt(i);
        }
        mapaCadenas.put(invertida, cadena);
    }

    /**
     * Elimina una cadena del mapa, dada la llave
     * @param cadena La llave para identificar el valor que se debe eliminar
     */
    public void eliminarCadenaConLLave( String llave )
    {
    	mapaCadenas.remove(llave);
    }

    /**
     * Elimina una cadena del mapa, dado el valor
     * @param cadena El valor que se debe eliminar
     */
    public void eliminarCadenaConValor( String valor )
    {
    	String llaveAEliminar = null;
        for (Map.Entry<String, String> entrada : mapaCadenas.entrySet()) {
            if (entrada.getValue().equals(valor)) {
                llaveAEliminar = entrada.getKey();
                break;
            }
        }
        if (llaveAEliminar != null) {
            mapaCadenas.remove(llaveAEliminar);
        }
    }

    /**
     * Reinicia el mapa de cadenas con las representaciones como Strings de los objetos contenidos en la lista del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Una lista de objetos
     */
    public void reiniciarMapaCadenas( List<Object> objetos )
    {
    	mapaCadenas.clear();
        for (Object objeto : objetos) {
            String valor = objeto.toString();
            String invertida = "";
            for (int i = valor.length() - 1; i >= 0; i--) {
                invertida += valor.charAt(i);
            }
            mapaCadenas.put(invertida, valor);
        }
    }

    /**
     * Modifica el mapa de cadenas reemplazando las llaves para que ahora todas estén en mayúsculas pero sigan conservando las mismas cadenas asociadas.
     */
    public void volverMayusculas( )
    {
    	Map<String, String> nuevoMapa = new HashMap<>();
        for (Map.Entry<String, String> entrada : mapaCadenas.entrySet()) {
            nuevoMapa.put(entrada.getKey().toUpperCase(), entrada.getValue());
        }
        mapaCadenas = nuevoMapa;
    }

    /**
     * Verifica si todos los elementos en el arreglo de cadenas del parámetro hacen parte del mapa de cadenas (de los valores)
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si todos los elementos del arreglo están dentro de los valores del mapa
     */
    public boolean compararValores( String[] otroArreglo )
    {
    	Collection<String> valores = mapaCadenas.values();
        for (String elemento : otroArreglo) {
            if (!valores.contains(elemento)) {
                return false;
            }
        }
        return true;
    
    }

}
