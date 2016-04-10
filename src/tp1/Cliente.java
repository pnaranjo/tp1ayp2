package tp1;

public abstract class Cliente {
	
	private final String nombre;
	private final long cuit;
	private Domicilio domicilio;
	private int telefono;
	private boolean habilitado;
	private String otrosDatos;
	
	public Cliente(String nombre, long cuit, Domicilio domicilio, int telefono, boolean habilitado, String otrosDatos) {
		this.nombre = nombre;
		this.cuit = cuit;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.habilitado = habilitado;
		this.otrosDatos = otrosDatos;
	}
	
	public Cliente(String nombre, long cuit, Domicilio domicilio, int telefono, boolean habilitado) {
		this.nombre = nombre;
		this.cuit = cuit;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.habilitado = habilitado;
	}
	
	public Cliente(String nombre, long cuit, Domicilio domicilio, int telefono) {
		this.nombre = nombre;
		this.cuit = cuit;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.habilitado = true;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public void setOtrosDatos(String otrosDatos) {
		this.otrosDatos = otrosDatos;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public long getCuit(){
		return cuit;
	}
	
	public Domicilio getDomicilio(){
		return domicilio;
	}
	
	public int getTelefono(){
		return telefono;
	}
	
	public String getOtrosDatos(){	
		return otrosDatos;
	}
	
	public boolean isEnabled() {
		return habilitado;
	}
	
	public void habilitar() {
		habilitado = true;
	}
	
	public void deshabilitar(){
		habilitado = false;
	}	

}
