package tp1;

//import boceto_TP.Cliente;
//import boceto_TP.Domicilio;
//import boceto_TP.PersonaFisicaException;

public class PersonaFisica extends Cliente {
	
	private final String tipoDeDocumento;
	private final int numeroDeDocumento;
	private String profesion;
	private String estadoCivil;
	private String conyugue;
	
	//Constructor para cliente HABILITADO y SOLTERO/A
	public PersonaFisica(String nombre, long cuit, Domicilio domicilio, int telefono, String tipoDeDocumento, int numeroDeDocumento, String profesion) {
		super (nombre, cuit, domicilio, telefono, true);
		
		this.tipoDeDocumento = tipoDeDocumento;
		this.numeroDeDocumento = numeroDeDocumento;
		this.profesion = profesion;
		this.estadoCivil = "Soltero/a";
		this.conyugue = "";
	}
	
	public PersonaFisica(String nombre, long cuit, Domicilio domicilio, int telefono, boolean habilitado, String tipoDeDocumento, int numeroDeDocumento, String profesion, String estadoCivil, String conyugue) {
		super (nombre, cuit, domicilio, telefono, habilitado);
		
		this.tipoDeDocumento = tipoDeDocumento;
		this.numeroDeDocumento = numeroDeDocumento;
		this.profesion = profesion;
		this.estadoCivil = estadoCivil;
		this.conyugue = conyugue;
	}
	
	//Constructor para PersonaFisica con estado Viudo, Divorciado o soltero. 
	public PersonaFisica(String nombre, long cuit, Domicilio domicilio, int telefono, boolean habilitado,String tipoDeDocumento, int numeroDeDocumento, String profesion, String estadoCivil) throws PersonaFisicaException {
		super (nombre, cuit, domicilio, telefono, habilitado);
		this.tipoDeDocumento = tipoDeDocumento;
		this.numeroDeDocumento = numeroDeDocumento;
		this.profesion = profesion;
		try {
			if ((estadoCivil.equals("Casado")) || (estadoCivil.equals("Casada")) || (estadoCivil.equals("casado")) || (estadoCivil.equals("casada"))) {
				throw new PersonaFisicaException("El campo <<CONYUGUE>> está incompleto.");
			} else {
				this.estadoCivil = estadoCivil;
			}
	
		}catch (Exception e){
			System.out.println("Excepción por campo <<CONYUGUE>> faltante.");
			/*
			 * Acá habría que hacer algún tipo de LOG
			 * para dejar asentado que hubo un error???
			 * 
			 */
			}
		}
	
	public String getTipoDeDocumento() {
		return tipoDeDocumento;
	}
	
	public int getNumeroDeDocumento(){
		return numeroDeDocumento;
	}
	
	public void setEstadoCivil(String estadoCivil){
		this.estadoCivil = estadoCivil; 
	}
	
	public String getEstadoCivil() {
		return estadoCivil;
	}
	
	public void setConyugue(String conyugue) {
		this.conyugue = conyugue;
	}
	
	public String getConyugue() {
		return conyugue;
	}
	
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	
	public String getProfesion() {
		return profesion;
	}
	}

			