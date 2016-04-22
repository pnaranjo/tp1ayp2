package tp1;

public class PersonaJuridica extends Cliente{
	
	private final String fechaContratoSocial;

	public PersonaJuridica(String nombre, long cuit, Domicilio domicilio, int telefono, boolean habilitado, String fechaContratoSocial) {
		
		super (nombre, cuit, domicilio, telefono, habilitado);
		this.fechaContratoSocial = fechaContratoSocial;
	}
	
	public PersonaJuridica(String nombre, long cuit, Domicilio domicilio, int telefono, boolean habilitado, String fechaContratoSocial, String otrosDatos) {
		
		super (nombre, cuit, domicilio, telefono, habilitado, otrosDatos);
		this.fechaContratoSocial = fechaContratoSocial;
	}
	
	public PersonaJuridica(String nombre, long cuit, Domicilio domicilio, int telefono, String fechaContratoSocial) {
		
		super (nombre, cuit, domicilio, telefono);
		this.fechaContratoSocial = fechaContratoSocial;
	}
	
	public String getFechaDelContratoSocial() {
		return fechaContratoSocial;
	}
	
	public String toString() {
		return (super.toString() + " Fecha del contrato social: " + fechaContratoSocial);
	}

}
