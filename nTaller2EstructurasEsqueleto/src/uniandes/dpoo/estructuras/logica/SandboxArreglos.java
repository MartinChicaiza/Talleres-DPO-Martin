package uniandes.dpoo.estructuras.logica;

import java.util.HashMap;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre arreglos de enteros y de cadenas.
 *
 * Todos los métodos deben operar sobre los atributos arregloEnteros y arregloCadenas.
 * 
 * No pueden agregarse nuevos atributos.
 * 
 * Implemente los métodos usando operaciones sobre arreglos (ie., no haga cosas como construir listas para evitar la manipulación de arreglos).
 */
public class SandboxArreglos
{
    /**
     * Un arreglo de enteros para realizar varias de las siguientes operaciones.
     * 
     * Ninguna posición del arreglo puede estar vacía en ningún momento.
     */
    private int[] arregloEnteros;

    /**
     * Un arreglo de cadenas para realizar varias de las siguientes operaciones
     * 
     * Ninguna posición del arreglo puede estar vacía en ningún momento.
     */
    private String[] arregloCadenas;

    /**
     * Crea una nueva instancia de la clase con los dos arreglos inicializados pero vacíos (tamaño 0)
     */
    public SandboxArreglos( )
    {
        arregloEnteros = new int[]{};
        arregloCadenas = new String[]{};
    }

    /**
     * Retorna una copia del arreglo de enteros, es decir un nuevo arreglo del mismo tamaño que contiene copias de los valores del arreglo original
     * @return Una copia del arreglo de enteros
     */
    public int[] getCopiaEnteros( )
    {
    	int[] copiaArreglosEnteros = new int[this.arregloEnteros.length];

        for (int i = 0; i < this.arregloEnteros.length; i++) {
            copiaArreglosEnteros[i] = this.arregloEnteros[i];
        }

        return copiaArreglosEnteros;
    }

    /**
     * Retorna una copia del arreglo de cadenas, es decir un nuevo arreglo del mismo tamaño que contiene copias de los valores del arreglo original
     * @return Una copia del arreglo de cadenas
     */
    public String[] getCopiaCadenas( )
    {
    	String[] copiaArreglosCadenas = new String[this.arregloCadenas.length];

        for (int i = 0; i < this.arregloCadenas.length; i++) {
            copiaArreglosCadenas[i] = this.arregloCadenas[i];
        }

        return copiaArreglosCadenas;
    }

    /**
     * Retorna la cantidad de valores en el arreglo de enteros
     * @return
     */
    public int getCantidadEnteros( )
    {
    	int numEnteros = this.arregloEnteros.length;
        return numEnteros;
    }

    /**
     * Retorna la cantidad de valores en el arreglo de cadenas
     * @return
     */
    public int getCantidadCadenas( )
    {
    	int contador = 0;
        for (String s : this.arregloCadenas) {
            if (s != null) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Agrega un nuevo valor al final del arreglo. Es decir que este método siempre debería aumentar en 1 la capacidad del arreglo.
     * 
     * @param entero El valor que se va a agregar.
     */
    public void agregarEntero( int entero )
    {
    	int[] nuevoArregloEnteros = new int[this.arregloEnteros.length + 1];
    	
    	for(int i = 0; i < this.arregloEnteros.length; i++) {
    		nuevoArregloEnteros[i] = this.arregloEnteros[i];
    	}
    	
    	nuevoArregloEnteros[nuevoArregloEnteros.length - 1] = entero;
    	this.arregloEnteros = nuevoArregloEnteros;
    }

    /**
     * Agrega un nuevo valor al final del arreglo. Es decir que este método siempre debería aumentar en 1 la capacidad del arreglo.
     * 
     * @param cadena La cadena que se va a agregar.
     */
    public void agregarCadena( String cadena )
    {
    	String[] nuevoArregloCadena = new String[this.arregloCadenas.length + 1];
    	
    	for(int i = 0; i < this.arregloCadenas.length; i++) {
    		nuevoArregloCadena[i] = this.arregloCadenas[i];
    	}
    	
    	nuevoArregloCadena[nuevoArregloCadena.length - 1] = cadena;
    	this.arregloCadenas = nuevoArregloCadena;
    }

    /**
     * Elimina todas las apariciones de un determinado valor dentro del arreglo de enteros
     * @param valor El valor que se va eliminar
     */
    public void eliminarEntero( int valor )
    {
    	int contador = 0;

        
        for (int num : this.arregloEnteros) {
            if (num != valor) {
                contador++;
            }
        }

        
        int[] nuevoArreglo = new int[contador];
        int j = 0;

        for (int num : this.arregloEnteros) {
            if (num != valor) {
                nuevoArreglo[j] = num;
                j++;
            }
        }

        
        this.arregloEnteros = nuevoArreglo;
    }

    /**
     * Elimina todas las apariciones de un determinado valor dentro del arreglo de cadenas
     * @param cadena La cadena que se va eliminar
     */
    public void eliminarCadena( String cadena )
    {
    	int contador = 0;

        
        for (String cad : this.arregloCadenas) {
            if (cad != cadena) {
                contador++;
            }
        }

        
        String[] nuevoArreglo = new String[contador];
        int j = 0;

        for (String cad : this.arregloCadenas) {
            if (cad != cadena) {
                nuevoArreglo[j] = cad;
                j++;
            }
        }

        
        this.arregloCadenas = nuevoArreglo;
    }

    /**
     * Inserta un nuevo entero en el arreglo de enteros.
     * 
     * @param entero El nuevo valor que debe agregarse
     * @param posicion La posición donde debe quedar el nuevo valor en el arreglo aumentado. Si la posición es menor a 0, se inserta el valor en la primera posición. Si la
     *        posición es mayor que el tamaño del arreglo, se inserta el valor en la última posición.
     */
    public void insertarEntero( int entero, int posicion )
    {
    	if (posicion < 0) {
            posicion = 0;
        } else if (posicion > this.arregloEnteros.length) {
            posicion = this.arregloEnteros.length;
        }

        int[] nuevoArregloEnteros = new int[this.arregloEnteros.length + 1];

        // Copiar los elementos antes de la posición
        for (int i = 0; i < posicion; i++) {
            nuevoArregloEnteros[i] = this.arregloEnteros[i];
        }

        // Insertar el nuevo entero
        nuevoArregloEnteros[posicion] = entero;

        // Copiar el resto de elementos después de la posición
        for (int i = posicion; i < this.arregloEnteros.length; i++) {
            nuevoArregloEnteros[i + 1] = this.arregloEnteros[i];
        }

        this.arregloEnteros = nuevoArregloEnteros;
 
    }

    /**
     * Elimina un valor del arreglo de enteros dada su posición.
     * @param posicion La posición donde está el elemento que debe ser eliminado. Si el parámetro posicion no corresponde a ninguna posición del arreglo de enteros, el método
     *        no debe hacer nada.
     */
    public void eliminarEnteroPorPosicion( int posicion )
    {
    	if (posicion >= 0 && posicion < this.arregloEnteros.length) {
            
            int[] nuevoArregloEnteros = new int[this.arregloEnteros.length - 1];
            
            int j = 0; 
            for (int i = 0; i < this.arregloEnteros.length; i++) {
                if (i != posicion) { 
                    nuevoArregloEnteros[j] = this.arregloEnteros[i];
                    j++;
                }
            }
            
            this.arregloEnteros = nuevoArregloEnteros;
        }
    }

    /**
     * Reinicia el arreglo de enteros con los valores contenidos en el arreglo del parámetro 'valores' truncados.
     * 
     * Es decir que si el valor fuera 3.67, en el nuevo arreglo de enteros debería quedar el entero 3.
     * @param valores Un arreglo de valores decimales.
     */
    public void reiniciarArregloEnteros( double[] valores )
    {
    	this.arregloEnteros = new int[valores.length];
        for (int i = 0; i < valores.length; i++) {
            this.arregloEnteros[i] = (int) valores[i]; 
        }
    }

    /**
     * Reinicia el arreglo de cadenas con las representaciones como Strings de los objetos contenidos en el arreglo del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Un arreglo de objetos
     */
    public void reiniciarArregloCadenas( Object[] objetos )
    {
    	this.arregloCadenas = new String[objetos.length];
        for (int i = 0; i < objetos.length; i++) {
            this.arregloCadenas[i] = objetos[i].toString();
        }
    }

    /**
     * Modifica el arreglo de enteros para que todos los valores sean positivos.
     * 
     * Es decir que si en una posición había un valor negativo, después de ejecutar el método debe quedar el mismo valor muliplicado por -1.
     */
    public void volverPositivos( )
    {
    	for (int i = 0; i < this.arregloEnteros.length; i++) {
            if (this.arregloEnteros[i] < 0) {
                this.arregloEnteros[i] *= -1;
            }
        }
    }

    /**
     * Modifica el arreglo de enteros para que todos los valores queden organizados de menor a mayor.
     */
    public void organizarEnteros( )
    {
    	 for (int i = 0; i < this.arregloEnteros.length - 1; i++) {
             for (int j = i + 1; j < this.arregloEnteros.length; j++) {
                 if (this.arregloEnteros[i] > this.arregloEnteros[j]) {
                     int temp = this.arregloEnteros[i];
                     this.arregloEnteros[i] = this.arregloEnteros[j];
                     this.arregloEnteros[j] = temp;
                 }
             }
         }
    }

    /**
     * Modifica el arreglo de cadenas para que todos los valores queden organizados lexicográficamente.
     */
    public void organizarCadenas( )
    {
    	for (int i = 0; i < this.arregloCadenas.length - 1; i++) {
            for (int j = i + 1; j < this.arregloCadenas.length; j++) {
                if (this.arregloCadenas[i].compareTo(this.arregloCadenas[j]) > 0) {
                    String temp = this.arregloCadenas[i];
                    this.arregloCadenas[i] = this.arregloCadenas[j];
                    this.arregloCadenas[j] = temp;
                }
            }
        }
    }

    /**
     * Cuenta cuántas veces aparece el valor recibido por parámetro en el arreglo de enteros
     * @param valor El valor buscado
     * @return La cantidad de veces que aparece el valor
     */
    public int contarApariciones( int valor )
    {
    	 int contador = 0;
         for (int num : this.arregloEnteros) {
             if (num == valor) contador++;
         }
         return contador;
    }

    /**
     * Cuenta cuántas veces aparece la cadena recibida por parámetro en el arreglo de cadenas.
     * 
     * La búsqueda no debe diferenciar entre mayúsculas y minúsculas.
     * @param cadena La cadena buscada
     * @return La cantidad de veces que aparece la cadena
     */
    public int contarApariciones( String cadena )
    {
    	int contador = 0;
        for (String s : this.arregloCadenas) {
            if (s != null && s.equalsIgnoreCase(cadena)) contador++;
        }
        return contador;
    }

    /**
     * Busca en qué posiciones del arreglo de enteros se encuentra el valor que se recibe en el parámetro
     * @param valor El valor que se debe buscar
     * @return Un arreglo con los números de las posiciones del arreglo de enteros en las que se encuentra el valor buscado. Si el valor no se encuentra, el arreglo retornado
     *         es de tamaño 0.
     */
    public int[] buscarEntero( int valor )
    {
    	int count = 0;
        for (int num : this.arregloEnteros) {
            if (num == valor) count++;
        }

        int[] posiciones = new int[count];
        int j = 0;
        for (int i = 0; i < this.arregloEnteros.length; i++) {
            if (this.arregloEnteros[i] == valor) {
                posiciones[j++] = i;
            }
        }
        return posiciones;
    }

    /**
     * Calcula cuál es el rango de los enteros (el valor mínimo y el máximo).
     * @return Un arreglo con dos posiciones: en la primera posición, debe estar el valor mínimo en el arreglo de enteros; en la segunda posición, debe estar el valor máximo
     *         en el arreglo de enteros. Si el arreglo está vacío, debe retornar un arreglo vacío.
     */
    public int[] calcularRangoEnteros( )
    {
    	if (this.arregloEnteros.length == 0) return new int[0];

        int min = this.arregloEnteros[0];
        int max = this.arregloEnteros[0];
        for (int num : this.arregloEnteros) {
            if (num < min) min = num;
            if (num > max) max = num;
        }
        return new int[]{min, max};
    }

    /**
     * Calcula un histograma de los valores del arreglo de enteros y lo devuelve como un mapa donde las llaves son los valores del arreglo y los valores son la cantidad de
     * veces que aparece cada uno en el arreglo de enteros.
     * @return Un mapa con el histograma de valores.
     */
    public HashMap<Integer, Integer> calcularHistograma( )
    {
    	 HashMap<Integer, Integer> histograma = new HashMap<>();
         for (int num : this.arregloEnteros) {
             histograma.put(num, histograma.getOrDefault(num, 0) + 1);
         }
         return histograma;
    }

    /**
     * Cuenta cuántos valores dentro del arreglo de enteros están repetidos.
     * @return La cantidad de enteos diferentes que aparecen más de una vez
     */
    public int contarEnterosRepetidos( )
    {
    	HashMap<Integer, Integer> histograma = calcularHistograma();
        int contador = 0;
        for (int valor : histograma.values()) {
            if (valor > 1) contador++;
        }
        return contador;
    }

    /**
     * Compara el arreglo de enteros con otro arreglo de enteros y verifica si son iguales, es decir que contienen los mismos elementos exactamente en el mismo orden.
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si los arreglos son idénticos y false de lo contrario
     */
    public boolean compararArregloEnteros( int[] otroArreglo )
    {
    	 if (this.arregloEnteros.length != otroArreglo.length) return false;
         for (int i = 0; i < this.arregloEnteros.length; i++) {
             if (this.arregloEnteros[i] != otroArreglo[i]) return false;
         }
         return true;
    }

    /**
     * Compara el arreglo de enteros con otro arreglo de enteros y verifica que tengan los mismos elementos, aunque podría ser en otro orden.
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si los elementos en los dos arreglos son los mismos
     */
    public boolean mismosEnteros( int[] otroArreglo )
    {
    	if (this.arregloEnteros.length != otroArreglo.length) return false;

        int[] copia1 = this.getCopiaEnteros();
        int[] copia2 = new int[otroArreglo.length];
        System.arraycopy(otroArreglo, 0, copia2, 0, otroArreglo.length);

                for (int i = 0; i < copia1.length - 1; i++) {
            for (int j = i + 1; j < copia1.length; j++) {
                if (copia1[i] > copia1[j]) {
                    int temp = copia1[i];
                    copia1[i] = copia1[j];
                    copia1[j] = temp;
                }
                if (copia2[i] > copia2[j]) {
                    int temp = copia2[i];
                    copia2[i] = copia2[j];
                    copia2[j] = temp;
                }
            }
        }

        for (int i = 0; i < copia1.length; i++) {
            if (copia1[i] != copia2[i]) return false;
        }
        return true;
    }

    /**
     * Cambia los elementos del arreglo de enteros por una nueva serie de valores generada de forma aleatoria.
     * 
     * Para generar los valores se debe partir de una distribución uniforme usando Math.random().
     * 
     * Los números en el arreglo deben quedar entre el valor mínimo y el máximo.
     * @param cantidad La cantidad de elementos que debe haber en el arreglo
     * @param minimo El valor mínimo para los números generados
     * @param maximo El valor máximo para los números generados
     */
    public void generarEnteros( int cantidad, int minimo, int maximo )
    {
    	 this.arregloEnteros = new int[cantidad];
         for (int i = 0; i < cantidad; i++) {
             this.arregloEnteros[i] = minimo + (int)(Math.random() * ((maximo - minimo) + 1));
         }
    }

}
