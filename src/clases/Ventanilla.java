package clases;

import clases.Banco;
import clases.Cuenta;

public class Ventanilla {

	enum tipoMonedaPermitida { PESOS, DOLARES }

	public Ventanilla() {
	}

	//TODO agregar exception y texto de salida
	public void depositoEnEfectivo(long cbu, double montoADepositar, String tipoMoneda) {
		CuentaComun cComun;
		try {	
			if(!tipoMoneda.equalsIgnoreCase((tipoMonedaPermitida.values()).toString())){
				System.out.println("tipo de moneda no permitida elija " + tipoMonedaPermitida.values());
				return;
			}
			
			if (Banco.portfolioDeCuentas.containsKey(cbu)) {
				cComun = Banco.portfolioDeCuentas.get(cbu);
				cComun.acreditar(montoADepositar);
			}
			
		} catch (Exception e) {
		System.out.println(e);
		}
	}
	

	
	//TODO agregar exception y texto de salida
	public void extraccionEfectivoCA(long cliente, long cbu, double montoDeExtraccion, String motivo){
		CajaDeAhorro ca = null;
		if (Banco.portfolioDeCuentas.containsKey(cbu)) {
			Cuenta c = Banco.portfolioDeCuentas.get(cbu);
			if(c.getTipoCuenta().equals("CuentaCorriente")){
				System.out.println("extracciones solo se permiten de Caja de Ahorro");
				return;
			}
			ca = (CajaDeAhorro) c;
			if (ca.isEnabled()) {				
				if(ca.getTitulares().contains(cliente)){
					ca.debitar("Debito", montoDeExtraccion, motivo);
					//toString();
				}
			}
		}

	}

	//TODO agregar exception y texto de salida
	public void transferencia(long cuitCliente, long cbuOrigen, long cbuDestino, double montoTransferencia, String motivo) {
		
		CuentaComun cDestino = null;
		CuentaComun cOrigen = null;
				
		//obtengo las cuentas y me fijo que esten habilitadas
		if(Banco.portfolioDeCuentas.containsKey(cbuOrigen)){
			cOrigen = (CuentaComun)Banco.portfolioDeCuentas.get(cbuOrigen);
			if(!cOrigen.isEnabled()){
				System.out.println("cuenta no hablitada");
				return;
			}
		}
		
		if(Banco.portfolioDeCuentas.containsKey(cbuDestino)){
			cDestino = (CuentaComun)Banco.portfolioDeCuentas.get(cbuDestino);
			if(!cDestino.isEnabled()){
				System.out.println("cuenta no hablitada");
				return;
			}
		}
		
		// verifico que cliente sea titular cuenta de origen
		if(cOrigen.tieneComoCliente(cuitCliente)){
		
			
			//TODO verificar que el tipo de movimiento sea el correcto
			String tipoDeMovimiento = "Transferencia";
			//TODO ver regla de monto por transferencia o movimiento
			
			//TODO recordar que el método debitar devuelve una Transferencia, Acreditar tambien deberia
			// Cambió la firma del método debitar( String tipoDeMovimiento, double monto, String motivo);
			cOrigen.debitar(tipoDeMovimiento, montoTransferencia, motivo); //+ ver regla de monto por transferencia o movimiento
			cDestino.acreditar(montoTransferencia);
			Transaccion transCOrigen = new Transaccion("Transferencia", montoTransferencia, motivo, ("a cuenta " + cDestino.getCbu())); // si fue dolar a pesos o viceversa poner observacion 
			cOrigen.historial.add(transCOrigen);
			Transaccion transCDestino = new Transaccion("Transferencia", montoTransferencia, motivo, ("de cuenta " + cOrigen.getCbu())); // si fue dolar a pesos o viceversa poner observacion 
			cOrigen.historial.add(transCDestino);
		}
	}

	
	public String listarMovimientos(long cbu, int ultimosNMovimientos) {
		Cuenta c = null;
		if(Banco.portfolioDeCuentas.containsKey(cbu)){
			c = Banco.portfolioDeCuentas.get(cbu);
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
		if(Banco.portfolioDeCuentas.containsKey(cbu)){
			c = Banco.portfolioDeCuentas.get(cbu);
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
