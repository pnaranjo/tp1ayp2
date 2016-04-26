package clases;

import java.util.ArrayList;

public abstract class Cuenta  {

    protected double saldo;
    protected ArrayList<Transaccion> historial;   
    protected boolean habilitada;
    protected String tipoDeCuenta; 
    // rc protected String tipoDeMoneda;
    
    

    public Cuenta(double saldo) {
		
		this.saldo = saldo;
		this.historial = new ArrayList<Transaccion>();
	}

	public double getSaldo() {
        return saldo;
    }

    public ArrayList<Transaccion> getHistorial() {
        return historial;
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
    //RC: Para mi esto no es necesario aca
    //TODO extederlo al resto de las clases hijas
/*    public String getTipoCuenta(){
    	return tipoCuenta;
    }
    */  
}