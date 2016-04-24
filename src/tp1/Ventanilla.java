package tp1;

import tp1.OperadorBancario;
import tp1.Cuenta;

public class Ventanilla {

	enum tipoMonedaPermitida { PESOS, DOLARES }

	public Ventanilla() {
	}

	// tipo moneda solo permite pesos o dolares
	public void depositoEnEfectivo(long cbu, double montoADepositar, String tipoMoneda) {
		try {	
			if(!tipoMoneda.equalsIgnoreCase((tipoMonedaPermitida.values()).toString())){
				System.out.println("tipo de moneda no permitida elija " + tipoMonedaPermitida.values());
				return;
			}		
			Cuenta c = null;
			if (OperadorBancario.portfolioDeCuentas.containsValue(cbu)) {
				// TODO aca tedria que tener c.setCbu para poder tomar obj c con el get
				c = OperadorBancario.portfolioDeCuentas.get(cbu);
				if (c.isEnabled()) {
					if (tipoDeCA(c).equals(tipoMoneda)) {
						if (montoADepositar > 0) {
							c.acreditar(cbu, montoADepositar);
							toString(cbu, montoADepositar, c.getSaldo());
						}
						System.out.println("monto erroneo");
					}
					System.out.println("tipo de moneda erroneo");
				}
				System.out.println("la cuenta no pertenece al banco");
			}
		} catch (Exception e) {
		System.out.println(e);
		}
	}
	
	public String toString(Long cbu, double monto, double saldo){
		return "Operacion Satisfactoria /n se deposito en cuenta: "+cbu+" $"+monto+
				"/n quedando un saldo de: $"+saldo;
	}

	private String tipoDeCA(Cuenta c){
		if(c instanceof CajaDeAhorroEnPesos) return "PESOS";
		return "DOLARES";
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
