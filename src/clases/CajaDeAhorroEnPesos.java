package clases;

import java.util.ArrayList;

import excepciones.MontoException;
import excepciones.SaldoNegativoException;

public class CajaDeAhorroEnPesos extends CajaDeAhorro {
	
	public double costoMantenimiento;
	//public String tipoCuenta;
	public String tipoMoneda;
	
	
	public CajaDeAhorroEnPesos(double saldo, ArrayList<PersonaFisica> titulares,double tasaDeInteres) throws MontoException{
		super(saldo, titulares, tasaDeInteres);
		super.tipoDeCuenta = "CajaDeAhorroEnPesos";
		super.tipoDeMoneda = "Pesos";
		costoMantenimiento = Banco.getCostoDeMantenimientoPesos();		
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