package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Ingrediente;
import modelo.ProductoAjustado;
import modelo.ProductoMenu;

class ProductoAjustadoTest {

	private ProductoMenu pm;
	private ProductoAjustado pa;
	private List<Ingrediente> lista1;
	private List<Ingrediente> lista2;

    @BeforeEach
    public void setUp() {
    	
    	this.lista1 = new ArrayList<>(); 
        this.lista2 = new ArrayList<>(); 

    	this.lista1.add(new Ingrediente("Pollo", 2500));
    	this.lista2.add(new Ingrediente("Carne", 5000));
    	this.lista2.add(new Ingrediente("Queso", 5000));
    	
    	this.pm = new ProductoMenu("Hamburguesa", 15000);
    	
    	this.pa = new ProductoAjustado(pm, lista1, lista2);
    }

    @Test
    public void testGetNombre() {
        assertEquals("Hamburguesa", pa.getNombre());
    }

    @Test
    public void testGetPrecioNuevo() {
        assertEquals(20000, pa.getPrecio()); 
    }
    
    @Test
    public void testGenerarTextoFactura_ConAgregadosYEliminados() {
        String expectedText = "\n- Hamburguesa\nCon:\n\tPollo_mod\nSin:\n\tCarne_mod\n\tQueso_mod";
        assertEquals(expectedText, pa.generarTextoFactura());
    }

    @Test
    public void testGenerarTextoFactura_SinAgregadosYEliminados() {
        ProductoAjustado paVacio = new ProductoAjustado(pm, new ArrayList<>(), new ArrayList<>());
        String expectedText = "\n- Hamburguesa"; // Se espera solo el nombre del producto
        assertEquals(expectedText, paVacio.generarTextoFactura());
    }

}
