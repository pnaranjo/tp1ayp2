package excepciones;

public class MontoException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7560070015649853521L;

	public MontoException(String s) {
		super(s);
	}
	
	public MontoException() {
		super("El monto ingresado debe ser mayor a cero");
	}
}
