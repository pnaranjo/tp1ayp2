package clases;

import java.util.HashMap;
import java.util.Map;
import excepciones.MontoException;
import excepciones.TransaccionException;

public class Banco{


    public static Map<Long,Cuenta> portfolioDeCuentas;
    public static Map<Long, Cliente> portfolioDeClientes;
    private static double tipoDeCambioVigente;
    private static long generadorCbu;
    private static double costoDeMantemientoPesos;
    private static double costoDeMantemientoDolares;
    private static CuentaEspecial retenciones;
    private CuentaEspecial mantenimiento;
    

    
    /*
     * Si no se le pasan parámetros
     * Post: El banco se crea con valores por default.
     */
    public Banco() throws MontoException {
        portfolioDeCuentas = new HashMap<Long, Cuenta>();
        portfolioDeClientes = new HashMap<Long, Cliente>();
        
        //TODO creo una cuenta especial con saldo 0.001, corregirlo luego
        mantenimiento = new CuentaEspecial(0.001);
        retenciones = new CuentaEspecial(0.001);
        mantenimiento.saldo=0;
        retenciones.saldo=0;
        
        setTipoDeCambioVigente(15.0);
        setCostoDeMantenimientoPesos(30.0);
        setCostoDeMantenimientoDolares(2.0);        
        generadorCbu = 0;
    }
    
    
    /*
     * Si se le pasan los parámetros tipoDeCambioVigente y costoDeMantemientoPesos.
     * Post: El banco se crea con un valor calculado para costoDeMantemientoDolares
     */
    public Banco(double tipoDeCambioVigente, double costoDeMantemientoPesos) throws MontoException{
        portfolioDeCuentas = new HashMap<Long, Cuenta>();
        portfolioDeClientes = new HashMap<Long, Cliente>();
        this.setTipoDeCambioVigente(tipoDeCambioVigente);
        this.setCostoDeMantenimientoPesos(costoDeMantemientoPesos);
        this.setCostoDeMantenimientoDolares(costoDeMantemientoPesos/tipoDeCambioVigente);
        generadorCbu = 0;
    }
    
    
    /*
     * Se genera el CBU para cada cuenta.
     * Post: el CBU generado es un número distinto para cada cuenta. 
     */
    public static long generarNuevoCbu(){
    	return generadorCbu++;
    }
    
    /*
     * El monto ingresado es mayor a 0.
     */
    private void validar(double cantidad)throws MontoException{
    	if(cantidad<=0){
    		throw new MontoException("El monto ingresado no es correcto. Ingrese un monto mayor que 0.");
    	}
    }
    
    public static double getCostoDeMantenimientoDolares() {
    	return costoDeMantemientoDolares;
    }
    
    public static double getCostoDeMantenimientoPesos(){
    	return costoDeMantemientoPesos;
    }
    
    
    public static double getTipoDeCambioVigente() {
    	return tipoDeCambioVigente;
    }
    
    public void setTipoDeCambioVigente(Double tipoDeCambioVigente) throws MontoException {
        validar(tipoDeCambioVigente);
        Banco.tipoDeCambioVigente = tipoDeCambioVigente;
    }
    
    public void setCostoDeMantenimientoPesos (double costoDeMantenimientoPesos) throws MontoException {
    	validar(costoDeMantenimientoPesos);
    	Banco.costoDeMantemientoPesos = costoDeMantenimientoPesos;
    }
    
    public void setCostoDeMantenimientoDolares (double costoDeMantenimientoDolares) throws MontoException {
    	validar(costoDeMantenimientoDolares);
    	costoDeMantemientoDolares = costoDeMantenimientoDolares;
    }
    
    /*
     * pre: el monto es válido
     * post: incrementa el saldo y genera una Transacción en el historial
     */
    public static void acreditarRetenciones(long cbu, double monto, String motivo) throws TransaccionException, MontoException {
    	Transaccion t = new Transaccion(("Cuenta origen: " + cbu), monto, motivo);
    	retenciones.historial.add(t);
    	retenciones.saldo+=monto;
    }

    /*
     * pre: el monto es válido
     * post: incrementa el saldo y genera una Transacción en el historial con Observaciones
     */
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