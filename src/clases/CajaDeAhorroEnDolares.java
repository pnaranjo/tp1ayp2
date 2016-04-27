package clases;

import java.util.ArrayList;

import excepciones.ArrayTitularesException;
import excepciones.MontoException;
import excepciones.SaldoNegativoException;

public class CajaDeAhorroEnDolares extends CajaDeAhorro{

	public double costoMantenimiento;
	
	public CajaDeAhorroEnDolares(double saldo, ArrayList<PersonaFisica> titulares,double tasaDeInteres) throws MontoException, ArrayTitularesException {
		super(saldo, titulares, tasaDeInteres);
		//costoMantenimiento = Banco.getCostoDeMantenimientoPesos() * Banco.getTipoDeCambioVigente();		
		costoMantenimiento = Banco.getCostoDeMantenimientoDolares();
		setTipoCuenta("CajaDeAhorroEnDolares");
		setTipoMoneda("Dolares");
	}
	
	public void cobroDeMantenimiento() throws SaldoNegativoException{
		if (saldo < costoMantenimiento) throw new SaldoNegativoException();
		saldo =- costoMantenimiento;
	}

	public double getCostoMantenimiento(){
		return costoMantenimiento;
	}
	
}
