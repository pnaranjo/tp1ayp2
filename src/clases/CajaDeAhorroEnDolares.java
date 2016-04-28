package clases;

import java.util.ArrayList;

import excepciones.ArrayTitularesException;
import excepciones.MontoException;
import excepciones.MontoTasaDeInteresException;


public class CajaDeAhorroEnDolares extends CajaDeAhorro{

	public CajaDeAhorroEnDolares(double monto, ArrayList<PersonaFisica> titulares,double tasaDeInteres) throws MontoException, ArrayTitularesException, MontoTasaDeInteresException {
		super(monto, titulares, tasaDeInteres);
		costoMantenimiento = Banco.getCostoDeMantenimientoDolares();
		setTipoCuenta("CajaDeAhorroEnDolares");
		setTipoMoneda("Dolares");
	}
}
