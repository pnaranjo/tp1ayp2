package tp1;

import java.util.ArrayList;
import java.util.HashSet;

public class GestorDeCuentas {

	
	public void abrirCajaDeAhorroEnPesos(double saldo, ArrayList<PersonaFisica> titulares, double tasaDeInteres, double costoDeMantenimiento){
		CajaDeAhorroEnPesos caPesos = new CajaDeAhorroEnPesos(saldo, titulares, tasaDeInteres, costoDeMantenimiento);
		OperadorBancario.portfolioDeCuentas.put(caPesos.getCbu(), caPesos);
	}
	
	public void abrirCajaDeAhorroEnDolares(double saldo, ArrayList<PersonaFisica> titulares, double tasaDeInteres, double costoDeMantenimiento){
		CajaDeAhorroEnDolares caDolares = new CajaDeAhorroEnDolares(saldo, titulares, tasaDeInteres, costoDeMantenimiento);
		OperadorBancario.portfolioDeCuentas.put(caDolares.getCbu(), caDolares);
	}
	
	public void abrirCuentaCorriente(double montoDeposito, ArrayList<Cliente> titulares,double montoSobreGiro){
		CuentaCorriente cCorriente = new CuentaCorriente(montoDeposito, titulares, montoSobreGiro);
		OperadorBancario.portfolioDeCuentas.put(cCorriente.getCbu(), cCorriente);
	}
	
	public void inhablitarCuenta(long cbu){
		if(OperadorBancario.portfolioDeCuentas.containsKey(cbu)){
			Cuenta cuenta = OperadorBancario.portfolioDeCuentas.get(cbu);
			cuenta.setDisable();
		}
	}
	
	public void hablitarCuenta(long cbu){
		if(OperadorBancario.portfolioDeCuentas.containsKey(cbu)){
			Cuenta cuenta = OperadorBancario.portfolioDeCuentas.get(cbu);
			cuenta.setEnable();
		}
	}
	
}
