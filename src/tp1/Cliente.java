package tp1;

import Excepciones.*;

public abstract class Cliente {
	
	private final String nombre;
	private final long cuit;
	private Domicilio domicilio;
	private int telefono;
	private boolean activo;
	private String otrosDatos;
	
	/*
	 * Constructor para cliente con otrosDatos
	 */
	public Cliente(String nombre, long cuit, Domicilio domicilio, int telefono, boolean activo, String otrosDatos) throws ExceptionCuitNoValido {
		validarCuit(cuit);
		this.nombre = nombre;
		this.cuit = cuit;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.activo = activo;
		this.otrosDatos = otrosDatos;
	}
	
	/*
	 * Constructor para cliente inactivo
	 */
	public Cliente(String nombre, long cuit, Domicilio domicilio, int telefono, boolean habilitado) throws ExceptionCuitNoValido {
		validarCuit(cuit);
		this.nombre = nombre;
		this.cuit = cuit;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.activo = habilitado;
	}

	/*
	 * Constructor para cliente activo
	 */
	public Cliente(String nombre, long cuit, Domicilio domicilio, int telefono) throws ExceptionCuitNoValido {
		validarCuit(cuit);
		this.nombre = nombre;
		this.cuit = cuit;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.activo = true;
	}



	
	/*
	 * pre: El cuit debe ser válido: debe ser >= 20000000010 y <= que 39999999999 
	 * pre: El número de CUIT no debe estar repetido.
	 * 		El Nº de cuit NO se escribe con guiones ni separadores.
	 * 		Si el cliente es persona física, el CUIT tiene que comenzar con 2
	 * 		
	 */
	private void validarCuit(Long cuit) throws ExceptionCuitNoValido {			
		
		if (cuit < 20000000010L || cuit > 39999999999L){
			throw new ExceptionCuitNoValido("El número de CUIT" + cuit + "ya figura en el sistema.");
		}	
		
		validarNumeroDeCuitPorTipoDeCliente(cuit);		
	}
	
	protected abstract void validarNumeroDeCuitPorTipoDeCliente(Long cuit) throws ExceptionCuitNoValido;


	abstract public String toString();
	
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
		return activo;
	}
	
	public void activar() {
		activo = true;
	}
	
	public void desactivar(){
		activo = false;
	}	

    @Override
    public int hashCode() {        
        return (int) (long) this.cuit;
        
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.cuit != other.cuit) {
            return false;
        }
        return true;
    }
     
}
