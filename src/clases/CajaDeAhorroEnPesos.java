package clases;

import java.util.ArrayList;

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
	

	public CajaDeAhorroEnPesos(double saldo, ArrayList<PersonaFisica> titulares,double tasaDeInteres) {
		super(saldo, titulares, tasaDeInteres);
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
	public void cobroDeMantenimiento(){
		saldo =- costoMantenimiento;
	}
	public double getCostoMantenimiento(){
		return costoMantenimiento;
	}
	
	
	
		
}