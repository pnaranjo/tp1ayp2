package clases;

import java.util.ArrayList;

import excepciones.ArrayTitularesException;
import excepciones.DebitarException;
import excepciones.MontoDepositoException;
import excepciones.MontoException;
import excepciones.TransaccionException;


public class CuentaCorriente extends Cuenta{
	private final ArrayList<Cliente> titulares = null;
	private double montoSobreGiro;
	private static double montoParaAbrirCuenta = 10000;
	static double comision = 0.03;

	public CuentaCorriente(double montoDeposito,ArrayList<Cliente> titulares,double montoSobreGiro)throws MontoDepositoException,MontoException,ArrayTitularesException {
		super(setMontoDeposito(montoDeposito));
		if(titulares.isEmpty()){
			throw new ArrayTitularesException();
			}
 	   	titulares = new ArrayList<Cliente>(titulares);                
		setMontoSobreGiro(montoSobreGiro);                 
		setTipoCuenta("CuentaCorriente");
		setTipoMoneda("Pesos");
	} 

	public double cobrarComision(double monto) throws MontoException{
		if (monto < 0) throw new MontoException("El depósito inicial debe ser mayor a 0");
		return comision * monto;
	}
	public ArrayList<Cliente> getTitulares() {
		return titulares;
	}
	public static double getMontoParaAbrirCuenta() {
		return montoParaAbrirCuenta;
	}
	public double setMontoParaAbrirCuenta(double monto) throws MontoException{
		if (monto < 0) throw new MontoException("El depósito inicial debe ser mayor a 0");
		return montoParaAbrirCuenta = monto;
	}
	public double getMontoSobreGiro() {
		return montoSobreGiro;
	}
	public void setMontoSobreGiro(double montoSobreGiro) throws MontoException {
		if(montoSobreGiro < 0){
			throw new MontoException("El monto de sobregiro debe ser cero o positivo.");
		}
		this.montoSobreGiro = montoSobreGiro;
	}
	public static double getComision() {
		return comision;
	}
	public static void setComision(double nuevaComision) throws MontoException {
		if (nuevaComision < 0) throw new MontoException("La nueva comision ingresada debe ser mayor a cero. ");
		comision = nuevaComision;
	}
	public static double setMontoDeposito(double montoDeposito) throws MontoDepositoException{
		if(montoDeposito <= getMontoParaAbrirCuenta()){
			throw new MontoDepositoException();
		}
		return montoDeposito;
	    }
	
	/*private void setTitulares(ArrayList<Cliente> titulares1) throws ArrayTitularesException{
 	   
    }*/
	public String acreditar(double monto, String motivo) throws TransaccionException, MontoException{		
		if(monto <= 0)
			throw new MontoException();
		saldo += (monto - cobrarComision(monto));
		transaccion = new Transaccion("acreditar", monto, motivo);
		historial.add(transaccion);
		Banco.acreditarRetenciones(this.getCbu(),cobrarComision(monto), motivo);
		return transaccion.toString();
	}
	public String acreditar(double monto, String motivo,String observacion) throws TransaccionException, MontoException{
		if(monto <= 0)
			throw new MontoException();
		saldo += (monto - cobrarComision(monto));
		transaccion = new Transaccion("acreditar", monto, motivo,observacion);
		historial.add(transaccion);
		Banco.acreditarRetenciones(this.getCbu(),cobrarComision(monto), motivo, observacion);
		return transaccion.toString();
	}
	public String debitar(double monto, String motivo) throws TransaccionException, MontoException, DebitarException{		
		if(monto <= 0)
			throw new MontoException();
		if(saldo - (monto + cobrarComision(monto)) < (-(montoSobreGiro))){
			throw new DebitarException("La operaci�n no se ha podido realizar, saldo insuficiente");
		}
		saldo -= (monto + cobrarComision(monto));
		transaccion = new Transaccion("debitar", monto, motivo);
		historial.add(transaccion);
		Banco.acreditarRetenciones(this.getCbu(),cobrarComision(monto), motivo);
		return transaccion.toString();
	}
	public String debitar(double monto, String motivo,String observacion) throws TransaccionException, MontoException, DebitarException{
		if(monto <= 0)
			throw new MontoException();
		if(saldo - (monto + cobrarComision(monto)) < (-(montoSobreGiro))){
			throw new DebitarException("La operaci�n no se ha podido realizar, saldo insuficiente");
		}
		saldo -= (monto + cobrarComision(monto));
		transaccion = new Transaccion("debitar", monto, motivo,observacion);
		historial.add(transaccion);
		Banco.acreditarRetenciones(this.getCbu(),cobrarComision(monto), motivo, observacion);
		return transaccion.toString();
	}
}
