package clases;

import java.util.ArrayList;

public class GestorDeCuentas {

	
	public void abrirCajaDeAhorroEnPesos(double saldo, ArrayList<PersonaFisica> titulares, double tasaDeInteres, double costoDeMantenimiento){
		CajaDeAhorroEnPesos caPesos = new CajaDeAhorroEnPesos(saldo, titulares, tasaDeInteres, costoDeMantenimiento);
		Banco.portfolioDeCuentas.put(caPesos.getCbu(), caPesos);
	}
	
	public void abrirCajaDeAhorroEnDolares(double saldo, ArrayList<PersonaFisica> titulares, double tasaDeInteres, double costoDeMantenimiento){
		CajaDeAhorroEnDolares caDolares = new CajaDeAhorroEnDolares(saldo, titulares, tasaDeInteres, costoDeMantenimiento);
		Banco.portfolioDeCuentas.put(caDolares.getCbu(), caDolares);
	}
	
	public void abrirCuentaCorriente(double saldo, ArrayList<Cliente> titulares,double montoSobreGiro,double montoDeposito){
		CuentaCorriente cCorriente = new CuentaCorriente(saldo, titulares, montoSobreGiro, montoDeposito);
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
