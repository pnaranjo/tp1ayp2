package excepciones;

public class SaldoNegativoException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5187096438124880514L;

	public SaldoNegativoException(String s){
		super(s);
	}
	
	public SaldoNegativoException(){
		super("El saldo de la Caja de Ahorro no puede ser negativo");
	}

}
