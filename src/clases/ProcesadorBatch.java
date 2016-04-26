package clases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;

import excepciones.MontoException;
import excepciones.TransaccionException;

public class ProcesadorBatch {
	
	Iterator<Entry<Long, CuentaComun>> it = Banco.portfolioDeCuentas.entrySet().iterator();
	CuentaComun cuenta = null;
	CuentaEspecial cuentaCobroMantenimientoPesos;
	CuentaEspecial cuentaCobroMantenimientoDolares;
	GestorDeCuentas gestorCuentas;
	
	public void cobrarCosto() throws TransaccionException, MontoException{
		
		while(it.hasNext()){
			cuenta = (CuentaComun) it.next();
			
			if(cuenta.getTipoCuenta().equals("CajaDeAhorroEnPesos")){
				CajaDeAhorroEnPesos caPesos = (CajaDeAhorroEnPesos) cuenta;
				
				if (!caPesos.isEnabled()){
					erroresMatenenimiento(caPesos.getCbu(), caPesos.getTipoCuenta(), caPesos.getCostoMantenimiento(), "Deshabilitada");
					
				}else if((caPesos.saldo - caPesos.getCostoMantenimiento()) < 0) {
					erroresMatenenimiento(caPesos.getCbu(), caPesos.getTipoCuenta(), caPesos.getCostoMantenimiento(), "Fondos Insuficientes");
					gestorCuentas.inhablitarCuenta(caPesos.getCbu());
					
				}else{
					caPesos.debitar("debito", caPesos.getCostoMantenimiento(), "Cobro Mantenimiento");
					//TODO ver
					//cuentaCobroMantenimientoPesos.acreditar(caPesos.getCostoMantenimiento());
					mantenimientosCobrados(caPesos.getCbu(), caPesos.getTipoCuenta(), caPesos.getCostoMantenimiento(), caPesos.getTipoMoneda(), "Pesos");
				}
				
			}
			
			if(cuenta.getTipoCuenta().equals("CajaDeAhorroEnDolares")){
				CajaDeAhorroEnDolares caDolares = (CajaDeAhorroEnDolares) cuenta;
				
				if (!caDolares.isEnabled()){
					erroresMatenenimiento(caDolares.getCbu(), caDolares.getTipoCuenta(), caDolares.getCostoDeMantenimientoDolares(), "Deshabilitada");
					
				}else if((caDolares.saldo - caDolares.getCostoDeMantenimientoDolares()) < 0) {
					erroresMatenenimiento(caDolares.getCbu(), caDolares.getTipoCuenta(), caDolares.getCostoDeMantenimientoDolares(), "Fondos Insuficientes");
					gestorCuentas.inhablitarCuenta(caDolares.getCbu());
					
				}else{
					caDolares.debitar("debito", caDolares.getCostoDeMantenimientoDolares(), "Cobro Mantenimiento");
					//TODO ver
					//cuentaCobroMantenimientoPesos.acreditar(caDolares.convertirDolaresAPesos(caDolares.getCostoDeMantenimientoDolares()));
					mantenimientosCobrados(caDolares.getCbu(), caDolares.getTipoCuenta(), caDolares.getCostoDeMantenimientoDolares(), caDolares.getTipoMoneda(), "Dolares");
				}
			}
			
			
		}
		
	}
	
	
	public void mantenimientosCobrados(long cbu, String tipoCuenta, double monto, String tipoMoneda, String tipoCambio){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String ruta = "/home/mantenimeintosCobrados_"+ dateFormat.format(date) +".txt";
		
		try{
			File archivo = new File(ruta);
	        BufferedWriter bw;
	        if(archivo.exists()) {
	            bw = new BufferedWriter(new FileWriter(archivo));
	            bw.write(cbu +", "+ "Tipo de Cuenta: " + tipoCuenta +", "+ "$"+monto +", "+ tipoMoneda + ", "+ tipoCambio + "/n");
	        } else {
	            archivo.createNewFile();
	        	bw = new BufferedWriter(new FileWriter(archivo));
	            bw.write(cbu +", "+ "Tipo de Cuenta: " + tipoCuenta +", "+ "$"+monto +", "+ tipoMoneda + ", "+ tipoCambio + "/n");
	        }
	        bw.close();	
		}catch(Exception e){
			
		}
		
	}
	
	public void erroresMatenenimiento(long cbu, String tipoCuenta, double monto, String motivo){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String ruta = "/home/erroresMatenenimiento_"+ dateFormat.format(date) +".txt";
		
		try{
			File archivo = new File(ruta);
	        BufferedWriter bw;
	        if(archivo.exists()) {
	            bw = new BufferedWriter(new FileWriter(archivo));
	            bw.write(cbu +", "+ "Tipo de Cuenta: " + tipoCuenta +", "+ "$"+monto +", "+ motivo + "/n");
	        } else {
	        	archivo.createNewFile();
	            bw = new BufferedWriter(new FileWriter(archivo));
	            bw.write(cbu +", "+ "Tipo de Cuenta: " + tipoCuenta +", "+ "$"+monto +", "+ motivo + "/n");
	        }
	        bw.close();	
		}catch(Exception e){
			
		}
	}
	
	
	
	
	
	

}
