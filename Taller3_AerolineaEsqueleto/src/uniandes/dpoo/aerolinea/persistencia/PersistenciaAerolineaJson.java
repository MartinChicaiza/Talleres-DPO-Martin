package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea {

    
    private static final String AVIONES = "aviones";
    private static final String AEROPUERTOS = "aeropuertos";
    private static final String RUTAS = "rutas";
    private static final String VUELOS = "vuelos";

    
    private static final String NOMBRE = "nombre";
    private static final String CAPACIDAD = "capacidad";

    
    private static final String CODIGO = "codigo";
    private static final String NOMBRE_CIUDAD = "nombreCiudad";
    private static final String LATITUD = "latitud";
    private static final String LONGITUD = "longitud";

    
    private static final String ORIGEN = "origen";
    private static final String DESTINO = "destino";
    private static final String HORA_SALIDA  = "horaSalida";
    private static final String HORA_LLEGADA = "horaLlegada";
    private static final String CODIGO_RUTA = "codigoRuta";

    
    private static final String FECHA = "fecha";
    private static final String NOMBRE_AVION = "nombreAvion"; 
    
    @Override
    public void cargarAerolinea(String archivo, Aerolinea aerolinea)
            throws IOException, InformacionInconsistenteException {

        String jsonCompleto = new String(Files.readAllBytes(new File(archivo).toPath()));
        JSONObject raiz = new JSONObject(jsonCompleto);

        Map<String, Aeropuerto> aeropuertosPorCodigo = new HashMap<>();
        if (raiz.has(AEROPUERTOS)) {
            JSONArray jAeropuertos = raiz.getJSONArray(AEROPUERTOS);
            int n = jAeropuertos.length();
            int i = 0;
            while (i < n) {
                JSONObject j = jAeropuertos.getJSONObject(i);
                String nombre = j.getString(NOMBRE);
                String codigo = j.getString(CODIGO);
                String nombreCiudad = j.getString(NOMBRE_CIUDAD);
                double lat = j.getDouble(LATITUD);
                double lon = j.getDouble(LONGITUD);

                Aeropuerto ap = new Aeropuerto(nombre, codigo, nombreCiudad, lat, lon);
                aeropuertosPorCodigo.put(codigo, ap);
                i = i + 1;
            }
        }

        
        if (raiz.has(AVIONES)) {
            JSONArray jAviones = raiz.getJSONArray(AVIONES);
            int n = jAviones.length();
            int i = 0;
            while (i < n) {
                JSONObject j = jAviones.getJSONObject(i);
                String nombre = j.getString(NOMBRE);
                int capacidad = j.getInt(CAPACIDAD);
                aerolinea.agregarAvion(new Avion(nombre, capacidad));
                i = i + 1;
            }
        }

        
        if (raiz.has(RUTAS)) {
            JSONArray jRutas = raiz.getJSONArray(RUTAS);
            int n = jRutas.length();
            int i = 0;
            while (i < n) {
                JSONObject j = jRutas.getJSONObject(i);
                String codRuta = j.getString(CODIGO_RUTA);
                String codOrigen = j.getString(ORIGEN);
                String codDestino = j.getString(DESTINO);
                String horaSalida = j.getString(HORA_SALIDA);
                String horaLlegada = j.getString(HORA_LLEGADA);

                Aeropuerto origen = aeropuertosPorCodigo.get(codOrigen);
                Aeropuerto destino = aeropuertosPorCodigo.get(codDestino);
                if (origen == null) {
                    throw new InformacionInconsistenteException("Aeropuerto de origen inexistente: " + codOrigen);
                }
                if (destino == null) {
                    throw new InformacionInconsistenteException("Aeropuerto de destino inexistente: " + codDestino);
                }

                Ruta ruta = new Ruta(codRuta, horaSalida, horaLlegada, destino, origen);
                aerolinea.agregarRuta(ruta);
                i = i + 1;
            }
        }

        
        if (raiz.has(VUELOS)) {
            JSONArray jVuelos = raiz.getJSONArray(VUELOS);
            int n = jVuelos.length();
            int i = 0;
            while (i < n) {
                JSONObject j = jVuelos.getJSONObject(i);
                String fecha = j.getString(FECHA);
                String codRuta = j.getString(CODIGO_RUTA);
                String nombreAvion = j.getString(NOMBRE_AVION);

                Ruta ruta = aerolinea.getRuta(codRuta);
                if (ruta == null) {
                    throw new InformacionInconsistenteException("Ruta inexistente para vuelo: " + codRuta);
                }

                Avion avion = buscarAvionPorNombre(aerolinea, nombreAvion);
                if (avion == null) {
                    throw new InformacionInconsistenteException("Avión inexistente para vuelo: " + nombreAvion);
                }

                // Tu constructor de Vuelo es (String fecha, Avion avion, Ruta ruta)
                Vuelo vuelo = new Vuelo(fecha, avion, ruta);
                aerolinea.getVuelos().add(vuelo);
                i = i + 1;
            }
        }
    }

    @Override
    public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException {
        JSONObject raiz = new JSONObject();

        
        JSONArray jAviones = new JSONArray();
        for (Avion a : aerolinea.getAviones()) {
            if (a != null) {
                JSONObject j = new JSONObject();
                j.put(NOMBRE, a.getNombre());
                j.put(CAPACIDAD, a.getCapacidad());
                jAviones.put(j);
            }
        }
        raiz.put(AVIONES, jAviones);

        
        JSONArray jRutas = new JSONArray();
        for (Ruta r : aerolinea.getRutas()) {
            if (r != null) {
                JSONObject j = new JSONObject();
                j.put(CODIGO_RUTA, r.getCodigoRuta());
                j.put(ORIGEN, r.getOrigen().getCodigo());
                j.put(DESTINO, r.getDestino().getCodigo());
                j.put(HORA_SALIDA, r.getHoraSalida());
                j.put(HORA_LLEGADA, r.getHoraLlegada());
                jRutas.put(j);
            }
        }
        raiz.put(RUTAS, jRutas);

        
        Set<String> yaEscritos = new HashSet<>();
        JSONArray jAeropuertos = new JSONArray();
        for (Ruta r : aerolinea.getRutas()) {
            if (r != null) {
                Aeropuerto[] ambos = new Aeropuerto[] { r.getOrigen(), r.getDestino() };
                int idx = 0;
                while (idx < ambos.length) {
                    Aeropuerto ap = ambos[idx];
                    if (ap != null && !yaEscritos.contains(ap.getCodigo())) {
                        JSONObject j = new JSONObject();
                        j.put(NOMBRE, ap.getNombre());
                        j.put(CODIGO, ap.getCodigo());
                        j.put(NOMBRE_CIUDAD, ap.getNombreCiudad());
                        j.put(LATITUD, ap.getLatitud());
                        j.put(LONGITUD, ap.getLongitud());
                        jAeropuertos.put(j);
                        yaEscritos.add(ap.getCodigo());
                    }
                    idx = idx + 1;
                }
            }
        }
        raiz.put(AEROPUERTOS, jAeropuertos);

        
        JSONArray jVuelos = new JSONArray();
        for (Vuelo v : aerolinea.getVuelos()) {
            if (v != null) {
                JSONObject j = new JSONObject();
                j.put(FECHA, v.getFecha());
                j.put(CODIGO_RUTA, v.getRuta().getCodigoRuta());
                j.put(NOMBRE_AVION, v.getAvion().getNombre());
                jVuelos.put(j);
            }
        }
        raiz.put(VUELOS, jVuelos);

        
        try (PrintWriter pw = new PrintWriter(archivo)) {
            raiz.write(pw, 2, 0);
        }
    }

    

    private Avion buscarAvionPorNombre(Aerolinea aerolinea, String nombre) {
        for (Avion a : aerolinea.getAviones()) {
            if (a != null && nombre.equals(a.getNombre())) {
                return a;
            }
        }
        return null;
    }
}
