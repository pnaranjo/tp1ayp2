package clases;

import java.util.ArrayList;

import excepciones.MontoException;
import excepciones.SaldoNegativoException;

public class CajaDeAhorroEnDolares extends CajaDeAhorro{

	public double costoMantenimiento;
	public CajaDeAhorroEnDolares(double saldo, ArrayList<PersonaFisica> titulares,double tasaDeInteres) throws MontoException {
		super(saldo, titulares, tasaDeInteres);
		super.tipoDeCuenta = "CajaDeAhorroEnDolares";
		super.tipoDeMoneda = "Dolares";
		costoMantenimiento = Banco.getCostoDeMantenimientoPesos() * Banco.getTipoDeCambioVigente();		
	}
	
	
	public void cobroDeMantenimiento() throws SaldoNegativoException{
		if (saldo < costoMantenimiento) throw new SaldoNegativoException();
		saldo =- costoMantenimiento;
	}
	
	public double getCostoMantenimiento(){
		return costoMantenimiento;
	}
	
	public String getTipoCuenta(){
		return super.tipoDeCuenta;
	}
	
	public String getTipoMoneda(){
		return super.tipoDeMoneda;
	}
	
	
}
