package clases;

import java.util.ArrayList;

import excepciones.MontoException;
import excepciones.SaldoNegativoException;

public class CajaDeAhorroEnPesos extends CajaDeAhorro {
	/*	Codigo Pablo
	public static final CuentaEspecial mantenimientoPesos = new CuentaEspecial(0,"pesos");
	public static double costoDeMantenimientoPesos;
	public static long cbu;

	public CajaDeAhorroEnPesos(double saldo, ArrayList<PersonaFisica> titulares,double tasaDeInteres, double costoDeMantenimiento) {
		super(saldo, titulares, tasaDeInteres);
		cbu = super.getCbu();
		CajaDeAhorroEnPesos.costoDeMantenimientoPesos = costoDeMantenimiento;
		
	}*/
	
	/* RC */
	
	public double costoMantenimiento;
	

	public CajaDeAhorroEnPesos(double saldo, ArrayList<PersonaFisica> titulares,double tasaDeInteres) throws MontoException{
		super(saldo, titulares, tasaDeInteres);
		if (saldo <= 0) throw new MontoException();
		costoMantenimiento = Banco.getCostoDeMantenimientoPesos();		
	}
	/* Codigo Pablo
	@Override
	public void cobroDeMantenimiento(){
		// faltan comprobar que no quede en negativo
		this.saldo =- CajaDeAhorroEnPesos.costoDeMantenimientoPesos;
		CajaDeAhorroEnPesos.mantenimientoPesos.saldo =+ CajaDeAhorroEnPesos.costoDeMantenimientoPesos;
	}*/
	
	/* RC */
	public void cobroDeMantenimiento() throws SaldoNegativoException{
		if (saldo < costoMantenimiento) throw new SaldoNegativoException();
		saldo =- costoMantenimiento;
	}
	public double getCostoMantenimiento(){
		return costoMantenimiento;
	}
	
	
	
		
}