package excepciones;

public class DebitarException extends Exception {
	public DebitarException(String s){
		super(s);
	}
	public DebitarException(){
		super("La operaci�n debitar no se ha podido realizar");
	}
}
