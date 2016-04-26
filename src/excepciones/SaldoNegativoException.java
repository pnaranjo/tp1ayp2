package excepciones;

public class SaldoNegativoException extends Exception{
	public SaldoNegativoException(String s){
		super(s);
	}
	
	public SaldoNegativoException(){
		super("El saldo de la Caja de Ahorro no puede ser negativo");
	}

}
