package clases;

import java.util.HashMap;
import java.util.Map;
import excepciones.MontoException;

public class Banco{


    public static Map<Long,CuentaComun > portfolioDeCuentas;
    public static Map<Long, Cliente> portfolioDeClientes;
    private static double tipoDeCambioVigente = 15.0;
    public static long generadorCbu = 1; /*generador de CBU*/
    private double costoDeMantemientoPesos = 30;
    private double costoDeMantemientoDolares = 2;
    private CuentaEspecial retenciones;
    private CuentaEspecial mantenimiento;
    
    
    public Banco() {
        portfolioDeCuentas = new HashMap<Long, CuentaComun>();
        portfolioDeClientes = new HashMap<Long, Cliente>();
    }
    
    public Banco(double tipoDeCambioVigente, double costoDeMantemientoPesos) throws MontoException{
        portfolioDeCuentas = new HashMap<Long, CuentaComun>();
        portfolioDeClientes = new HashMap<Long, Cliente>();
        validar(tipoDeCambioVigente);
        validar(costoDeMantemientoPesos);
        Banco.tipoDeCambioVigente = tipoDeCambioVigente;
        this.costoDeMantemientoPesos = costoDeMantemientoPesos;
        this.costoDeMantemientoDolares = costoDeMantemientoPesos/tipoDeCambioVigente;
    }
    
    public void validar(double cantidad)throws MontoException{
    	if(cantidad<=0){
    		new MontoException("El monto ingresado no es correcto. Ingrese un monto mayor que 0.");
    	}
    }
    
    public double getCostoDeMantenimientoDolares() {
    	return costoDeMantemientoDolares;
    }
    
    public double getCostoDeMantenimientoPesos(){
    	return costoDeMantemientoPesos;
    }
    
    
    public static double getTipoDeCambioVigente() {
    	return tipoDeCambioVigente;
    }
    
    public void setTipoDeCambioVigente(Double tipoDeCambioVigente) {
        Banco.tipoDeCambioVigente = tipoDeCambioVigente;
    }
    
    public void setCostoDeMantenimientoPesos (double costoDeMantenimientoPesos) {
    	this.costoDeMantemientoPesos = costoDeMantenimientoPesos;
    }
    
    public void setCostoDeMantenimientoDolares (double costoDeMantenimientoDolares) {
    	this.costoDeMantemientoDolares = costoDeMantenimientoDolares;
    }
    
    public void acreditarRetenciones(double monto) {
    	retenciones.saldo+=monto;
    }
    
    public void acreditarMantenimiento(double monto) {
    	mantenimiento.saldo+=monto;
    }
    
    public double getSaldoMantenimiento() {
    	return mantenimiento.saldo;
    }
    
    public double getSaldoRetenciones(){
    	return retenciones.saldo;
    }
    
}