package excepciones;

public class TransaccionException extends Exception {
	
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
