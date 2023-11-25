package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Pedido;
import modelo.Producto;
import modelo.ProductoMenu;
import modelo.ValorTotalPedidoExcedidoException;

class PedidoTest {

	private Pedido p;

    @BeforeEach
    public void setUp() {
        p = new Pedido("Cliente", "Dirección");
    }

    @Test
    public void testGenerarTextoFactura_SinProductos() {
        String facturaEsperada = "Factura\n\nPedido 0\nNombre: Cliente\nDirección: Dirección\n\n\nPrecio Neto: $0\nIVA: $0\nPrecio Total: $0";
        assertEquals(facturaEsperada, p.generarTextoFactura());
    }

    @Test
    public void testGenerarTextoFactura_ConProductos() throws ValorTotalPedidoExcedidoException {
        ProductoMenu producto1 = new ProductoMenu("Hamburguesa", 15000);
        ProductoMenu producto2 = new ProductoMenu("Refresco", 5000);
        
		p.agregarProducto(producto1);
		
			//Lo manda a la funcion de abajo para saber si si se cumplio la excepcion.
		
      
		p.agregarProducto(producto2);
		

        String facturaEsperada = "Factura\n\nPedido 0\nNombre: Cliente\nDirección: Dirección\n" +
                producto1.generarTextoFactura() + producto2.generarTextoFactura() +
                "\n\nPrecio Neto: $20000\nIVA: $3800\nPrecio Total: $23800";
        assertEquals(facturaEsperada, p.generarTextoFactura());
    }
    
  //(expected = ValorTotalPedidoExcedidoException.class)

    @Test
    public void testAgregarProducto_ValorTotalExcedeLimite() {
        try {
            // Agregar productos al pedido que excedan el límite de 150.000 pesos
            ProductoMenu producto1 = new ProductoMenu("Producto 1", 100000);
            ProductoMenu producto2 = new ProductoMenu("Producto 2", 60000);
            p.agregarProducto(producto1);
            p.agregarProducto(producto2);

            // Si llegamos a este punto y no se lanzó ninguna excepción,
            // entonces la prueba debería fallar porque se esperaba la excepción.
            fail("Se esperaba ValorTotalPedidoExcedidoException");
        } catch (ValorTotalPedidoExcedidoException e) {
            // Verifica si la excepción capturada es del tipo ValorTotalPedidoExcedidoException
            assertNotNull(e);
            // También se podria haber hecho con AssertThrows pero decidi que no era necesario.
        }
    }
    
    @Test
    public void testGetIdPedido() {
        assertEquals(Pedido.numeroPedidos, p.getIdPedido());
    }

    @Test
    public void testGetItemsPedido() throws ValorTotalPedidoExcedidoException {
        assertTrue(p.getItemsPedido().isEmpty());
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 15000);
        p.agregarProducto(producto);
        List<Producto> items = p.getItemsPedido();
        assertFalse(items.isEmpty());
        assertEquals(producto, items.get(0));
    }


    @Test
    public void testIncrementarNumeroPedidos() {
        int numeroInicial = Pedido.numeroPedidos;
        Pedido.incrementarNumeroPedidos();
        assertEquals(numeroInicial + 1, Pedido.numeroPedidos);
    }

    @Test
    public void testGuardarFactura() throws IOException {
        File archivo = new File("factura.txt");
        try {
            p.guardarFactura(archivo);
            assertTrue(archivo.exists());
        } finally {
            archivo.delete(); // Elimina el archivo después de la prueba
        }
    }
    
    @Test
    public void testEquals() throws ValorTotalPedidoExcedidoException {
        Pedido otroPedido = new Pedido("Cliente", "Dirección");
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 15000);
        p.agregarProducto(producto);
        assertEquals(p.equals(otroPedido), false);
    }
	    
	    
	    

}
