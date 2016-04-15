package tp1;

public class CuentaComun extends Cuenta {
	private final long cbu;
    public CuentaComun(double saldo) {
	   super(saldo);
	   this.cbu = OperadorBancario.generadorCbu++;
	   // TODO Auto-generated constructor stub
	}  
        
    public long getCbu(){
       return this.cbu;
    }
       
}
