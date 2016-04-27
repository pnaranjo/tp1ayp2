package clases;

import clases.Banco;
import excepciones.ExceptionCbuNoEncontrado;
import excepciones.MontoException;
import excepciones.TransaccionException;

public class Ventanilla {


		
	public void depositoEnEfectivo(long cbu, double montoADepositar, String tipoMoneda){
		try {	
			tipoMonedaPermitida(tipoMoneda);
			Cuenta cuenta = getCuenta(cbu);
			if(cuenta.getTipoMoneda().equalsIgnoreCase(tipoMoneda)){
				cuenta.acreditar(montoADepositar, "Depósito por ventanilla. ");
			}
		} catch (ExceptionCbuNoEncontrado e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void extraccionEfectivoCA(long cuit, long cbu, double montoDeExtraccion, String motivo) throws TransaccionException, MontoException{
		try {
			Cuenta	cuenta = getCuenta(cbu);
			if(cuenta.getTipoCuenta().equalsIgnoreCase("CuentaCorriente")) throw new Exception("extracciones solo se permiten de Caja de Ahorro");
			 
			if(cuenta.tieneComoCliente(cuit)) throw new Exception("cliente y cuenta no coinciden");
			
			CajaDeAhorro cajaDeAhorro = (CajaDeAhorro)cuenta;
			cajaDeAhorro.debitar(montoDeExtraccion, "Extracción por ventanilla. ");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void transferencia(long cuit, long cbuOrigen, long cbuDestino, double montoTransferencia, String motivo) throws TransaccionException, MontoException {
		try {
			Cuenta cuentaOrigen = getCuenta(cbuOrigen);
			Cuenta cuentaDestino = getCuenta(cbuDestino);
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
		Cuenta cuenta = getCuenta(cbu);
		
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
		Cuenta cuenta = getCuenta(cbu);
		
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
	
	public Cuenta getCuenta(long cbu) throws Exception {
		if (Banco.portfolioDeCuentas.containsKey(cbu)) {
			Cuenta cuenta = Banco.portfolioDeCuentas.get(cbu);
			if (cuenta.isEnabled()) {
				return cuenta;
			}else{ 
				throw new Exception("cuenta no habilitada");
			
			}	
		}
		throw new Exception("cuenta no encontrada");						
	}
	
}
