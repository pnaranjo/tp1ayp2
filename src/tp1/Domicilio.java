package tp1;

public class Domicilio {
	
	private String direccion;
	private String codigoPostal;
	private String localidad;
	private String provincia;
	
	public Domicilio(String direccion, String codigoPostal, String localidad, String provincia) {
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
		this.provincia = provincia;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public String getCodigoPostal() {
		return codigoPostal;
	}
	
	public String getLocalidad() {
		return localidad;
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	public String toString() {
		return ("Dirección: " +direccion+ " Código Postal: "+codigoPostal+ " Localidad: " +localidad+ " Provincia: " +provincia);
	}

}
