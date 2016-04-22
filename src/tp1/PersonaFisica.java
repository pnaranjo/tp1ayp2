package tp1;

import Excepciones.ExceptionNumeroDeDocumentoNoValido;
import Excepciones.PersonaFisicaException;
import tp1.Cliente;
import tp1.Domicilio;

public class PersonaFisica extends Cliente {
	
	private final String tipoDeDocumento;
	private final int numeroDeDocumento;
	private String profesion;
	private String estadoCivil;
	private String conyugue;
	
	//Constructor para cliente HABILITADO y SOLTERO/A
	public PersonaFisica(String nombre, long cuit, Domicilio domicilio, int telefono, String tipoDeDocumento, int numeroDeDocumento, String profesion) throws ExceptionNumeroDeDocumentoNoValido {
		super (nombre, cuit, domicilio, telefono, true);
		
		validarDocumento(numeroDeDocumento);
		
		this.tipoDeDocumento = tipoDeDocumento;
		this.numeroDeDocumento = numeroDeDocumento;
		this.profesion = profesion;
		this.estadoCivil = "Soltero/a";
		this.conyugue = "";
	}
	
	//Constructor para cliente Casado/a
	public PersonaFisica(String nombre, long cuit, Domicilio domicilio, int telefono, boolean habilitado, String tipoDeDocumento, int numeroDeDocumento, String profesion, String estadoCivil, String conyugue) throws ExceptionNumeroDeDocumentoNoValido {
		super (nombre, cuit, domicilio, telefono, habilitado);
		
		validarDocumento(numeroDeDocumento);
		
		this.tipoDeDocumento = tipoDeDocumento;
		this.numeroDeDocumento = numeroDeDocumento;
		this.profesion = profesion;
		this.estadoCivil = estadoCivil;
		this.conyugue = conyugue;
	}
	
	//Constructor para PersonaFisica con estado Viudo, Divorciado o soltero. 
	public PersonaFisica(String nombre, long cuit, Domicilio domicilio, int telefono, boolean habilitado,String tipoDeDocumento, int numeroDeDocumento, String profesion, String estadoCivil) throws Exception {
		super (nombre, cuit, domicilio, telefono, habilitado);
		validarDocumento(numeroDeDocumento);
		this.tipoDeDocumento = tipoDeDocumento;
		this.numeroDeDocumento = numeroDeDocumento;
		this.profesion = profesion;
		
			if ((estadoCivil.equals("Casado")) || (estadoCivil.equals("Casada")) || (estadoCivil.equals("casado")) || (estadoCivil.equals("casada"))) {
				throw new PersonaFisicaException("Campo <<CONYUGUE>> faltante.");
			} else {
				this.estadoCivil = estadoCivil;
			}
		}
	
	/*
	 * El documento debe ser válido.
	 * El número de El DNI debe ser > 1000000
	 */
	private void validarDocumento(int documento) throws ExceptionNumeroDeDocumentoNoValido{
		
		if (documento < 1000000) {
			throw new ExceptionNumeroDeDocumentoNoValido("Número de documento inválido");
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
	
	public void setEstadoCivilCasado(String estadoCivil, String conyugue){
		this.estadoCivil = estadoCivil; 
		this.conyugue = conyugue;
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
	
	public String toString() {
		
		return (super.toString() + " Tipo de documento: " +tipoDeDocumento+ " Número de documento: " +numeroDeDocumento+ 
				                   " Profesión: " +profesion+ " Estado civil: " +estadoCivil+ (conyugue != null ? (" Cónyugue: " +conyugue):""));
	}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.numeroDeDocumento;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersonaFisica other = (PersonaFisica) obj;
        if (this.numeroDeDocumento != other.numeroDeDocumento) {
            return false;
        }
        return true;
    }
}

			
