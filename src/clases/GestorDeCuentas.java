package clases;

import java.util.ArrayList;
import java.util.HashSet;

import Excepciones.ArrayTitularesException;
import Excepciones.MontoDepositoException;
import Excepciones.MontoException;

public class GestorDeCuentas {

	
	public void abrirCajaDeAhorroEnPesos(double saldo, ArrayList<PersonaFisica> titulares, double tasaDeInteres, double costoDeMantenimiento){
		CajaDeAhorroEnPesos caPesos = new CajaDeAhorroEnPesos(saldo, titulares, tasaDeInteres, costoDeMantenimiento);
		Banco.portfolioDeCuentas.put(caPesos.getCbu(), caPesos);
	}
	
	public void abrirCajaDeAhorroEnDolares(double saldo, ArrayList<PersonaFisica> titulares, double tasaDeInteres, double costoDeMantenimiento){
		CajaDeAhorroEnDolares caDolares = new CajaDeAhorroEnDolares(saldo, titulares, tasaDeInteres, costoDeMantenimiento);
		Banco.portfolioDeCuentas.put(caDolares.getCbu(), caDolares);
	}
	
	public void abrirCuentaCorriente(double montoDeposito, ArrayList<Cliente> titulares,double montoSobreGiro) throws ArrayTitularesException,MontoException,MontoDepositoException{
		CuentaCorriente cCorriente = new CuentaCorriente(montoDeposito, titulares, montoSobreGiro);
		Banco.portfolioDeCuentas.put(cCorriente.getCbu(), cCorriente);
	}
	
	public void inhablitarCuenta(long cbu){
		if(Banco.portfolioDeCuentas.containsKey(cbu)){
			Cuenta cuenta = Banco.portfolioDeCuentas.get(cbu);
			cuenta.setDisable();
		}
	}
	
	public void hablitarCuenta(long cbu){
		if(Banco.portfolioDeCuentas.containsKey(cbu)){
			Cuenta cuenta = Banco.portfolioDeCuentas.get(cbu);
			cuenta.setEnable();
		}
	}
	
}
