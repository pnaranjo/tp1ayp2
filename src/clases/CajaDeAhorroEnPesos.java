package clases;

import java.util.ArrayList;

import excepciones.ArrayTitularesException;
import excepciones.MontoException;


public class CajaDeAhorroEnPesos extends CajaDeAhorro {
	
	public CajaDeAhorroEnPesos(double monto, ArrayList<PersonaFisica> titulares,double tasaDeInteres) throws MontoException, ArrayTitularesException{
		super(monto, titulares, tasaDeInteres);
		costoMantenimiento = Banco.getCostoDeMantenimientoPesos();				
		setTipoCuenta("CajaDeAhorroEnPesos");
		setTipoMoneda("Pesos");
	}
}