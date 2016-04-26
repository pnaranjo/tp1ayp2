package clases;

public abstract class CuentaComun extends Cuenta {
	private final long cbu;
	protected Cliente[] titulares;
	protected String tipoDeMoneda;//rc
	protected String tipoCuenta;
	public CuentaComun(double saldo) {
	   super(saldo);
	   this.cbu = Banco.generadorCbu++;
	   // TODO Auto-generated constructor stub
	}  
        
    public long getCbu(){
       return cbu;
    }
  //TODO cargar tipo de moneda en las clases hijas
    public String getTipoMoneda(){
    	return tipoDeMoneda;
    } 	
    //metodo temporal, debe ser de tipo transaccion, con las respectivas validaciones.
    public void debitar(double monto){
    	saldo -= monto;
    }
    //metodo temporal, debe ser de tipo transaccion, con las respectivas validaciones.
    public void acreditar(double monto){
    	saldo += monto;
    }
    public boolean tieneComoCliente(long cuit){
    	if(Banco.portfolioDeClientes.containsKey(cuit)){
    		return true;    	
    	}
    	return false;
    }
    public String getTipoCuenta(){
    	return tipoCuenta;
    }
}