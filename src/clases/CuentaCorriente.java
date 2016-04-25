package clases;

import java.util.ArrayList;

import excepciones.ArrayTitularesException;
import excepciones.MontoDepositoException;
import excepciones.MontoException;


public class CuentaCorriente extends CuentaComun {
    private final ArrayList<Cliente> titulares;
    private double montoSobreGiro, montoParaAbrirCuenta = 10000;
    static double comision = 0.03;
    
    	public CuentaCorriente(double montoDeposito,ArrayList<Cliente> titulares,double montoSobreGiro)throws MontoDepositoException,MontoException,ArrayTitularesException {
    	 super(montoDeposito);
    	 if(montoDeposito < montoParaAbrirCuenta){
             throw new MontoDepositoException();
         }
    	 if(montoSobreGiro >= 0){
    		 throw new MontoException("El monto de sobregiro debe ser negativo");
    	 }
    	 if(titulares.isEmpty()){
    		 throw new ArrayTitularesException();
    	 }
         this.titulares = titulares;                 
         this.montoSobreGiro = montoSobreGiro;                 
         habilitada = true;
         tipoDeMoneda = "pesos";
    	 }
	
    public double cobrarComision(double monto){
        return comision * monto;
    }
    public ArrayList<Cliente> getTitulares() {
		return titulares;
	}
	public double getMontoParaAbrirCuenta() {
		return montoParaAbrirCuenta;
	}
    public double setMontoParaAbrirCuenta(double monto){
        return montoParaAbrirCuenta = monto;
    }
	public double getMontoSobreGiro() {
        return montoSobreGiro;
    }
	public void setMontoSobreGiro(double montoSobreGiro) {
		this.montoSobreGiro = montoSobreGiro;
	}
	public static double getComision() {
		return comision;
	}
	public static void setComision(double nuevaComision) {
		comision = nuevaComision;
	}
       
}
