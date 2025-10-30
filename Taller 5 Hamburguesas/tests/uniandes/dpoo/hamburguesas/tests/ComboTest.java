package uniandes.dpoo.hamburguesas.tests;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;




public class ComboTest {
	
	private Combo nuevoCombo;
	private ArrayList<ProductoMenu> itemsCombo;
	
	
	
	@BeforeEach
	void setUp() {
		
		ProductoMenu producto1 = new ProductoMenu("Papas fritas medianas",4500);
		ProductoMenu producto2 = new ProductoMenu("Hamburguesa Grande",19000);
		ProductoMenu producto3 = new ProductoMenu("Gaseosa mediana",5000);
		double descuento = 0.15;
		
		itemsCombo = new ArrayList<ProductoMenu>();
		itemsCombo.add(producto1);
		itemsCombo.add(producto2);
		itemsCombo.add(producto3);
		
		nuevoCombo = new Combo("La super Big Mac",descuento,itemsCombo);
		
	}
	@AfterEach
	void tearDown() {
		
	}
	
	@Test
	void testGetNombre() {
		assertEquals("La super Big Mac",nuevoCombo.getNombre(),"El nombre no es el correcto");
	}
	
	@Test
	void testGetPrecio() {
		
		int suma = 4500+19000+5000;
		double precio = suma * (1 - 0.15);
		
		assertEquals(precio,nuevoCombo.getPrecio(),"El precio no es el correcto");
	}
	
	@Test
	void testGenerarTextoFactura() {
		
		String factura = nuevoCombo.generarTextoFactura();
		
		String mensaje = "Combo La super Big Mac\n Descuento: 0.15\n            24225.0\n";
		
		assertEquals(mensaje,factura,"La factura no esta bien redactada");
	}
	

}
