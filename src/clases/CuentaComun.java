package clases;

import excepciones.DebitarException;
import excepciones.MontoException;
import excepciones.TransaccionException;

public abstract class CuentaComun extends Cuenta {
	private final long cbu;
	protected Cliente[] titulares;
	protected String tipoDeMoneda;	
	protected boolean habilitada;
	
	public CuentaComun(double saldo) {
	   super(saldo);
	   this.cbu = Banco.generadorCbu++;	   
	}  
        
    public long getCbu(){
       return cbu;
    }
    public boolean isEnabled() {
        return habilitada;
    }
    
    public void setEnable(){
        habilitada = true;
    }
    public void setDisable(){
        habilitada = false;
    }
  
    public String getTipoMoneda(){
    	return tipoDeMoneda;
    }
    
    public abstract String debitar(double monto,String motivo) throws TransaccionException, MontoException, DebitarException;
    public abstract String debitar(double monto,String motivo,String Observaciones) throws TransaccionException, MontoException, DebitarException;
    
    public abstract String acreditar(double monto,String motivo) throws TransaccionException, MontoException;
    public abstract String acreditar(double monto,String motivo,String Observaciones) throws TransaccionException, MontoException;
    public boolean tieneComoCliente(long cuit){
    	if(Banco.portfolioDeClientes.containsKey(cuit)){
    		return true;    	
    	}
    	return false;
    }
    
    protected double convertirPesosADolares(double montoEnPesos){
        return montoEnPesos*Banco.getTipoDeCambioVigente();   
     }
         
     protected double convertirDolaresAPesos(double montoEnDolares){
        return montoEnDolares/Banco.getTipoDeCambioVigente();
     }
    
}