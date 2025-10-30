package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante();
        
        File facturasDir = new File("./facturas");
        if (!facturasDir.exists()) {
            
            facturasDir.mkdirs();
        }
        
        File[] prev = facturasDir.listFiles();
        if (prev != null) {
            for (File f : prev) {
                
                if (f.isFile()) {
                    f.delete();
                }
            }
        }
    }

    @AfterEach
    void tearDown() throws Exception{
        
    }

    
    @Test
    void testEstadoInicial() {
        assertNull(restaurante.getPedidoEnCurso(), "Debe iniciar sin pedido en curso");
        assertEquals(0, restaurante.getPedidos().size(), "Histórico de pedidos debe iniciar vacío");
        assertEquals(0, restaurante.getMenuBase().size(), "Menú base debe iniciar vacío");
        assertEquals(0, restaurante.getMenuCombos().size(), "Menú combos debe iniciar vacío");
        assertEquals(0, restaurante.getIngredientes().size(), "Ingredientes debe iniciar vacío");
    }

    
    @Test
    void testIniciarPedido_ok() throws YaHayUnPedidoEnCursoException {
        restaurante.iniciarPedido("Ana", "Calle 1");
        Pedido p = restaurante.getPedidoEnCurso();
        assertNotNull(p, "Debe haber pedido en curso tras iniciarPedido");
        assertEquals("Ana", p.getNombreCliente(), "El nombre del cliente del pedido en curso no coincide");
    }

    @Test
    void testIniciarPedidoExcepcion() throws YaHayUnPedidoEnCursoException {
        restaurante.iniciarPedido("Ana", "Calle 1");
        assertThrows(YaHayUnPedidoEnCursoException.class,
                () -> restaurante.iniciarPedido("Beto", "Calle 2"),
                "Debe lanzar YaHayUnPedidoEnCursoException al iniciar un segundo pedido");
    }

    
    @Test
    void testCerrarYGuardarPedido() {
        assertThrows(NoHayPedidoEnCursoException.class,
                () -> restaurante.cerrarYGuardarPedido(),
                "Debe lanzar NoHayPedidoEnCursoException si no hay pedido en curso");
    }

    @Test
    void testCerrarYGuardarPedidocreaArchivo() throws Exception {
        restaurante.iniciarPedido("Carla", "Calle 3");
        Pedido enCurso = restaurante.getPedidoEnCurso();
        assertNotNull(enCurso, "Debe existir pedido en curso antes de cerrar");

        
        enCurso.agregarProducto(new ProductoMenu("Hamburguesa Sencilla", 15000));

        int id = enCurso.getIdPedido();
        String nombreArchivo = "factura_" + id + ".txt";
        File destino = new File("./facturas/" + nombreArchivo);

        restaurante.cerrarYGuardarPedido();

        
        assertNull(restaurante.getPedidoEnCurso(), "Después de cerrar, no debe haber pedido en curso");
        assertTrue(destino.exists(), "Debe existir el archivo de factura con el prefijo y el id correctos");
        assertTrue(destino.length() > 0, "El archivo de factura no debe estar vacío");

        
        assertEquals(1, restaurante.getPedidos().size(), "El pedido cerrado debe agregarse al histórico de pedidos");
    }

    
    @Test
    void testCargarInformacionRestaurante(@TempDir Path tmp) throws Exception {
        
        Path ing = tmp.resolve("ingredientes.txt");
        try (FileWriter w = new FileWriter(ing.toFile())) {
            w.write("Tomate;1000\n");
            w.write("Queso;1500\n");
        }

        
        Path menu = tmp.resolve("menu.txt");
        try (FileWriter w = new FileWriter(menu.toFile())) {
            w.write("Hamburguesa;18000\n");
            w.write("Papas;5000\n");
            w.write("Gaseosa;3000\n");
        }

        
        Path combos = tmp.resolve("combos.txt");
        try (FileWriter w = new FileWriter(combos.toFile())) {
            w.write("Combo1;10%;Hamburguesa;Papas;Gaseosa\n");
        }

        restaurante.cargarInformacionRestaurante(ing.toFile(), menu.toFile(), combos.toFile());

        assertEquals(2, restaurante.getIngredientes().size(), "Debe cargar 2 ingredientes");
        assertEquals(3, restaurante.getMenuBase().size(), "Debe cargar 3 productos del menú base");
        assertEquals(1, restaurante.getMenuCombos().size(), "Debe cargar 1 combo");
    }

    
    @Test
    void testCargarIngredientes(@TempDir Path tmp) throws Exception {
        Path ing = tmp.resolve("ingredientes.txt");
        try (FileWriter w = new FileWriter(ing.toFile())) {
            w.write("Tomate;1000\n");
            w.write("Tomate;1200\n");
        }

        Path menu = tmp.resolve("menu.txt");
        Files.writeString(menu, "Hamburguesa;18000\n");

        Path combos = tmp.resolve("combos.txt");
        Files.writeString(combos, "Combo1;10%;Hamburguesa\n");

        assertThrows(IngredienteRepetidoException.class,
                () -> restaurante.cargarInformacionRestaurante(ing.toFile(), menu.toFile(), combos.toFile()),
                "Debe lanzar IngredienteRepetidoException al cargar un ingrediente duplicado");
    }

    
    @Test
    void testCargarMenu(@TempDir Path tmp) throws Exception {
        Path ing = tmp.resolve("ingredientes.txt");
        Files.writeString(ing, "Tomate;1000\n");

        Path menu = tmp.resolve("menu.txt");
        try (FileWriter w = new FileWriter(menu.toFile())) {
            w.write("Hamburguesa;18000\n");
            w.write("Hamburguesa;20000\n"); 
        }

        Path combos = tmp.resolve("combos.txt");
        Files.writeString(combos, "Combo1;10%;Hamburguesa\n");

        assertThrows(ProductoRepetidoException.class,
                () -> restaurante.cargarInformacionRestaurante(ing.toFile(), menu.toFile(), combos.toFile()),
                "Debe lanzar ProductoRepetidoException al cargar un producto duplicado en el menú");
    }

    
    @Test
    void testCargarCombos_productoFaltante(@TempDir Path tmp) throws Exception {
        Path ing = tmp.resolve("ingredientes.txt");
        Files.writeString(ing, "Tomate;1000\n");

        Path menu = tmp.resolve("menu.txt");
        Files.writeString(menu, "Hamburguesa;18000\n");

        Path combos = tmp.resolve("combos.txt");
        
        Files.writeString(combos, "Combo1;10%;Hamburguesa;Papas\n");

        assertThrows(ProductoFaltanteException.class,
                () -> restaurante.cargarInformacionRestaurante(ing.toFile(), menu.toFile(), combos.toFile()),
                "Debe lanzar ProductoFaltanteException si un combo referencia un producto inexistente");
    }

    
    @Test
    void testCargarCombos_comboRepetido(@TempDir Path tmp) throws Exception {
        Path ing = tmp.resolve("ingredientes.txt");
        Files.writeString(ing, "Tomate;1000\n");

        Path menu = tmp.resolve("menu.txt");
        Files.writeString(menu, "Hamburguesa;18000\n");

        Path combos = tmp.resolve("combos.txt");
        try (FileWriter w = new FileWriter(combos.toFile())) {
            w.write("Combo1;10%;Hamburguesa\n");
            w.write("Combo1;15%;Hamburguesa\n"); 
        }

        assertThrows(ProductoRepetidoException.class,
                () -> restaurante.cargarInformacionRestaurante(ing.toFile(), menu.toFile(), combos.toFile()),
                "Debe lanzar ProductoRepetidoException si hay dos combos con el mismo nombre");
    }
}
