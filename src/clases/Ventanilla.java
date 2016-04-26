package clases;

import clases.Banco;
import clases.Cuenta;
import excepciones.ExceptionCbuNoEncontrado;
import excepciones.MontoException;
import excepciones.TransaccionException;

public class Ventanilla {

	public void depositoEnEfectivo(long cbu, double montoADepositar, String tipoMoneda){
		try {	
			tipoMonedaPermitida(tipoMoneda);
			CuentaComun	cComun = getCuenta(cbu);
			if(cComun.getTipoMoneda().equalsIgnoreCase(tipoMoneda)){
				cComun.acreditar(montoADepositar);
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
			//TODO debitar no recibe motivo
			cajaDeAhorro.debitar(montoDeExtraccion);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void transferencia(long cuit, long cbuOrigen, long cbuDestino, double montoTransferencia, String motivo) throws TransaccionException, MontoException {
		try {
			CuentaComun cuentaOrigen = getCuenta(cbuOrigen);
			CuentaComun cuentaDestino = getCuenta(cbuDestino);
			if(cuentaOrigen.tieneComoCliente(cuit)) throw new Exception("cliente y cuenta no coinciden");
			//TODO falta motivo
			cuentaOrigen.debitar(montoTransferencia); 
			cuentaDestino.acreditar(montoTransferencia);
			
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public void tipoMonedaPermitida(String tipoMoneda) throws Exception{
		if(!tipoMoneda.equalsIgnoreCase("Pesos") && !tipoMoneda.equalsIgnoreCase("Dolares")){
			throw new Exception("Tipo Moneda No Permitida, solo se aceptan Pesos o Dolares");
		}
	}
	
	public CuentaComun getCuenta(long cbu) throws Exception {
		if (Banco.portfolioDeCuentas.containsKey(cbu)) {
			CuentaComun cComun = Banco.portfolioDeCuentas.get(cbu);
			if (cComun.isEnabled()) {
				return cComun;
			}else{ 
				throw new Exception("cuenta no habilitada");
			}
		}
		throw new ExceptionCbuNoEncontrado("Cuenta No existe");
	}

}
