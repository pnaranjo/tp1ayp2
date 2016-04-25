package clases;


import excepciones.ArrayTitularesException;
import excepciones.MontoDepositoException;
import excepciones.MontoException;

public class GestorDeCuentas {

	public void abrirCajaDeAhorroEnPesos(CajaDeAhorroEnPesos caPesos){
		Banco.portfolioDeCuentas.put(caPesos.getCbu(), caPesos);

	}
	
	public void abrirCajaDeAhorroEnDolares(CajaDeAhorroEnDolares caDolares){
		Banco.portfolioDeCuentas.put(caDolares.getCbu(), caDolares);
	}
	
	public void abrirCuentaCorriente(CuentaCorriente cCorriente){
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
