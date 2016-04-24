package tp1;

public class PersonaJuridica extends Cliente{
	
	private final String fechaContratoSocial;

	public PersonaJuridica(String nombre, long cuit, Domicilio domicilio, int telefono, boolean habilitado, String fechaContratoSocial) {
		
		super (nombre, cuit, domicilio, telefono, habilitado);
		this.fechaContratoSocial = fechaContratoSocial;
	}
	
	public String getFechaDelContratoSocial() {
		return fechaContratoSocial;
	}

}
