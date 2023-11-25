package pruebas;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ ComboTest.class, ExcepcionesRepetidosTest.class, PedidoTest.class, ProductoAjustadoTest.class,
		ProductoMenuTest.class })
public class AllTests {

}
