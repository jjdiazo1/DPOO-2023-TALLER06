package pruebas;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.ProductoMenu;

class ProductoMenuTest {

	private ProductoMenu producto;

    @BeforeEach
    public void setUp() {
        producto = new ProductoMenu("Hamburguesa todoterreno", 45000);
    }

    @Test
    public void testGetNombre() {
        assertEquals("Hamburguesa todoterreno", producto.getNombre());
    }

    @Test
    public void testGetPrecio() {
        assertEquals(45000, producto.getPrecio());
    }

}
