package clases;

import java.util.Iterator;

import excepciones.ExceptionCuitNoEncontrado;
import excepciones.ExceptionCuitNoValido;

public class GestorDeClientes {
	
	/*
	 * Pre: El cliente no debe existir en el sistema
	 * Post: el cliente es agregado al sistema
	 */
	public void alta(Cliente cliente) throws ExceptionCuitNoValido {
		
		long cuit = cliente.getCuit();
		validarCuit(cuit);
		Banco.portfolioDeClientes.put(cuit, cliente);
		
	}
	
	
	/*
	 * post: si es existente, arroja una excepción.
	 */
	private void validarCuit(Long cuit) throws ExceptionCuitNoValido {		 
				
		if (Banco.portfolioDeClientes.containsKey(cuit)){
			throw new ExceptionCuitNoValido("El número de CUIT" + cuit + "ya figura en el sistema.");
		}		
	}
	
	/* 
	 * pre: el cliente existe en el sistema.
	 * 		el cliente NO está asociado a ninguna cuenta ACTIVA.
	 * post: el cliente pasa a estado inactivo. 
	 *
	 */
	public void baja(Cliente cliente) {
						
		// if (OperadorBancario.portfolioDeCuentas.
		// while (!hayCuentaActiva) {traeme todas las cuentas en que esté este cliente y decime si alguna está activa}
		boolean esCliente = Banco.portfolioDeClientes.containsKey(cliente);
				
		if (!esCliente || !hayCuentaActiva(cliente)){
			cliente.desactivar();
			}
	} 
	
	public void activar(Cliente cliente) {
		boolean esCliente = Banco.portfolioDeClientes.containsKey(cliente);
		
		if (esCliente){
			cliente.activar();
			}
	}
	
	private boolean hayCuentaActiva(Cliente cliente){
		boolean hayCuentaActiva = false;
				
		Iterator<Long> it = Banco.portfolioDeClientes.keySet().iterator();
		
		while(it.hasNext()){
			  Long key = (Long) it.next();
			  boolean esCliente = Banco.portfolioDeCuentas.get(key).tieneComoCliente(cliente.getCuit());
			  boolean esCuentaActiva = Banco.portfolioDeCuentas.get(key).isEnabled();
			  hayCuentaActiva = esCliente & esCuentaActiva;
			  if(hayCuentaActiva) {
				  return true;
			  }
			}
		return hayCuentaActiva;
		
	}
	
	
	/*
	 * devuelve true si el cliente ya es cliente del banco.
	 * El cuit es el identificador único.
	 */
	public boolean esClienteExistente(Cliente cliente) {
		return Banco.portfolioDeClientes.containsKey(cliente.getCuit());
		
	}
	
	public Cliente buscarConCuit (long cuit) throws ExceptionCuitNoEncontrado{
		
		Cliente resultado;	
		if(Banco.portfolioDeClientes.containsKey(cuit)){
			resultado =  Banco.portfolioDeClientes.get(cuit);
			return resultado;
		}
	
		// Si llegó hasta acá es que no encontró el cuit.
		throw new ExceptionCuitNoEncontrado("El CUIT no figura en el sistema.");	
	}

}
