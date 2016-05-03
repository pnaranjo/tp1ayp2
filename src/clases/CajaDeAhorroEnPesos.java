package clases;

import java.util.ArrayList;

import excepciones.ArrayTitularesException;
import excepciones.MontoException;
import excepciones.MontoTasaDeInteresException;


public class CajaDeAhorroEnPesos extends CajaDeAhorro {
	
	public CajaDeAhorroEnPesos(double monto, ArrayList<PersonaFisica> titulares,double tasaDeInteres) throws MontoException, ArrayTitularesException, MontoTasaDeInteresException{
		super(monto, titulares, tasaDeInteres);
		costoMantenimiento = Banco.getCostoDeMantenimientoPesos();				
		setTipoCuenta("CajaDeAhorroEnPesos");
		setTipoMoneda("Pesos");
	}
	
}