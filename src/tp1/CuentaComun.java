package tp1;

public abstract class CuentaComun extends Cuenta {
	private final long cbu;
    
	public CuentaComun(double saldo) {
	   super(saldo);
	   this.cbu = OperadorBancario.generadorCbu++;
	   // TODO Auto-generated constructor stub
	}  
        
    public long getCbu(){
       return this.cbu;
    }
    
    public void debitar(double monto){
    	//TODO hacer metodo
    }
    
    public boolean tieneComoCliente(long cuit){
    	if(OperadorBancario.portfolioDeClientes.containsKey(cuit)){
    		return true;    	
    	}
    	return false;
    }
    
    //TODO cargar tipo de moneda en las clases hijas
    public String getTipoMoneda(){
    	return "";
    }
       
}