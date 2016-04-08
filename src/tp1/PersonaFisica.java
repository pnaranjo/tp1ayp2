package boceto_tp1_ayp2;

public class PersonaFisica {
	
	private final String tipoDeDocumento;
	private final int numeroDeDocumento;
	private String profesion;
	private String estadoCivil;
	private String conyugue;
	
	public PersonaFisica(String tipoDeDocumento, int numeroDeDocumento, String profesion, String estadoCivil, String conyugue) {
		this.tipoDeDocumento = tipoDeDocumento;
		this.numeroDeDocumento = numeroDeDocumento;
		this.profesion = profesion;
		this.estadoCivil = estadoCivil;
		this.conyugue = conyugue;
	}
	
	public PersonaFisica(String tipoDeDocumento, int numeroDeDocumento, String profesion, String estadoCivil) throws PersonaFisicaException {
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

