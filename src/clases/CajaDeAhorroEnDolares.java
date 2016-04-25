package clases;

import java.util.ArrayList;

public class CajaDeAhorroEnDolares extends CajaDeAhorro{
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
	}
	
	public double getCostoDeMantenimientoDolares(){
		return costoDeMantenimientoDolares;
	}

}
