package tp1;

import java.util.ArrayList;

public class CajaDeAhorroEnPesos extends CajaDeAhorro {
	public static final CuentaEspecial mantenimientoPesos = new CuentaEspecial(0,"pesos");
	public static double costoDeMantenimientoPesos;
	public static long cbu;
	
	public CajaDeAhorroEnPesos(double saldo, ArrayList<PersonaFisica> titulares,
			double tasaDeInteres, double costoDeMantenimiento) {
		super(saldo, titulares, tasaDeInteres);
		cbu = super.getCbu();
		CajaDeAhorroEnPesos.costoDeMantenimientoPesos = costoDeMantenimiento;
		
	}
	@Override
	public void cobroDeMantenimiento(){
		// faltan comprobar que no quede en negativo
		this.saldo =- CajaDeAhorroEnPesos.costoDeMantenimientoPesos;
		CajaDeAhorroEnPesos.mantenimientoPesos.saldo =+ CajaDeAhorroEnPesos.costoDeMantenimientoPesos;
	}
	
	public double getCostoMantenimiento(){
		return costoDeMantenimientoPesos;
	}
		
}