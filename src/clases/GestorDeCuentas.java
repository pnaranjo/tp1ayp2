package clases;

import excepciones.ExceptionCbuNoEncontrado;



public class GestorDeCuentas {


	
	public void abrirCajaDeAhorroEnPesos(CajaDeAhorroEnPesos caPesos){
		try {
			Banco.getPortfolioDeCuentas().put(caPesos.getCbu(), caPesos);
			this.hablitarCuenta(caPesos.getCbu());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void abrirCajaDeAhorroEnDolares(CajaDeAhorroEnDolares caDolares){
		try {
			Banco.getPortfolioDeCuentas().put(caDolares.getCbu(), caDolares);	
			this.hablitarCuenta(caDolares.getCbu());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void abrirCuentaCorriente(CuentaCorriente cCorriente){
		try {
			Banco.getPortfolioDeCuentas().put(cCorriente.getCbu(), cCorriente);
			this.hablitarCuenta(cCorriente.getCbu());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void inhablitarCuenta(long cbu) throws ExceptionCbuNoEncontrado{
		if(Banco.getPortfolioDeCuentas().containsKey(cbu)){
			Cuenta cuenta = Banco.getPortfolioDeCuentas().get(cbu);
			cuenta.setDisable();
		}else{
			throw new ExceptionCbuNoEncontrado("Cuenta no existe o no esta vinculada al portfolio de cuentas"); 
		}
	}
	 
	public void hablitarCuenta(long cbu)throws ExceptionCbuNoEncontrado{
		if(Banco.getPortfolioDeCuentas().containsKey(cbu)){
			Cuenta cuenta = Banco.getPortfolioDeCuentas().get(cbu);
			cuenta.setEnable();
		}else{
			throw new ExceptionCbuNoEncontrado("Cuenta no existe o no esta vinculada al portfolio de cuentas");
		}
	}
	
}
