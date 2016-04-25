package clases;

import java.util.ArrayList;

public abstract class Cuenta  {

    //private final long cbu = 0;
    //estan bien los protected???
    protected double saldo;
    protected ArrayList<Transaccion> historial;   
    protected boolean enabled;
    protected String tipoCuenta;
    
    public Cuenta(double saldo){
    	
    	this.saldo = saldo;
    	this.enabled = false;
    	historial = new ArrayList<Transaccion>();
    	this.tipoCuenta = "Cuenta";  //TODO extederlo al resto de las clases hijas
    }

    public double getSaldo() {
        return saldo;
    }

    public ArrayList<Transaccion> getHistorial() {
        return historial;
    }

    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnable(){
        enabled = true;
    }
    public void setDisable(){
        enabled = false;
    }
    
    public void acreditar(double monto){
    	
    	//TODO acreditar debería chequear que el monto sea mayor que 0
    	
    	//TODO acreditar debería incrementar el saldo
    	
    	//TODO acreditar debería generar una Transaccion y agregarla al historial
        
    }
    
    //TODO extederlo al resto de las clases hijas
    public String getTipoCuenta(){
    	return tipoCuenta;
    }
      
}