package tp1;

import java.util.ArrayList;

public class CajaDeAhorroEnDolares extends CajaDeAhorro {
		private static double costoDeMantenimientoDolares;
		public CajaDeAhorroEnDolares(ArrayList<PersonaFisica> titulares, double tasaDeInteres, double tipoDeCambioVigente, double costoDeMantenimiento){
			super (titulares, tasaDeInteres, tipoDeCambioVigente);
			CajaDeAhorroEnDolares.costoDeMantenimientoDolares = costoDeMantenimiento;
		}
		
		public double getCostoDeManteniento(){
			return CajaDeAhorroEnDolares.costoDeMantenimientoDolares;
		}
}
