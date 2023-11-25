package modelo;

public class ValorTotalPedidoExcedidoException extends Exception {
    private static final long serialVersionUID = 1L;
	private static final String MENSAJE = "¡El valor total del pedido excede los 150.000 pesos!";
    
    public ValorTotalPedidoExcedidoException() {
        super(MENSAJE);
    }
}
