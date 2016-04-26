package clases;

import clases.Banco;
import clases.Cuenta;
import excepciones.MontoException;
import excepciones.TransaccionException;

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
				if (cComun.isEnabled()) {
					cComun.acreditar(montoADepositar);		
				}
			}

		} catch (Exception e) {
		System.out.println(e);
		}
	}
	

	
	//TODO agregar exception y texto de salida
	public void extraccionEfectivoCA(long cliente, long cbu, double montoDeExtraccion, String motivo) throws TransaccionException, MontoException{
		CajaDeAhorro ca = null;
		if (Banco.portfolioDeCuentas.containsKey(cbu)) {
			CuentaComun cComun = Banco.portfolioDeCuentas.get(cbu);
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
			}
		}

	}

	//TODO agregar exception y texto de salida
	public void transferencia(long cuitCliente, long cbuOrigen, long cbuDestino, double montoTransferencia, String motivo) throws TransaccionException, MontoException {
		
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
		
		// verificar que cliente sea titular cuenta de origen
		if(cOrigen.tieneComoCliente(cuitCliente)){
			cOrigen.debitar(montoTransferencia); //+ ver regla de monto por transferencia o movimiento
			cDestino.acreditar(montoTransferencia);
			//TODO VER
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
