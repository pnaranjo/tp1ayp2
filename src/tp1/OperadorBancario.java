package tp1;

import java.util.HashMap;
import java.util.Map;

public class OperadorBancario{

    public static Map<Cuenta, Cuenta> portfolioDeCuentas;

    public static Map<Cliente, Long> portfolioDeClientes;

    public OperadorBancario() {
        portfolioDeCuentas = new HashMap<Cuenta, Cuenta>();
        portfolioDeClientes = new HashMap<Cliente, Long>();
    }
}
