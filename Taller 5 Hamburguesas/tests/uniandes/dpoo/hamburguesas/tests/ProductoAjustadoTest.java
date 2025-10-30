package uniandes.dpoo.hamburguesas.tests;

import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductoAjustadoTest {
	
	private ProductoMenu nuevoProductoBase;
	private ProductoAjustado nuevoProductoAjustado;
	
	@BeforeEach
	void setUp() {
		
		
		//Ingrediente ingrediente1A = new Ingrediente( "cebolla caramelizada", 2000 );
		//Ingrediente ingrediente2A = new Ingrediente( "suero costeno", 1000 );
		//Ingrediente ingrediente3E = new Ingrediente( "pepinillos", 0 );
		//Ingrediente ingrediente4E = new Ingrediente( "tomate", 0 );
		
		
		nuevoProductoBase = new ProductoMenu("Hamburguesa grande",19000);
		nuevoProductoAjustado = new ProductoAjustado(nuevoProductoBase);
	}
	@AfterEach
    void tearDown( ) throws Exception
    {
    }
	
	@Test
	void testGetNombre() {
		assertEquals("Hamburguesa grande",nuevoProductoAjustado.getNombre(),"El nombre no es el correcto");
		System.out.println(nuevoProductoBase);
	}
	
	@Test
	void testGetPrecio() {
		
		int precioBase =  19000;
		int precioIngredientes = 3000;
		
		
		int precioFinal = precioBase + precioIngredientes;
		
		assertEquals(precioFinal,nuevoProductoAjustado.getPrecio(),"El precio no es el correcto");
	}
	
	//No hay un metodo en ProductoAjustable que me permita obtener 
	// o modificar la lista de ingredientes agregados y eliminados
	@Test
	void testGenerarTextoFactura() {
		
		String factura = nuevoProductoAjustado.generarTextoFactura();
		
		String mensaje = "Hamburguesa grande,19000    +cebolla caramelizada                2000"
				+ "    +suero costeno                1000    "
				+ "-pepinillos    -tomate            22000\n";
		
		assertEquals(mensaje,factura,"La factura no esta bien redactada");
		
	}
	

}
