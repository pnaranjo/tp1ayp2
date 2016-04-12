package tp1;

import tp1.OperadorBancario;
import tp1.Cuenta;

public class Ventanilla {

	enum tipoMoneda {
		PESOS, DOLARES
	}

	public Ventanilla() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Se denomina depósito en efectivo al acto de entregar al Banco una suma de
	 * dinero en efectivo para acreditarlo a una Cuenta bancaria (sumarlo al
	 * saldo de la misma). La operación se puede realizar si la cuenta destino
	 * está habilitada y solo permite depósitos en la moneda nominal. Es decir
	 * en una cuenta en pesos solo se podrá realizar depósitos en pesos y en una
	 * cuenta en dólares, solo depósitos en dólares.
	 */

	// tipo moneda solo permite pesos o dolares
	public void depositoEnEfectivo(long cuenta, double montoADepositar, String tipoMoneda){
		long cuentaADepositar = cuenta;
	    try{
	    	
		//buscar cuenta	
		if(buscarCuenta(cuenta)){
			//ver cuenta habilitada
			
			if(true/*.isEnabled()*/){
				
				
				//tipo moneda coincida con cuenta
				if(true/*c.getTipoCuenta*/){
					
					
					//sumar monto al saldo
					if(montoADepositar > 0){
						//c.acreditar(cuenta, montoADepositar);
						//toString de ticket Deposito OK
					}
				}
			}
		}
		}catch(Exception e){
			
		}
	    
		
	}

	public boolean buscarCuenta(long cuenta) {
		while (!OperadorBancario.portfolioDeCuentas.isEmpty()) {
			if (OperadorBancario.portfolioDeCuentas.iterator().hasNext()) {
				Cuenta c = OperadorBancario.portfolioDeCuentas.iterator()
						.next();
				if (c.getCbu() == cuenta) {
					return true;
				}
				OperadorBancario.portfolioDeCuentas.iterator().next();
			}
		}
		return false;
	}

	public void extraccionEfectivoCA(long cliente, long cuenta,
			double montoDeExtraccion, String motivo) {
		// ver cuenta habilitada
		// ver que cliente sea titular de la cuenta
		// restar monto al saldo y ver que no exeda lo disponible
		// crear un movimiento
		// toString de ticket extraccion OK, si cuenta no habilitada devolver
		// extraccion KO

	}

	public void transferencia(long cliente, long cuentaOrigen,
			long cuentaDestino, double montoTransferencia, String motivo) {
		// ver ambas cuentas habilitadas
		// que cliente sea titular cuenta de origen
		// monto transferencia (con retenciones y etc...) no exede limite
		// disponible de origen
		// si existe un cambio de divisa agregarlo al comentario del ticket con
		// el cambio vigente
		// hacer la conversion
		// depositar en destino
		// toString con ok o ko
	}

	// lista los ultimos n movimientos de la cuenta
	public String listarMovimientos(long cuenta, int ultimosNMovimientos) {
		// ver cuenta habilitada
		// recorrer cuenta los ultimos n movimientos

		return null;
	}

	// lista todos los movimientos de la cuenta
	public String listarMovimientos(long cuenta) {
		// ver cuenta habilitada
		// recorrer toda la cuenta

		return null;
	}

}
