package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clases.Banco;
import clases.CajaDeAhorroEnDolares;
import clases.Domicilio;
import clases.GestorDeClientes;
import clases.GestorDeCuentas;
import clases.PersonaFisica;
import excepciones.ArrayTitularesException;
import excepciones.ExceptionCuitNoValido;
import excepciones.ExceptionNumeroDeDocumentoNoValido;
import excepciones.MontoDepositoException;
import excepciones.MontoException;
import excepciones.MontoTasaDeInteresException;
import excepciones.TransaccionException;

public class PruebaCajaDeAhorroEnDolares {
	static Banco banco;
	static GestorDeClientes gestorClientes;
	static GestorDeCuentas gestorCuentas;
	static ArrayList<PersonaFisica> titular; 
	static Domicilio domicilio;
	 	
	@BeforeClass
	public static void initialize() throws Exception{
		banco = new Banco();	
		gestorClientes = new GestorDeClientes();
		gestorCuentas = new GestorDeCuentas();
		titular = new ArrayList<>();
		domicilio = new Domicilio("Av. Siempre Viva 123", "1676", "Caseros", "Caseros City");
	}
	
	String nombre = "Juan Perez";
	long cuit = 20228833449L; //HabrÃ­a que validar el cuit???
	int telefono = 48329944;
	boolean habilitado = true;
	String tipoDeDocumento = "PASAPORTE";
	int numeroDeDocumento = 22883344;
	String profesion = "Horticultor";
	
	@Test
	public void testCrearCajaDeAhorroEnDolares() throws ExceptionCuitNoValido, MontoDepositoException, MontoException, ArrayTitularesException, ExceptionNumeroDeDocumentoNoValido, TransaccionException, MontoTasaDeInteresException {
		PersonaFisica cliente1 = new PersonaFisica(nombre, cuit, domicilio, telefono, habilitado, tipoDeDocumento, numeroDeDocumento, profesion, "casado", "conyugue");
		ArrayList<PersonaFisica> titulares = new ArrayList<PersonaFisica>();
		titulares.add(cliente1);
		CajaDeAhorroEnDolares c1 = new CajaDeAhorroEnDolares(100, titulares, 200);
		gestorCuentas.abrirCajaDeAhorroEnDolares(c1);
		Assert.assertEquals(true, Banco.portfolioDeCuentas.containsKey(c1.getCbu()));	
		}
	@Test (expected = MontoException.class)
	public void testMontoDeposito() throws ExceptionCuitNoValido, MontoDepositoException, MontoException, ArrayTitularesException, ExceptionNumeroDeDocumentoNoValido, MontoTasaDeInteresException {
		PersonaFisica cl1 = new PersonaFisica(nombre, cuit, domicilio, telefono, habilitado, tipoDeDocumento, numeroDeDocumento, profesion, "casado", "conyugue");
		ArrayList<PersonaFisica> titulares = new ArrayList<PersonaFisica>();
		titulares.add(cl1);
		CajaDeAhorroEnDolares c1 = new CajaDeAhorroEnDolares(0, titulares, 200);
	}
	@Test (expected = MontoTasaDeInteresException.class)
	public void testTasaDeInteres() throws ExceptionCuitNoValido, MontoDepositoException, MontoException, ArrayTitularesException, ExceptionNumeroDeDocumentoNoValido, MontoTasaDeInteresException {
		PersonaFisica cl1 = new PersonaFisica(nombre, cuit, domicilio, telefono, habilitado, tipoDeDocumento, numeroDeDocumento, profesion, "casado", "conyugue");
		ArrayList<PersonaFisica> titulares = new ArrayList<PersonaFisica>();
		titulares.add(cl1);
		CajaDeAhorroEnDolares c1 = new CajaDeAhorroEnDolares(100, titulares,-20);
	}
	@Test (expected = ArrayTitularesException.class)
	public void testTitulares() throws ExceptionCuitNoValido, MontoDepositoException, MontoException, ArrayTitularesException, ExceptionNumeroDeDocumentoNoValido, MontoTasaDeInteresException {
		ArrayList<PersonaFisica> titulares = new ArrayList<PersonaFisica>();
		CajaDeAhorroEnDolares c1 = new CajaDeAhorroEnDolares(10001, titulares, 234);
	}
}
