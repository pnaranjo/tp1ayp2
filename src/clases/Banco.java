package clases;

import java.util.HashMap;
import java.util.Map;
import excepciones.MontoException;

public class Banco{


    public static Map<Long,CuentaComun > portfolioDeCuentas;
    public static Map<Long, Cliente> portfolioDeClientes;
    private static double tipoDeCambioVigente = 15.0;
    public static long generadorCbu = 1; /*generador de CBU*/
    private static double costoDeMantemientoPesos = 30;//RC Como todas las cuentas van a tener el mismo costo de mantenimiento
    // para mi no tiene sentido tener costo de mant. pesos y otro dolares. Basta con que sea solam pesos. y en 
    // CA dolares le seteamos el cost. de mant. al valorde cambio vigente. Que opinan? Ademas tuve que agregar la 
    //palabra static al atributo.
    private double costoDeMantemientoDolares = 2;
    private static CuentaEspecial retenciones;//RC agregue la palabra static para poder realizar la acreditacion de retenciones de los movimientos en cc.
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
    	this.costoDeMantemientoPesos = costoDeMantenimientoPesos;
    }
    
    public void setCostoDeMantenimientoDolares (double costoDeMantenimientoDolares) {
    	this.costoDeMantemientoDolares = costoDeMantenimientoDolares;
    }
    
    public static void acreditarRetenciones(double monto) {
    	//TODO generar transaccion
    	retenciones.saldo+=monto;
    }
    
    public void acreditarMantenimiento(double monto) {
    	//TODO generar transaccion
    	mantenimiento.saldo+=monto;
    }
    
    public double getSaldoMantenimiento() {
    	return mantenimiento.saldo;
    }
    
    public double getSaldoRetenciones(){
    	return retenciones.saldo;
    }
    
}