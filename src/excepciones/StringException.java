package excepciones;

public class StringException extends Exception{
	public StringException(String s){
		super(s);
	}
	public StringException(){
		super("El valor ingresado debe ser una cadena de caracteres. ");
	}
	
}
