package tp1;

import java.util.HashSet;

public class OperadorBancario {
	
	
	public static HashSet<Cuenta> portfolioDeCuentas;
	
	public static HashSet<Cliente> portfolioDeClientes;
	
	public OperadorBancario(){
		portfolioDeCuentas = new HashSet<Cuenta>();
		portfolioDeClientes = new HashSet<Cliente>();
	}
        
}
