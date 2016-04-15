package tp1;

import java.util.HashMap;
import java.util.Map;

public class OperadorBancario{

    public static Map<Cuenta, Cuenta> portfolioDeCuentas;
    public static Map<Cliente, Long> portfolioDeClientes;
    public static double tipoDeCambioVigente = 15.0;
    public static long generadorCbu = 1; /*generador de CBU*/
    public static final double costoDeMantemientoPesos = 30;
    public static final double costoDeMantemientoDolares = 2;
    
    public OperadorBancario() {
        portfolioDeCuentas = new HashMap<Cuenta, Cuenta>();
        portfolioDeClientes = new HashMap<Cliente, Long>();
    }
}
