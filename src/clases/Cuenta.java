package clases;

import java.util.ArrayList;

public abstract class Cuenta  {

    protected double saldo;
    protected ArrayList<Transaccion> historial;  
    protected String tipoDeCuenta;
    

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
      public String getTipoCuenta(){
    	return tipoDeCuenta;
    }
      
}