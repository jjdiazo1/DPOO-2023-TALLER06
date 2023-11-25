package pruebas;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import modelo.IngredienteRepetidoException;
import modelo.ProductoRepetidoException;
import procesamiento.LoaderRestaurante;

class ExcepcionesRepetidosTest {

	
	private static final String PRODUCTFILE = "data/productosExcepcion.txt";
    private static final String INGFILE = "data/ingredientesExcepcion.txt";
    
    @Test
    public void testIngredienteRepetidoException() {
        assertThrows(IngredienteRepetidoException.class, () -> {
            LoaderRestaurante.cargarIngredientes(INGFILE);
        });
    }
    
    @Test
    public void testProductoRepetidoException() {
        assertThrows(ProductoRepetidoException.class, () -> {
            LoaderRestaurante.cargarMenu(PRODUCTFILE);
        });
    }

    


	

	
}



