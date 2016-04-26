package clases;

import java.util.ArrayList;

public class CajaDeAhorroEnDolares extends CajaDeAhorro{
/* Codigo Pablo
	public static final CuentaEspecial mantenimientoDolares  = new CuentaEspecial(0,"pesos");
	public static double costoDeMantenimientoDolares;
	public static long cbu;
	
	public CajaDeAhorroEnDolares(double saldo, ArrayList<PersonaFisica> titulares,
		double tasaDeInteres, double costoDeMantenimiento) {
		super(saldo, titulares, tasaDeInteres);
		cbu = super.getCbu();
		CajaDeAhorroEnDolares.costoDeMantenimientoDolares = costoDeMantenimiento;
	}
	
	@Override
	public void cobroDeMantenimiento() {
		// faltan comprobar que no quede en negativo
		this.saldo =- CajaDeAhorroEnDolares.costoDeMantenimientoDolares;
		CajaDeAhorroEnDolares.mantenimientoDolares.saldo =+ CajaDeAhorroEnDolares.costoDeMantenimientoDolares;	
	}*/
	
	/* RC */
	public double costoMantenimiento;
	public CajaDeAhorroEnDolares(double saldo, ArrayList<PersonaFisica> titulares,double tasaDeInteres) {
		super(saldo, titulares, tasaDeInteres);
		costoMantenimiento = Banco.getCostoDeMantenimientoPesos() * Banco.getTipoDeCambioVigente();		
	}
	public double getCostoDeMantenimientoDolares(){
		return costoMantenimiento;
	}
	public void cobroDeMantenimiento(){
		saldo =- costoMantenimiento;
	}
}
