package uniandes.dpoo.hamburguesas.tests;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductoMenuTest {
	
	private ProductoMenu nuevoProductoMenu;
	
	
	@BeforeEach
	void setUp() {
		
		nuevoProductoMenu = new ProductoMenu("Papas fritas medianas",4500);
	}
	@AfterEach
    void tearDown( ) throws Exception
    {
    }
	
	
	@Test
	void testGetPrecio() {
		assertEquals(4500,nuevoProductoMenu.getPrecio(),"El precio no es el correcto");
	}
	
	@Test
	void testGetNombre() {
		assertEquals("Papas fritas medianas",nuevoProductoMenu.getNombre(),"El nombre no es el correcto");
	}
	
	@Test
	void testGenerarTextoFactura() {
		
		String factura = nuevoProductoMenu.generarTextoFactura();
		
		
		
		String mensaje = "Papas fritas medianas\n            4500\n";

        
        
        assertEquals(mensaje,factura,"El texto de la factura no es el mismo");
	}

}
