package clases;

import java.util.ArrayList;

import excepciones.ArrayTitularesException;
import excepciones.MontoException;
import excepciones.SaldoNegativoException;

public class CajaDeAhorroEnPesos extends CajaDeAhorro {
	
	public double costoMantenimiento;
	public String tipoMoneda;
	
	
	public CajaDeAhorroEnPesos(double saldo, ArrayList<PersonaFisica> titulares,double tasaDeInteres) throws MontoException, ArrayTitularesException{
		super(saldo, titulares, tasaDeInteres);
		costoMantenimiento = Banco.getCostoDeMantenimientoPesos();		
		costoMantenimiento = Banco.getCostoDeMantenimientoPesos();
		setTipoCuenta("CajaDeAhorroEnPesos");
		setTipoMoneda("Pesos");
	}
	
	public void cobroDeMantenimiento() throws SaldoNegativoException{
		if (saldo < costoMantenimiento) throw new SaldoNegativoException();
		saldo =- costoMantenimiento;
	}
	public double getCostoMantenimiento(){
		return costoMantenimiento;
	}
	
}