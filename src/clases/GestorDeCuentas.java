package clases;


public class GestorDeCuentas {

	
	public void abrirCajaDeAhorroEnPesos(CajaDeAhorroEnPesos caPesos){
		Banco.portfolioDeCuentas.put(caPesos.getCbu(), caPesos);
		this.hablitarCuenta(caPesos.getCbu());
	}
	
	public void abrirCajaDeAhorroEnDolares(CajaDeAhorroEnDolares caDolares){
		Banco.portfolioDeCuentas.put(caDolares.getCbu(), caDolares);
		this.hablitarCuenta(caDolares.getCbu());
	}
	
	
	public void abrirCuentaCorriente(CuentaCorriente cCorriente){
		Banco.portfolioDeCuentas.put(cCorriente.getCbu(), cCorriente);
		this.hablitarCuenta(cCorriente.getCbu());
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
