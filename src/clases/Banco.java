package clases;

import java.util.HashMap;
import java.util.Map;
import excepciones.MontoException;
import excepciones.TransaccionException;

public class Banco{


    public static Map<Long,CuentaComun > portfolioDeCuentas;
    public static Map<Long, Cliente> portfolioDeClientes;
    private static double tipoDeCambioVigente;
    public static long generadorCbu = 1; /*generador de CBU*/
    private static double costoDeMantemientoPesos;
    private double costoDeMantemientoDolares;
    private static CuentaEspecial retenciones;
    private CuentaEspecial mantenimiento;
    
    
    /*
     * Si no se le pasan parámetros
     * Post: El banco se crea con valores por default.
     */
    public Banco() {
        portfolioDeCuentas = new HashMap<Long, CuentaComun>();
        portfolioDeClientes = new HashMap<Long, Cliente>();
        
        tipoDeCambioVigente = 15.0;
        costoDeMantemientoPesos = 30;
        costoDeMantemientoDolares = 2;
    }
    
    
    /*
     * Si se le pasan los parámetros tipoDeCambioVigente y costoDeMantemientoPesos.
     * Post: El banco se crea con un valor calculado para costoDeMantemientoDolares
     */
    public Banco(double tipoDeCambioVigente, double costoDeMantemientoPesos) throws MontoException{
        portfolioDeCuentas = new HashMap<Long, CuentaComun>();
        portfolioDeClientes = new HashMap<Long, Cliente>();
        validar(tipoDeCambioVigente);
        validar(costoDeMantemientoPesos);
        Banco.tipoDeCambioVigente = tipoDeCambioVigente;
        Banco.costoDeMantemientoPesos = costoDeMantemientoPesos;
        this.costoDeMantemientoDolares = costoDeMantemientoPesos/tipoDeCambioVigente;
    }
    
    /*
     * El monto ingresado es mayor a 0.
     */
    public void validar(double cantidad)throws MontoException{
    	if(cantidad<=0){
    		new MontoException("El monto ingresado no es correcto. Ingrese un monto mayor que 0.");
    	}
    }
    
    public double getCostoDeMantenimientoDolares() {
    	return costoDeMantemientoDolares;
    }
    
    public static double getCostoDeMantenimientoPesos(){
    	return costoDeMantemientoPesos;
    }
    
    
    public static double getTipoDeCambioVigente() {
    	return tipoDeCambioVigente;
    }
    
    public void setTipoDeCambioVigente(Double tipoDeCambioVigente) {
        Banco.tipoDeCambioVigente = tipoDeCambioVigente;
    }
    
    public void setCostoDeMantenimientoPesos (double costoDeMantenimientoPesos) {
    	Banco.costoDeMantemientoPesos = costoDeMantenimientoPesos;
    }
    
    public void setCostoDeMantenimientoDolares (double costoDeMantenimientoDolares) {
    	this.costoDeMantemientoDolares = costoDeMantenimientoDolares;
    }
    
    public static void acreditarRetenciones(long cbu, double monto, String motivo) throws TransaccionException, MontoException {
    	Transaccion t = new Transaccion(("Cuenta origen: " + cbu), monto, motivo);
    	retenciones.historial.add(t);
    	retenciones.saldo+=monto;
    }
    
    public static void acreditarRetenciones(long cbu, double monto, String motivo, String observaciones) throws TransaccionException, MontoException {
    	Transaccion t = new Transaccion(("Cuenta origen: " + cbu), monto, motivo, observaciones);
    	retenciones.historial.add(t);
    	retenciones.saldo+=monto;
    }
    
    public void acreditarMantenimiento(long cbu, double monto, String motivo) throws TransaccionException, MontoException {
    	Transaccion t = new Transaccion(("Cuenta origen: " + cbu), monto, motivo);
    	mantenimiento.historial.add(t);
    	mantenimiento.saldo+=monto;
    }
    
    public void acreditarMantenimiento(long cbu, double monto, String motivo, String observaciones) throws TransaccionException, MontoException {
    	Transaccion t = new Transaccion(("Cuenta origen: " + cbu), monto, motivo, observaciones);
    	mantenimiento.historial.add(t);
    	mantenimiento.saldo+=monto;
    }
    
    public double getSaldoMantenimiento() {
    	return mantenimiento.saldo;
    }
    
    public double getSaldoRetenciones(){
    	return retenciones.saldo;
    }
    
}