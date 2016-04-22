package tp1;

import Excepciones.ExceptionCuitNoEncontrado;

public class GestorDeClientes {
	
	
	public void alta(Cliente cliente) {
		
		OperadorBancario.portfolioDeClientes.put(cliente.getCuit(), cliente);
		
	}
	
	public void baja(Cliente cliente) {
		OperadorBancario.portfolioDeClientes.remove(cliente);
	}
	
	
	/*
	 * devuelve true si el cliente ya es cliente del banco.
	 * El cuit es el identificador único.
	 */
	public boolean esClienteExistente(Cliente cliente) {
		return OperadorBancario.portfolioDeClientes.containsKey(cliente.getCuit());
		
	}
	
	public Cliente buscarConCuit (long cuit) throws ExceptionCuitNoEncontrado{
		
		Cliente resultado;	
		if(OperadorBancario.portfolioDeClientes.containsKey(cuit)){
			resultado =  OperadorBancario.portfolioDeClientes.get(cuit);
			return resultado;
		}
	
		// Si llegó hasta acá es que no encontró el cuit.
		throw new ExceptionCuitNoEncontrado("El CUIT no figura en el sistema.");	
	}

}
