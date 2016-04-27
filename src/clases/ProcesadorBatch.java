package clases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;

import excepciones.ExceptionCuitNoEncontrado;
import excepciones.MontoException;
import excepciones.SaldoNegativoException;
import excepciones.TransaccionException;

public class ProcesadorBatch {
	
	Iterator<Entry<Long, CuentaComun>> it = Banco.portfolioDeCuentas.entrySet().iterator();
	CuentaComun cuenta = null;
	CuentaEspecial cuentaCobroMantenimientoPesos;
	CuentaEspecial cuentaCobroMantenimientoDolares;
	GestorDeCuentas gestorCuentas;
	
	
	public void cobrarCosto() throws TransaccionException, MontoException, ExceptionCuitNoEncontrado, SaldoNegativoException{
		
		while(it.hasNext()){
			cuenta = (CuentaComun) it.next();
			
			if(!cuenta.getTipoCuenta().equalsIgnoreCase("CajaDeAhorroEnPesos")){ 
				CajaDeAhorroEnPesos cajaAhorroPesos = (CajaDeAhorroEnPesos) cuenta;
				
				if (!cajaAhorroPesos.isEnabled()){
					erroresMatenenimiento(cajaAhorroPesos.getCbu(), cajaAhorroPesos.getTipoCuenta(), cajaAhorroPesos.getCostoMantenimiento(), "Deshabilitada");
				}else if ((cajaAhorroPesos.getSaldo() - cajaAhorroPesos.getCostoMantenimiento()) < 0){
					erroresMatenenimiento(cajaAhorroPesos.getCbu(), cajaAhorroPesos.getTipoCuenta(), cajaAhorroPesos.getCostoMantenimiento(), "Fondos Insuficientes");
					cajaAhorroPesos.setDisable();
				}else{
					cajaAhorroPesos.cobroDeMantenimiento();
					//TODO acreditar en cuentaEspecial
					//TODO ver tipoDeCambio
					mantenimientosCobrados(cajaAhorroPesos.getCbu(), cajaAhorroPesos.getTipoCuenta(), cajaAhorroPesos.getCostoMantenimiento(), "Pesos", "tipoDeCambio"); 
				}
			}	
			
			
			if(!cuenta.getTipoCuenta().equalsIgnoreCase("CajaDeAhorroEnDolares")){ 
				CajaDeAhorroEnDolares cajaAhorroDolares = (CajaDeAhorroEnDolares) cuenta;
				
				if (!cajaAhorroDolares.isEnabled()){
					erroresMatenenimiento(cajaAhorroDolares.getCbu(), cajaAhorroDolares.getTipoCuenta(), cajaAhorroDolares.getCostoMantenimiento(), "Deshabilitada");
				}else if ((cajaAhorroDolares.getSaldo() - cajaAhorroDolares.getCostoMantenimiento()) < 0){
					erroresMatenenimiento(cajaAhorroDolares.getCbu(), cajaAhorroDolares.getTipoCuenta(), cajaAhorroDolares.getCostoMantenimiento(), "Fondos Insuficientes");
					cajaAhorroDolares.setDisable();
				}else{
					cajaAhorroDolares.cobroDeMantenimiento();
					//TODO acreditar en cuentaEspecial
					//TODO ver tipoDeCambio
					mantenimientosCobrados(cajaAhorroDolares.getCbu(), cajaAhorroDolares.getTipoCuenta(), cajaAhorroDolares.getCostoMantenimiento(), "Dolares", "tipoDeCambio"); 
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
