package excepciones;

public class DebitarException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4017068920471868993L;
	public DebitarException(String s){
		super(s);
	}
	public DebitarException(){
		super("La operación debitar no se ha podido realizar");
	}
}
