package clases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;

import excepciones.ExceptionCuitNoEncontrado;
import excepciones.MontoException;
import excepciones.SaldoNegativoException;
import excepciones.TransaccionException;

public class ProcesadorBatch {
	
	Iterator<Entry<Long, Cuenta>> it = Banco.portfolioDeCuentas.entrySet().iterator();
	Cuenta cuenta;
	Date date = new Date();
	
	public void correBatch(){
		try {
			if(date == getUltimoDiaDelMes()){
				cobrarCosto();
				return;
			}
			System.out.println("Se cobran los costos el ultimo dia del mes");
		} catch (TransaccionException | MontoException
				| ExceptionCuitNoEncontrado | SaldoNegativoException e) {
			e.printStackTrace();
		}
	}
	
	
	private void cobrarCosto() throws TransaccionException, MontoException, ExceptionCuitNoEncontrado, SaldoNegativoException{
		
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
					pagarInteres(cajaAhorroPesos.getCbu());
					cajaAhorroPesos.cobroDeMantenimiento();
					Banco.acreditarRetenciones(cajaAhorroPesos.getCbu(), cajaAhorroPesos.costoMantenimiento, "Cobro retenciones en pesos");
					//TODO ver tipoDeCambio que pide mantenimientosCobrados
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
					pagarInteres(cajaAhorroDolares.getCbu());
					cajaAhorroDolares.cobroDeMantenimiento();
					Banco.acreditarRetenciones(cajaAhorroDolares.getCbu(), cajaAhorroDolares.costoMantenimiento, "Cobro retenciones en dolares", ("valor de cambio " + Banco.getTipoDeCambioVigente()).toString());
					//TODO ver tipoDeCambio que pide mantenimientosCobrados
					mantenimientosCobrados(cajaAhorroDolares.getCbu(), cajaAhorroDolares.getTipoCuenta(), cajaAhorroDolares.getCostoMantenimiento(), "Dolares", "tipoDeCambio"); 
				}
			}
		}
	}
	 
	public void pagarInteres(long cbu) throws TransaccionException, MontoException {
		CajaDeAhorro cajaAhorro = (CajaDeAhorro)Banco.portfolioDeCuentas.get(cbu);
		cajaAhorro.acreditar(cajaAhorro.getTasaDeInteres(), "Intereses mensuales");
	}
	
	
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
	
	public static Date getUltimoDiaDelMes() {
		Calendar calendario = Calendar.getInstance();
		calendario.set(calendario.get(Calendar.YEAR),
				calendario.get(Calendar.MONTH),
				calendario.getActualMaximum(Calendar.DAY_OF_MONTH));

		return calendario.getTime();
	}
	
}
