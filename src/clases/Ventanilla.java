package clases;

import clases.Banco;
import excepciones.ExceptionCbuNoEncontrado;
import excepciones.MontoException;
import excepciones.TransaccionException;

public class Ventanilla {

<<<<<<< HEAD
	public enum TipoMonedaPermitida { PESOS , DOLARES }

	public Ventanilla() {
	} 

	//TODO agregar exception y texto de salida
	public void depositoEnEfectivo(long cbu, double montoADepositar, String tipoMoneda) {
		Cuenta cComun;
		TipoMonedaPermitida tmp = null;
		
=======
	public void depositoEnEfectivo(long cbu, double montoADepositar, String tipoMoneda){
>>>>>>> branch 'master' of https://github.com/pnaranjo/tp1ayp2.git
		try {	
			tipoMonedaPermitida(tipoMoneda);
			CuentaComun	cComun = getCuenta(cbu);
			if(cComun.getTipoMoneda().equalsIgnoreCase(tipoMoneda)){
				cComun.acreditar(montoADepositar, "Depósito por ventanilla. ");
			}
		} catch (ExceptionCbuNoEncontrado e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void extraccionEfectivoCA(long cuit, long cbu, double montoDeExtraccion, String motivo) throws TransaccionException, MontoException{
		try {
			CuentaComun	cComun = getCuenta(cbu);
			if(cComun.getTipoCuenta().equalsIgnoreCase("CuentaCorriente")) throw new Exception("extracciones solo se permiten de Caja de Ahorro");
			 
			if(cComun.tieneComoCliente(cuit)) throw new Exception("cliente y cuenta no coinciden");
			
			CajaDeAhorro cajaDeAhorro = (CajaDeAhorro)cComun;
			cajaDeAhorro.debitar(montoDeExtraccion, "Extracción por ventanilla. ");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void transferencia(long cuit, long cbuOrigen, long cbuDestino, double montoTransferencia, String motivo) throws TransaccionException, MontoException {
		try {
			CuentaComun cuentaOrigen = getCuenta(cbuOrigen);
			CuentaComun cuentaDestino = getCuenta(cbuDestino);
			if(cuentaOrigen.tieneComoCliente(cuit)) throw new Exception("cliente y cuenta no coinciden");

			if(cuentaOrigen.getTipoMoneda().equalsIgnoreCase("Dolares") && cuentaDestino.getTipoMoneda().equalsIgnoreCase("Pesos")){
				cuentaOrigen.debitar(montoTransferencia, ("Transferencia a cuenta: " + cuentaDestino.getCbu())); 
				cuentaDestino.acreditar(cuentaDestino.convertirDolaresAPesos(montoTransferencia), ("Transferencia de cuenta: " + cuentaOrigen.getCbu()));
			
			}else if(cuentaOrigen.getTipoMoneda().equalsIgnoreCase("Pesos") && cuentaDestino.getTipoMoneda().equalsIgnoreCase("Dolares")){
				cuentaOrigen.debitar(montoTransferencia, ("Transferencia a cuenta: " + cuentaDestino.getCbu())); 
				cuentaDestino.acreditar(cuentaDestino.convertirPesosADolares(montoTransferencia), ("Transferencia de cuenta: " + cuentaOrigen.getCbu()));
				
			}else{
				cuentaOrigen.debitar(montoTransferencia, ("Transferencia a cuenta: " + cuentaDestino.getCbu())); 
				cuentaDestino.acreditar(montoTransferencia, ("Transferencia de cuenta: " + cuentaOrigen.getCbu()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public String listarMovimientos(long cbu, int ultimosNMovimientos) throws Exception {
			
		String historial = "";
		CuentaComun cuenta = getCuenta(cbu);
		
		if(cuenta.getHistorial().isEmpty()){
			return "Historial vacio, no hay datos para listar";
		}
		
		if(cuenta.getHistorial().size() < ultimosNMovimientos){
			ultimosNMovimientos = cuenta.getHistorial().size();
			System.out.println("no hay tantos movimientos, se listaran " + ultimosNMovimientos + " movimientos");
		}
			
		for(int i = 0; i < ultimosNMovimientos ; i++){
			historial += (cuenta.getHistorial().get(i)).toString() + "/n";	
		}
		
		return historial;
	}

	public String listarMovimientos(long cbu) throws Exception {
		
		String historial = "";
		CuentaComun cuenta = getCuenta(cbu);
		
		if(cuenta.getHistorial().isEmpty()){
			return "Historial vacio, no hay datos para listar";
		}	
			
		for(int i = 0; i< cuenta.getHistorial().size() ; i++){
			historial += (cuenta.getHistorial().get(i)).toString() + "/n";	
		}
		
		return historial;
	}
	
	public void tipoMonedaPermitida(String tipoMoneda) throws Exception{
		if(!tipoMoneda.equalsIgnoreCase("Pesos") && !tipoMoneda.equalsIgnoreCase("Dolares")){
			throw new Exception("Tipo Moneda No Permitida, solo se aceptan Pesos o Dolares");
		}
	}
	
	public CuentaComun getCuenta(long cbu) throws Exception {
		if (Banco.portfolioDeCuentas.containsKey(cbu)) {
<<<<<<< HEAD
			Cuenta cComun = Banco.portfolioDeCuentas.get(cbu);
			if(cComun.getTipoCuenta().equals("CuentaCorriente")){
				System.out.println("extracciones solo se permiten de Caja de Ahorro");
				return;
			}
			ca = (CajaDeAhorro) cComun;
			if (ca.isEnabled()) {				
				if(ca.getTitulares().contains(cliente)){
					ca.debitar("Debito", montoDeExtraccion, motivo);
					//toString();
				}
=======
			CuentaComun cComun = Banco.portfolioDeCuentas.get(cbu);
			if (cComun.isEnabled()) {
				return cComun;
			}else{ 
				throw new Exception("cuenta no habilitada");
>>>>>>> branch 'master' of https://github.com/pnaranjo/tp1ayp2.git
			}
		}
		throw new ExceptionCbuNoEncontrado("Cuenta No existe");
	}

}
