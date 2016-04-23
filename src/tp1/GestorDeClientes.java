package tp1;

import Excepciones.ExceptionCuitNoEncontrado;
import Excepciones.ExceptionCuitNoValido;

public class GestorDeClientes {
	
	/*
	 * Pre: El cliente no debe existir en el sistema
	 * Post: el cliente es agregado al sistema
	 */
	public void alta(Cliente cliente) throws ExceptionCuitNoValido {
		
		long cuit = cliente.getCuit();
		validarCuit(cuit);
		OperadorBancario.portfolioDeClientes.put(cuit, cliente);
		
	}
	
	
	/*
	 * post: si es existente, arroja una excepción.
	 */
	private void validarCuit(Long cuit) throws ExceptionCuitNoValido {		 
				
		if (OperadorBancario.portfolioDeClientes.containsKey(cuit)){
			throw new ExceptionCuitNoValido("El número de CUIT" + cuit + "ya figura en el sistema.");
		}		
	}
	
	/*
	 * post: el cliente es removido del sistema
	 */
	public void baja(Cliente cliente) {
		OperadorBancario.portfolioDeClientes.remove(cliente.getCuit());
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
