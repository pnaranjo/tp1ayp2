package clases;

import java.util.ArrayList;

import excepciones.ArrayTitularesException;
import excepciones.MontoException;
import excepciones.SaldoNegativoException;
import excepciones.TransaccionException;

public abstract class CajaDeAhorro extends Cuenta{
    private final ArrayList<PersonaFisica> titulares;
    private final double tasaDeInteres;
    public double costoMantenimiento;
	
    public CajaDeAhorro(double saldo, ArrayList<PersonaFisica> titulares, double tasaDeInteres) throws MontoException, ArrayTitularesException{
        super(saldo);
        if(titulares.isEmpty()){
      		 throw new ArrayTitularesException();
      	 }
        if (tasaDeInteres < 0) throw new MontoException("La tasa de interes no puede ser negativa");
    	this.tasaDeInteres = tasaDeInteres;
    	this.titulares = titulares;
    }
    public ArrayList<PersonaFisica> getTitulares(){
    	return this.titulares;
    }
    
    public double getTasaDeInteres(){
    	return this.tasaDeInteres;
    }
    public double getCostoMantenimiento(){
		return costoMantenimiento;
	}
	public void cobroDeMantenimiento() throws SaldoNegativoException{
		if (saldo < costoMantenimiento) throw new SaldoNegativoException();
		saldo -= costoMantenimiento;
	}
    public String debitar(double monto, String motivo,  String observaciones) throws TransaccionException, MontoException{
    
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
