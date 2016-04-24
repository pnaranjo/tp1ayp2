package clases;

public abstract class CuentaComun extends Cuenta {
	private final long cbu;
    
	public CuentaComun(double saldo) {
	   super(saldo);
	   this.cbu = Banco.generadorCbu++;
	   // TODO Auto-generated constructor stub
	}  
        
    public long getCbu(){
       return this.cbu;
    }
    
    /*
     * El método debitar() debe ser abstracto ya que, en una cuenta corriente 
     * se permite debitar hasta el monto de sobregiro y en Caja de Ahorro no.
     * 
     */
    protected abstract Transaccion debitar( String tipoDeMovimiento, double monto, String motivo);

    
    public boolean tieneComoCliente(long cuit){
    	if(Banco.portfolioDeClientes.containsKey(cuit)){
    		return true;    	
    	}
    	return false;
    }
    
    //TODO cargar tipo de moneda en las clases hijas
    public String getTipoMoneda(){
    	return "";
    }
       
}