package tp1;

import java.util.HashMap;
import java.util.Map;

public class OperadorBancario{

    public static Map<Long,Cuenta > portfolioDeCuentas;

    public static Map<Long, Cliente> portfolioDeClientes;

    public OperadorBancario() {
        portfolioDeCuentas = new HashMap<Long, Cuenta>();
        portfolioDeClientes = new HashMap<Long, Cliente>();
    }
    
}
