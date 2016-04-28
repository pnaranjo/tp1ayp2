package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import clases.Banco;
import clases.CajaDeAhorroEnPesos;
import clases.Cliente;
import clases.CuentaCorriente;
import clases.Domicilio;
import clases.PersonaFisica;
import excepciones.ArrayTitularesException;
import excepciones.ExceptionCuitNoValido;
import excepciones.ExceptionNumeroDeDocumentoNoValido;
import excepciones.MontoDepositoException;
import excepciones.MontoException;
import excepciones.MontoTasaDeInteresException;
import excepciones.TransaccionException;

public class PruebaCajaDeAhorroEnPesos {
	String codigoPostal1 = "1426";
	String direccion = "Las Heras 2532";
	String localidad = "Capital";
	String provincia = "CABA";
	Domicilio domicilio1 = new Domicilio(direccion, codigoPostal1, localidad, provincia);	
	String nombre = "Juan Perez";
	long cuit = 20228833449L; //Habría que validar el cuit???
	int telefono = 48329944;
	boolean habilitado = true;
	String tipoDeDocumento = "PASAPORTE";
	int numeroDeDocumento = 22883344;
	String profesion = "Horticultor";

	
	@Test
	public void testCrearCajaDeAhorroEnPesos() throws ExceptionCuitNoValido, MontoDepositoException, MontoException, ArrayTitularesException, ExceptionNumeroDeDocumentoNoValido, TransaccionException {
		//completar
		}
	@Test (expected = MontoException.class)
	public void testMontoDeposito() throws ExceptionCuitNoValido, MontoDepositoException, MontoException, ArrayTitularesException, ExceptionNumeroDeDocumentoNoValido, MontoTasaDeInteresException {
		PersonaFisica cl1 = new PersonaFisica(nombre, cuit, domicilio1, telefono, habilitado, tipoDeDocumento, numeroDeDocumento, profesion, "casado", "conyugue");
		ArrayList<PersonaFisica> titulares = new ArrayList<PersonaFisica>();
		titulares.add(cl1);
		CajaDeAhorroEnPesos c1 = new CajaDeAhorroEnPesos(0, titulares, 200);
	}
	@Test (expected = MontoTasaDeInteresException.class)
	public void testTasaDeInteres() throws ExceptionCuitNoValido, MontoDepositoException, MontoException, ArrayTitularesException, ExceptionNumeroDeDocumentoNoValido, MontoTasaDeInteresException {
		PersonaFisica cl1 = new PersonaFisica(nombre, cuit, domicilio1, telefono, habilitado, tipoDeDocumento, numeroDeDocumento, profesion, "casado", "conyugue");
		ArrayList<PersonaFisica> titulares = new ArrayList<PersonaFisica>();
		titulares.add(cl1);
		CajaDeAhorroEnPesos c1 = new CajaDeAhorroEnPesos(100, titulares,-20);
	}
	@Test (expected = ArrayTitularesException.class)
	public void testTitulares() throws ExceptionCuitNoValido, MontoDepositoException, MontoException, ArrayTitularesException, ExceptionNumeroDeDocumentoNoValido, MontoTasaDeInteresException {
		ArrayList<PersonaFisica> titulares = new ArrayList<PersonaFisica>();
		CajaDeAhorroEnPesos c1 = new CajaDeAhorroEnPesos(10001, titulares, 234);
	}

}
