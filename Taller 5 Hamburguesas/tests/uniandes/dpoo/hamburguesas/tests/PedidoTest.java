package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Path;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {

    private Pedido pedido;
    private ProductoMenu hamburguesa; 
    private ProductoMenu papas;       

    @BeforeEach
    void setUp() {
        pedido = new Pedido("Juan Perez", "Calle 123 #45-67");
        hamburguesa = new ProductoMenu("Hamburguesa Sencilla", 15000);
        papas       = new ProductoMenu("Papas Medianas", 5000);
    }

    @AfterEach
    void tearDown() { }

    
    @Test
    void testGetIdPedido() {
        Pedido p1 = new Pedido("A", "X");
        Pedido p2 = new Pedido("B", "Y");
        assertEquals(p1.getIdPedido() + 1, p2.getIdPedido(),
                "El id del segundo pedido debería ser consecutivo al primero");
    }

    
    @Test
    void testGetNombreCliente() {
        assertEquals("Juan Perez", pedido.getNombreCliente(),
                "El nombre del cliente no es el esperado");
    }

    
    @Test
    void testAgregarProducto() {
        
        assertEquals(0, pedido.getPrecioTotalPedido(), "El total debería iniciar en 0");

        
        pedido.agregarProducto(hamburguesa); 
        assertEquals(17850, pedido.getPrecioTotalPedido(),
                "El total no refleja el producto agregado con su IVA");
    }

    
    @Test
    void testGetPrecioTotalPedido() {
        pedido.agregarProducto(hamburguesa); 
        pedido.agregarProducto(papas);       

        int neto = 15000 + 5000;          
        int iva  = (int) (neto * 0.19);   
        int totalEsperado = neto + iva;    

        assertEquals(totalEsperado, pedido.getPrecioTotalPedido(),
                "El total (neto + IVA) no es el esperado");
    }

    
    @Test
    void testGenerarTextoFactura() {
        pedido.agregarProducto(hamburguesa);
        pedido.agregarProducto(papas);

        
        String esperado =
            "Cliente: Juan Perez\n" +
            "Dirección: Calle 123 #45-67\n" +
            "----------------\n" +
            "Hamburguesa Sencilla\n" +
            "            15000\n" +
            "Papas Medianas\n" +
            "            5000\n" +
            "----------------\n" +
            "Precio Neto:  20000\n" +
            "IVA:          3800\n" +
            "Precio Total: 23800\n";

        assertEquals(esperado, pedido.generarTextoFactura(),
                "La factura no coincide con el formato/valores esperados");
    }

    
    @Test
    void testGuardarFactura(@TempDir Path tempDir) throws Exception {
        pedido.agregarProducto(hamburguesa);
        String contenidoEsperado = pedido.generarTextoFactura();

        File destino = tempDir.resolve("factura.txt").toFile();
        pedido.guardarFactura(destino);

        String contenidoLeido = java.nio.file.Files.readString(destino.toPath());
        assertEquals(contenidoEsperado, contenidoLeido,
                "El archivo no contiene exactamente la factura generada");
    }

}

