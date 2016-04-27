package clases;

import java.util.ArrayList;

import excepciones.MontoException;
import excepciones.SaldoNegativoException;
import excepciones.TransaccionException;

public abstract class CajaDeAhorro extends CuentaComun {
    private final ArrayList<PersonaFisica> titulares;
    private final double tasaDeInteres;
    private Transaccion transaccion;
	
    public CajaDeAhorro(double saldo, ArrayList<PersonaFisica> titulares, double tasaDeInteres) throws MontoException{
        super(saldo);
        if (saldo <= 0) throw new MontoException("El depósito inicial debe ser mayor a 0");
    	this.tasaDeInteres = tasaDeInteres;
    	this.titulares = titulares;
    }
    public ArrayList<PersonaFisica> getTitulares(){
    	return this.titulares;
    }
    
    public double getTasaDeInteres(){
    	return this.tasaDeInteres;
    }
    abstract  protected void cobroDeMantenimiento() throws SaldoNegativoException;
    
    public  String debitar(double monto, String motivo,  String observaciones) throws TransaccionException, MontoException{
    	if(monto <= 0)
			throw new MontoException();
    	if(monto > saldo)
    		throw new MontoException("No dispone de saldo suficiente para realizar la operaci�n");
    	this.saldo =- monto;
    	transaccion = new Transaccion("debitar", monto, motivo, observaciones);
    	this.historial.add(transaccion);
		return transaccion.toString(); 
    }
    public String debitar(double monto, String motivo) throws TransaccionException, MontoException{
    	if(monto <= 0)
			throw new MontoException();
    	if(monto > saldo)
    		throw new MontoException("No dispone de saldo suficiente para realizar la operaci�n");
    	this.saldo =- monto;
    	transaccion = new Transaccion("debitar", monto, motivo);
    	this.historial.add(transaccion);
		return transaccion.toString(); 
    }
    
    
    public String acreditar(double monto, String motivo) throws TransaccionException, MontoException{		
		if(monto <= 0)
			throw new MontoException();
		saldo += monto;
		transaccion = new Transaccion("acreditar", monto, motivo);
		historial.add(transaccion);
		return transaccion.toString();
	}
    public String acreditar(double monto, String motivo,String observaciones) throws TransaccionException, MontoException{		
		if(monto <= 0)
			throw new MontoException();
		saldo += monto;
		transaccion = new Transaccion("acreditar", monto, motivo,observaciones);
		historial.add(transaccion);
		return transaccion.toString();
	}
}
