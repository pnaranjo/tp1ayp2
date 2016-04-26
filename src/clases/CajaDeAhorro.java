package clases;

import clases.Banco;

import java.util.ArrayList;

import excepciones.MontoException;
import excepciones.TransaccionException;


public abstract class CajaDeAhorro extends CuentaComun {
    private final ArrayList<PersonaFisica> titulares;
    private final double tasaDeInteres;
	
    public CajaDeAhorro(double saldo, ArrayList<PersonaFisica> titulares, double tasaDeInteres) {
        super(saldo);
    	this.tasaDeInteres = tasaDeInteres;
    	this.titulares = titulares;
    }
        
    protected double convertirPesosADolares(double montoEnPesos){
       return montoEnPesos*Banco.getTipoDeCambioVigente();   
    }
        
    protected double convertirDolaresAPesos(double montoEnDolares){
       return montoEnDolares/Banco.getTipoDeCambioVigente();
    }

    public ArrayList<PersonaFisica> getTitulares(){
    	return this.titulares;
    }
    
    public double getTasaDeInteres(){
    	return this.tasaDeInteres;
    }
    
    protected Transaccion debitar( String tipoDeMovimiento, double monto, String motivo,  String observaciones) throws TransaccionException, MontoException{
    	//TODO:
    	/*falta verificar que el saldo no quede negativo*/
    	this.saldo =- monto;
    	Transaccion transaccion = new Transaccion(tipoDeMovimiento, monto, motivo, observaciones);
    	this.historial.add(transaccion);
		return transaccion; 
    }
    protected Transaccion debitar( String tipoDeMovimiento, double monto, String motivo) throws TransaccionException, MontoException{
    	//TODO:
    	/*falta verificar que el saldo no quede negativo*/
    	this.saldo =- monto;
    	Transaccion transaccion = new Transaccion(tipoDeMovimiento, monto, motivo);
    	this.historial.add(transaccion);
		return transaccion; 
    }
    
    abstract protected void cobroDeMantenimiento();
    
}
