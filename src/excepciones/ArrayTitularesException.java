package excepciones;

public class ArrayTitularesException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2546609050082506745L;

	public ArrayTitularesException(String s) {
		super(s);
	}
	
	public ArrayTitularesException() {
		super("Debes ingresar al menos un titular a la cuenta.");
	}

}
