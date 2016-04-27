package clases;

import java.util.ArrayList;

import excepciones.DebitarException;
import excepciones.MontoException;
import excepciones.TransaccionException;

public abstract class Cuenta  {
	
    protected double saldo;
    protected ArrayList<Transaccion> historial;  
    protected String tipoDeCuenta;
    private final long cbu;	
	private String tipoDeMoneda;	
	protected boolean habilitada;
	protected Transaccion transaccion;
	

    public Cuenta(double saldo) throws MontoException {	
    	if (saldo <= 0) throw new MontoException("El depósito inicial debe ser mayor a 0");
    	this.saldo = saldo;
		this.historial = new ArrayList<Transaccion>();
		this.cbu = Banco.generarNuevoCbu();
    }
	public double getSaldo() {
        return saldo;
    }
    public ArrayList<Transaccion> getHistorial() {
        return historial;
    }
      public String getTipoCuenta(){
    	return tipoDeCuenta;
    }
      public void setTipoCuenta(String tipoDeCuenta){
    	  this.tipoDeCuenta = tipoDeCuenta;
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
       public void setTipoMoneda(String tipoDeMoneda){
          	this.tipoDeMoneda = tipoDeMoneda;
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