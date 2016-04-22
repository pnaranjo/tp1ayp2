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
	
	private String getTipoDeCuenta(Cuenta c){
		if(c instanceof CajaDeAhorroEnPesos){
			return "CajaDeAhorroEnPesos";
		}else if(c instanceof CajaDeAhorroEnDolares){
			return "CajaDeAhorroEnDolares";
		}
		return "CuentaCorriente";
	}
	
	//TODO agregar exception y texto de salida
	public void depositoEnEfectivo(long cbu, double montoADepositar, String tipoMoneda) {
		try {	
			Cuenta c = null;
			
			if(!tipoMoneda.equalsIgnoreCase((tipoMonedaPermitida.values()).toString())){
				System.out.println("tipo de moneda no permitida elija " + tipoMonedaPermitida.values());
				return;
			}
			
			if (montoADepositar > 0){
				System.out.println("el monto tiene que ser mayor a 0");
				return;
			}
			
			if (OperadorBancario.portfolioDeCuentas.containsKey(cbu)) {
				c = OperadorBancario.portfolioDeCuentas.get(cbu);
				if (c.isEnabled()) {
					if(getTipoDeCuenta(c).equals("CuentaCorriente") && tipoMoneda.equals("PESOS")){
						c.acreditar(cbu, montoADepositar);
						//toString();
					}else if (getTipoDeCA(c).equals(tipoMoneda)) {
						c.acreditar(cbu, montoADepositar);
						//toString();		
					}
				}
			}
		} catch (Exception e) {
		System.out.println(e);
		}
	}
	

	
	//TODO agregar exception y texto de salida
	public void extraccionEfectivoCA(long cliente, long cbu, double montoDeExtraccion, String motivo){
		CajaDeAhorro ca = null;
		if (OperadorBancario.portfolioDeCuentas.containsKey(cbu)) {
			Cuenta c = OperadorBancario.portfolioDeCuentas.get(cbu);
			if(getTipoDeCuenta(c).equals("CuentaCorriente")){
				System.out.println("extracciones solo se permiten de Caja de Ahorro");
				return;
			}
			ca = (CajaDeAhorroEnPesos) c;
			if (ca.isEnabled()) {				
				if(ca.getTitulares().contains(cliente)){
					ca.debitar("Debito", montoDeExtraccion, motivo);
					//toString();
				}
			}
		}

	}

	//TODO agregar exception y texto de salida
	public void transferencia(long cliente, long cbuOrigen, long cbuDestino, double montoTransferencia, String motivo) {
		
		// ver ambas cuentas habilitadas
		Cuenta cOrigen = null;
		Cuenta cDestino = null;
		
		//cuenta origen
		if (OperadorBancario.portfolioDeCuentas.containsKey(cbuOrigen)) {
			cOrigen = OperadorBancario.portfolioDeCuentas.get(cbuOrigen);
			if (!cOrigen.isEnabled()) {
				return;
			}
		}

		//cuenta destino
		if (OperadorBancario.portfolioDeCuentas.containsValue(cbuDestino)) {
			cDestino = OperadorBancario.portfolioDeCuentas.get(cbuDestino);
			if (!cDestino.isEnabled()) {
				return;
			}
		}
		
		// que cliente sea titular cuenta de origen
		
		
		// monto transferencia (con retenciones y etc...) no exede limite
		
		
		
		
		// disponible de origen
		
		// si existe un cambio de divisa agregarlo al comentario del ticket con
		
		// el cambio vigente
		
		// hacer la conversion
		
		// depositar en destino
		
		
		// toString();
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
