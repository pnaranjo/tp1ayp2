package clases;

import java.util.ArrayList;

import excepciones.ArrayTitularesException;
import excepciones.MontoException;

public class CajaDeAhorroEnDolares extends CajaDeAhorro{
	
	public CajaDeAhorroEnDolares(double saldo, ArrayList<PersonaFisica> titulares,double tasaDeInteres) throws MontoException, ArrayTitularesException {
		super(saldo, titulares, tasaDeInteres);
		//costoMantenimiento = Banco.getCostoDeMantenimientoPesos() * Banco.getTipoDeCambioVigente();		
		costoMantenimiento = Banco.getCostoDeMantenimientoDolares();
		setTipoCuenta("CajaDeAhorroEnDolares");
		setTipoMoneda("Dolares");
	}
	
}
