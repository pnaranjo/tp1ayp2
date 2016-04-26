package excepciones;

public class TransaccionException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8094502521810371745L;

	public TransaccionException(String s){
		super(s);
	}
	public TransaccionException(){
		super();
	}
	
	public String tipoMovimientoException(){
			return "El tipo de movimiento realizado no es correcto";
	}
}
