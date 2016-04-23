package tp1;

import Excepciones.ExceptionCuitNoValido;

public class PersonaJuridica extends Cliente{
	
	private final String fechaContratoSocial;

	public PersonaJuridica(String nombre, long cuit, Domicilio domicilio, int telefono, boolean habilitado, String fechaContratoSocial) throws ExceptionCuitNoValido
	{
		
		super (nombre, cuit, domicilio, telefono, habilitado);
		this.fechaContratoSocial = fechaContratoSocial;
	}
	
	public PersonaJuridica(String nombre, long cuit, Domicilio domicilio, int telefono, boolean habilitado, String fechaContratoSocial, String otrosDatos) throws ExceptionCuitNoValido {
		
		super (nombre, cuit, domicilio, telefono, habilitado, otrosDatos);
		this.fechaContratoSocial = fechaContratoSocial;
	}
	
	public PersonaJuridica(String nombre, long cuit, Domicilio domicilio, int telefono, String fechaContratoSocial) throws ExceptionCuitNoValido {
		
		super (nombre, cuit, domicilio, telefono);
		this.fechaContratoSocial = fechaContratoSocial;
	}
	
	public String getFechaDelContratoSocial() {
		return fechaContratoSocial;
	}
	
	public String toString() {
		return ("Razón Social: " + super.getNombre()+ " CUIT: " +super.getCuit()+ " Domicilio: " 
				+super.getDomicilio().toString()+ " Telefono: " + super.getTelefono() 
				+(super.isEnabled()?" Cliente habilitado.":" Cliente NO habilitado") 
				+ " Fecha del contrato social: " + fechaContratoSocial);
	}

	/*
	 * pre: Si el cliente es persona jurídica, el CUIT tiene que comenzar con 3
	 */
	@Override
	protected void validarNumeroDeCuitPorTipoDeCliente(Long cuit) throws ExceptionCuitNoValido {
		
		while(cuit>10) {
			cuit /= 10;
		}
		if (cuit != 3){
			throw new ExceptionCuitNoValido("El número de cuit debe comenzar con 3 para Persona Jurídica.");
		}		
	}

}
