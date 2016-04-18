package tp1;

import tp1.OperadorBancario;
import tp1.Cuenta;

public class Ventanilla {

	enum tipoMonedaPermitida { PESOS, DOLARES }

	public Ventanilla() {
	}

	private String getTipoDeCA(Cuenta c){
		if(c instanceof CajaDeAhorroEnPesos) return "PESOS";
		return "DOLARES";
	}
	
	// tipo moneda solo permite pesos o dolares
//TODO agregar leyendas y texto de salida
	public void depositoEnEfectivo(long cbu, double montoADepositar, String tipoMoneda) {
		try {	
			if(!tipoMoneda.equalsIgnoreCase((tipoMonedaPermitida.values()).toString())){
				System.out.println("tipo de moneda no permitida elija " + tipoMonedaPermitida.values());
				return;
			}		
			Cuenta c = null;
			if (OperadorBancario.portfolioDeCuentas.containsValue(cbu)) {
				c = OperadorBancario.portfolioDeCuentas.get(cbu);
				if (c.isEnabled()) {
					if (getTipoDeCA(c).equals(tipoMoneda)) {
						if (montoADepositar > 0) {
							c.acreditar(cbu, montoADepositar);
							toString(cbu, montoADepositar, c.getSaldo());
						}
					}
				}
			}
		} catch (Exception e) {
		System.out.println(e);
		}
	}
	
	//TODO ver de usarlo para todos los meotodos
	public String toString(Long cbu, double monto, double saldo){
		return "Operacion Satisfactoria /n se deposito en cuenta: "+cbu+" $"+monto+
				"/n quedando un saldo de: $"+saldo;
	}

	
	//TODO agregar leyendas y texto de salida
	public void extraccionEfectivoCA(long cliente, long cbu,
			double montoDeExtraccion, String motivo){
		Cuenta c = null;
		if (OperadorBancario.portfolioDeCuentas.containsValue(cbu)) {
		CajaDeAhorro ca = (CajaDeAhorroEnPesos) OperadorBancario.portfolioDeCuentas.get(cbu);
			if (ca.isEnabled()) {				
				if(ca.getTitulares().contains(cliente)){
					ca.debitar("Debito", montoDeExtraccion, motivo);
				}
			}
		}

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

	
	public String listarMovimientos(long cbu, int ultimosNMovimientos) {
		Cuenta c = null;
		if(OperadorBancario.portfolioDeCuentas.containsKey(cbu)){
			c = OperadorBancario.portfolioDeCuentas.get(cbu);
			if(!c.getHistorial().isEmpty()){
				String historial = "";
				
				int nAListar;
				if(c.getHistorial().size() >= ultimosNMovimientos){
					nAListar = ultimosNMovimientos;
				}else{
					nAListar = c.getHistorial().size();
					System.out.println("no hay tantos movimientos, se listaran " + c.getHistorial().size());
					
				}
					for(int i = 0; i< nAListar ; i++){
						historial += c.getHistorial().get(i) + "/n";	
					}
					return historial;
			}
		}
		return "cuenta inexistente";
	}

	public String listarMovimientos(long cbu) {
		Cuenta c = null;
		if(OperadorBancario.portfolioDeCuentas.containsKey(cbu)){
			c = OperadorBancario.portfolioDeCuentas.get(cbu);
			if(!c.getHistorial().isEmpty()){
				String historial = "";
				for(int i = 0; i< c.getHistorial().size() ; i++){
					historial += c.getHistorial().get(i) + "/n";	
				}
				return historial;
			}
		}
		return "cuenta inexistente";
	}

}
