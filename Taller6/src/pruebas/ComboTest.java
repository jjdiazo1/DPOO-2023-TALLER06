package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Combo;
import modelo.ProductoMenu;

class ComboTest {

	private Combo c;

    @BeforeEach
    public void setUp() {
       
        c = new Combo("Combo Hamburguesa", 5.0);
    }

    @Test
    public void testGetNombre() {
        assertEquals("Combo Hamburguesa" + "_c_mod", c.getNombre());
    }

    @Test
    public void testAgregarItemsACombo() {
    	c.agregarItemACombo(new ProductoMenu("Hamburguesa", 20000));
    	c.agregarItemACombo(new ProductoMenu("Palomitas", 5000));
    }
    
    @Test
    public void testGetPrecioSinNada() {
    	
    	int pTotal = 0;
		
		for (ProductoMenu p : c.getItemsCombo()) {
			pTotal += p.getPrecio();
		}
		
		pTotal *= 1 - (5.0*2 / 100);
    
        assertEquals(pTotal, c.getPrecio());
    }
    
    @Test
    public void testGetPrecio() {
    	
    	c.agregarItemACombo(new ProductoMenu("Hamburguesa", 20000));
    	c.agregarItemACombo(new ProductoMenu("Palomitas", 5000));
    	
    	int pTotal = 0;
		
		for (ProductoMenu p : c.getItemsCombo()) {
			pTotal += p.getPrecio();
		}
		
		pTotal *= 1 - (5.0*2 / 100);
    
        assertEquals(pTotal, c.getPrecio());
    }
    
    @Test
    public void testGenerarTextoFactura_SinProductos() {
        String facturaEsperada = "Factura generada: Combo Hamburguesa_c (0 items)";
        assertEquals(facturaEsperada, c.generarTextoFactura());
    }

    @Test
    public void testGenerarTextoFactura_ConProductos() {
        ProductoMenu p1 = new ProductoMenu("Hamburguesa", 15000);
        ProductoMenu p2 = new ProductoMenu("Refresco", 5000);

        c.agregarItemACombo(p1);
        c.agregarItemACombo(p2);

        String facturaEsperada = "Factura generada: Combo Hamburguesa_c (2 items)";
        assertEquals(facturaEsperada, c.generarTextoFactura());
    }

}
