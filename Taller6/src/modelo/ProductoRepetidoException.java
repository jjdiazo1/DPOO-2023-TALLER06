package modelo;

public class ProductoRepetidoException extends HamburguesaException {
    private static final long serialVersionUID = 1L;
	private static final String MENSAJE = "\"IMPORTANTE: Hay un error con el archivo de datos, contacte con su ingeniero de sistemas.\n ¡El ingrediente repetido encontrado fue!";
    
    public ProductoRepetidoException(String name) {
        super(MENSAJE + " " + name + "!");
    }
}
