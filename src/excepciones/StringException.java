package excepciones;

public class StringException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7722415263022280857L;
	public StringException(String s){
		super(s);
	}
	public StringException(){
		super("El valor ingresado debe ser una cadena de caracteres. ");
	}
	
}
