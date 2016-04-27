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
	
	Iterator<Entry<Long, Cuenta>> it = Banco.portfolioDeCuentas.entrySet().iterator();
	Cuenta cuenta = null;
	CuentaEspecial cuentaCobroMantenimientoPesos;
	CuentaEspecial cuentaCobroMantenimientoDolares;
	GestorDeCuentas gestorCuentas;
	
	
	public void cobrarCosto() throws TransaccionException, MontoException, ExceptionCuitNoEncontrado, SaldoNegativoException{
		
		while(it.hasNext()){
			cuenta = (Cuenta) it.next();
			
			if(!cuenta.getTipoCuenta().equalsIgnoreCase("CajaDeAhorroEnPesos")){ 
				CajaDeAhorroEnPesos cajaAhorroPesos = (CajaDeAhorroEnPesos) cuenta;
				
				if (!cajaAhorroPesos.isEnabled()){
					erroresMatenenimiento(cajaAhorroPesos.getCbu(), cajaAhorroPesos.getTipoCuenta(), cajaAhorroPesos.getCostoMantenimiento(), "Deshabilitada");
				}else if ((cajaAhorroPesos.getSaldo() - cajaAhorroPesos.getCostoMantenimiento()) < 0){
					erroresMatenenimiento(cajaAhorroPesos.getCbu(), cajaAhorroPesos.getTipoCuenta(), cajaAhorroPesos.getCostoMantenimiento(), "Fondos Insuficientes");
					cajaAhorroPesos.setDisable();
				}else{
					cajaAhorroPesos.cobroDeMantenimiento();
					Banco.acreditarRetenciones(cajaAhorroPesos.getCbu(), cajaAhorroPesos.costoMantenimiento, "Cobro retenciones en pesos");
					//TODO ver tipoDeCambio que pide mantenimientosCobrados
					mantenimientosCobrados(cajaAhorroPesos.getCbu(), cajaAhorroPesos.getTipoCuenta(), cajaAhorroPesos.getCostoMantenimiento(), "Pesos", "tipoDeCambio"); 
				}
			}	
			
			/*
			if(!cuenta.getTipoCuenta().equalsIgnoreCase("CajaDeAhorroEnDolares")){ 
				CajaDeAhorroEnDolares cajaAhorroDolares = (CajaDeAhorroEnDolares) cuenta;
				
<<<<<<< HEAD
				if (!caDolares.isEnabled()){
					erroresMatenenimiento(caDolares.getCbu(), caDolares.getTipoCuenta(), caDolares.getCostoMantenimiento(), "Deshabilitada");
					
				}else if((caDolares.saldo - caDolares.getCostoMantenimiento()) < 0) {
					erroresMatenenimiento(caDolares.getCbu(), caDolares.getTipoCuenta(), caDolares.getCostoMantenimiento(), "Fondos Insuficientes");
					gestorCuentas.inhablitarCuenta(caDolares.getCbu());
					
				}else{				//RC- Liber esto no seria mejor asi? espero confirmacion.	
					caDolares.debitar(/*"debito", *///caDolares.getCostoMantenimiento(), "Cobro Mantenimiento");
					//TODO ver
					//cuentaCobroMantenimientoPesos.acreditar(caDolares.convertirDolaresAPesos(caDolares.getCostoDeMantenimientoDolares()));
		/*			mantenimientosCobrados(caDolares.getCbu(), caDolares.getTipoCuenta(), caDolares.getCostoMantenimiento(), caDolares.getTipoMoneda(), "Dolares");
======= 
				if (!cajaAhorroDolares.isEnabled()){
					erroresMatenenimiento(cajaAhorroDolares.getCbu(), cajaAhorroDolares.getTipoCuenta(), cajaAhorroDolares.getCostoMantenimiento(), "Deshabilitada");
				}else if ((cajaAhorroDolares.getSaldo() - cajaAhorroDolares.getCostoMantenimiento()) < 0){
					erroresMatenenimiento(cajaAhorroDolares.getCbu(), cajaAhorroDolares.getTipoCuenta(), cajaAhorroDolares.getCostoMantenimiento(), "Fondos Insuficientes");
					cajaAhorroDolares.setDisable();
				}else{
					cajaAhorroDolares.cobroDeMantenimiento();
					Banco.acreditarRetenciones(cajaAhorroDolares.getCbu(), cajaAhorroDolares.costoMantenimiento, "Cobro retenciones en dolares", ("valor de cambio " + Banco.getTipoDeCambioVigente()).toString());
					//TODO ver tipoDeCambio que pide mantenimientosCobrados
					mantenimientosCobrados(cajaAhorroDolares.getCbu(), cajaAhorroDolares.getTipoCuenta(), cajaAhorroDolares.getCostoMantenimiento(), "Dolares", "tipoDeCambio"); 
>>>>>>> branch 'master' of https://github.com/pnaranjo/tp1ayp2.git
				}
			}
		}
	}*/
	
	
	public void mantenimientosCobrados(long cbu, String tipoCuenta, double monto, String tipoMoneda, String tipoCambio){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String ruta = File.separator + "mantenimeintosCobrados_"+ dateFormat.format(date) +".txt";
		
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
			e.printStackTrace();
		}
		
	}
	
	public void erroresMatenenimiento(long cbu, String tipoCuenta, double monto, String motivo){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String ruta = File.separator + "mantenimeintosCobrados_"+ dateFormat.format(date) +".txt";
		
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
			e.printStackTrace();
		}
	}
	

}
