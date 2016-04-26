package clases;

import excepciones.ExceptionCuitNoEncontrado;



public class GestorDeCuentas {


	
	public void abrirCajaDeAhorroEnPesos(CajaDeAhorroEnPesos caPesos){
		try {
			Banco.portfolioDeCuentas.put(caPesos.getCbu(), caPesos);
			this.hablitarCuenta(caPesos.getCbu());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void abrirCajaDeAhorroEnDolares(CajaDeAhorroEnDolares caDolares){
		try {
			Banco.portfolioDeCuentas.put(caDolares.getCbu(), caDolares);	
			this.hablitarCuenta(caDolares.getCbu());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void abrirCuentaCorriente(CuentaCorriente cCorriente){
		try {
			Banco.portfolioDeCuentas.put(cCorriente.getCbu(), cCorriente);
			this.hablitarCuenta(cCorriente.getCbu());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void inhablitarCuenta(long cbu) throws ExceptionCuitNoEncontrado{
		if(Banco.portfolioDeCuentas.containsKey(cbu)){
			Cuenta cuenta = Banco.portfolioDeCuentas.get(cbu);
			cuenta.setDisable();
		}else{
			throw new ExceptionCuitNoEncontrado("Cuenta no existe o no esta vinculada al portfolio de cuentas"); 
		}
	}
	 
	public void hablitarCuenta(long cbu)throws ExceptionCuitNoEncontrado{
		if(Banco.portfolioDeCuentas.containsKey(cbu)){
			Cuenta cuenta = Banco.portfolioDeCuentas.get(cbu);
			cuenta.setEnable();
		}else{
			throw new ExceptionCuitNoEncontrado("Cuenta no existe o no esta vinculada al portfolio de cuentas");
		}
	}
	
}
