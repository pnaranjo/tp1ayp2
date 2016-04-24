package clases;

import java.util.HashMap;
import java.util.Map;

public class Banco{


    public static Map<Long,CuentaComun > portfolioDeCuentas;
    public static Map<Long, Cliente> portfolioDeClientes;
    public static double tipoDeCambioVigente = 15.0;
    public static long generadorCbu = 1; /*generador de CBU*/
    public static final double costoDeMantemientoPesos = 30;
    public static final double costoDeMantemientoDolares = 2;
    
    public Banco() {
        portfolioDeCuentas = new HashMap<Long, CuentaComun>();
        portfolioDeClientes = new HashMap<Long, Cliente>();
    }
     
}