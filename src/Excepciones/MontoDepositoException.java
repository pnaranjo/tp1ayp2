package Excepciones;

public class MontoDepositoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5562185014684280615L;

	public MontoDepositoException(String s) {
		super(s);
	}
	
	public MontoDepositoException() {
		super("Para abrir la cuenta, el deposito ingresado debe ser mayor a $10.000");
	}

}
