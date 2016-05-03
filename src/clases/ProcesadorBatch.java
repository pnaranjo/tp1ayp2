package clases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import excepciones.ExceptionCuitNoEncontrado;
import excepciones.MontoException;
import excepciones.SaldoNegativoException;
import excepciones.TransaccionException;

public class ProcesadorBatch {
	
	Iterator<Entry<Long, Cuenta>> it = Banco.getPortfolioDeCuentas().entrySet().iterator();
	Cuenta cuenta;
	Date date = new Date();
	CuentaEspecial cuentaEspecialCobroMantenimiento ;
	
	
	
	public ProcesadorBatch() throws MontoException, TransaccionException {
		cuentaEspecialCobroMantenimiento = new CuentaEspecial();
	}
	
	
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void correBatchParaTest() throws TransaccionException, MontoException, ExceptionCuitNoEncontrado, SaldoNegativoException{
		try {
			cobrarCosto();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void cobrarCosto() throws TransaccionException, MontoException, ExceptionCuitNoEncontrado, SaldoNegativoException, IOException{
		
		
		
		while(it.hasNext()){
			Map.Entry e = (Map.Entry)it.next();
			cuenta = Banco.portfolioDeCuentas.get(e.getKey());
			
			if(cuenta.getTipoCuenta().equalsIgnoreCase("CajaDeAhorroEnPesos")){ 
				CajaDeAhorroEnPesos cajaAhorroPesos = (CajaDeAhorroEnPesos) cuenta;
				
				if (!cajaAhorroPesos.isEnabled()){
					erroresMatenenimiento(cajaAhorroPesos.getCbu(), cajaAhorroPesos.getTipoCuenta(), cajaAhorroPesos.getCostoMantenimiento(), "Deshabilitada");
				}else if ((cajaAhorroPesos.getSaldo() - cajaAhorroPesos.getCostoMantenimiento()) < 0){
					erroresMatenenimiento(cajaAhorroPesos.getCbu(), cajaAhorroPesos.getTipoCuenta(), cajaAhorroPesos.getCostoMantenimiento(), "Fondos Insuficientes");
					cajaAhorroPesos.setDisable();
				}else{
					pagarInteres(cajaAhorroPesos.getCbu());
					cajaAhorroPesos.cobroDeMantenimiento();
					cuentaEspecialCobroMantenimiento.acreditar(cajaAhorroPesos.getCostoMantenimiento(), "Cobro retenciones en pesos", "de cuenta: " + cajaAhorroPesos.getCbu());
					mantenimientosCobrados(cajaAhorroPesos.getCbu(), cajaAhorroPesos.getTipoCuenta(), cajaAhorroPesos.getCostoMantenimiento(), "Pesos", "Pesos"); 
				} 
			}	
			 
			if(cuenta.getTipoCuenta().equalsIgnoreCase("CajaDeAhorroEnDolares")){ 
				CajaDeAhorroEnDolares cajaAhorroDolares = (CajaDeAhorroEnDolares) cuenta;
	
				if (!cajaAhorroDolares.isEnabled()){
					erroresMatenenimiento(cajaAhorroDolares.getCbu(), cajaAhorroDolares.getTipoCuenta(), cajaAhorroDolares.getCostoMantenimiento(), "Deshabilitada");
				}else if ((cajaAhorroDolares.getSaldo() - cajaAhorroDolares.getCostoMantenimiento()) < 0){
					erroresMatenenimiento(cajaAhorroDolares.getCbu(), cajaAhorroDolares.getTipoCuenta(), cajaAhorroDolares.getCostoMantenimiento(), "Fondos Insuficientes");
					cajaAhorroDolares.setDisable();
				}else{
					pagarInteres(cajaAhorroDolares.getCbu());
					cajaAhorroDolares.cobroDeMantenimiento();
					cuentaEspecialCobroMantenimiento.acreditar(cajaAhorroDolares.costoMantenimiento, "Cobro retenciones en dolares", "de cuenta: " + cajaAhorroDolares.getCbu());
					mantenimientosCobrados(cajaAhorroDolares.getCbu(), cajaAhorroDolares.getTipoCuenta(), cajaAhorroDolares.getCostoMantenimiento(), "Dolares", "tipoDeCambio"); 
				}
			}
		}
	}
	 
	public void pagarInteres(long cbu) throws TransaccionException, MontoException {
		CajaDeAhorro cajaAhorro = (CajaDeAhorro)Banco.getPortfolioDeCuentas().get(cbu);
		cajaAhorro.acreditar(cajaAhorro.getTasaDeInteres(), "Intereses mensuales");
	}
	
	//Pre: tener creada en C: la carpeta Banco
	public void mantenimientosCobrados(long cbu, String tipoCuenta, double monto, String tipoMoneda, String tipoCambio){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String ruta = "c:\\Banco\\" + "MantenimientoCobrados_"+ dateFormat.format(date) +".txt";
		try{
			File archivo = new File(ruta);		
			FileWriter escribirArchivo = new FileWriter(archivo, true); 
			BufferedWriter buffer = new BufferedWriter(escribirArchivo); 
			buffer.write("cbu: "+cbu +", "+ "Tipo de Cuenta: " + tipoCuenta +", "+ tipoMoneda+" "+ monto +", "+ tipoCambio ); 
			buffer.newLine(); 
			buffer.close();
		}catch (IOException e){
			
		}
		
	}
	
	//Pre: tener creada en C: la carpeta Banco
	public static void erroresMatenenimiento(long cbu, String tipoCuenta, double monto, String motivo) throws IOException{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String ruta = "c:\\Banco\\" + "ErroresMantenimientoCobrados_"+ dateFormat.format(date) +".txt";
		try{
			File archivo = new File(ruta);		
			FileWriter escribirArchivo = new FileWriter(archivo, true); 
			BufferedWriter buffer = new BufferedWriter(escribirArchivo); 
			buffer.write("cbu: "+cbu +", "+ "Tipo de Cuenta: " + tipoCuenta +", "+ "$"+monto +", "+ motivo); 
			buffer.newLine();  
			buffer.close();	
		}catch (IOException e){
			
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
