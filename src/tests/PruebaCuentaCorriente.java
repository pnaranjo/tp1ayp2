package tests;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import excepciones.ArrayTitularesException;
import excepciones.ExceptionCuitNoValido;
import excepciones.ExceptionNumeroDeDocumentoNoValido;
import excepciones.MontoDepositoException;
import excepciones.MontoException;

import clases.Cliente;
import clases.CuentaCorriente;
import clases.Domicilio;
import clases.Banco;
import clases.GestorDeClientes;
import clases.GestorDeCuentas;
import clases.PersonaFisica;

public class PruebaCuentaCorriente  {
	
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
	
	
	//El montonto de deposito no puede ser menor a 10 mil pesos.
	@Test
	public void testCrearCuentaCorriente() throws Exception {
	PersonaFisica cliente1 = new PersonaFisica(nombre, cuit, domicilio, telefono, habilitado, tipoDeDocumento, numeroDeDocumento, profesion, "casado", "conyugue");
	ArrayList<Cliente> titulares = new ArrayList<Cliente>();
	titulares.add(cliente1);
	CuentaCorriente cCorriente = new CuentaCorriente(10001, titulares,3000);
	gestorCuentas.abrirCuentaCorriente(cCorriente);
	Assert.assertEquals(true, Banco.portfolioDeCuentas.containsKey(cCorriente.getCbu()));	
	}
	
	@Test (expected = MontoDepositoException.class)
	public void testMontoDeposito() throws ExceptionCuitNoValido, MontoDepositoException, MontoException, ArrayTitularesException, ExceptionNumeroDeDocumentoNoValido {
		Cliente cl1 = new PersonaFisica(nombre, cuit, domicilio, telefono, habilitado, tipoDeDocumento, numeroDeDocumento, profesion, "casado", "conyugue");
		ArrayList<Cliente> titulares = new ArrayList<Cliente>();
		titulares.add(cl1);
		CuentaCorriente c1 = new CuentaCorriente(2000, titulares, 1000);
	}
	
	@Test (expected = MontoException.class)
	public void testMontoSobregiro() throws ExceptionCuitNoValido, MontoDepositoException, MontoException, ArrayTitularesException, ExceptionNumeroDeDocumentoNoValido {
		Cliente cl1 = new PersonaFisica(nombre, cuit, domicilio, telefono, habilitado, tipoDeDocumento, numeroDeDocumento, profesion, "casado", "conyugue");
		ArrayList<Cliente> titulares = new ArrayList<Cliente>();
		titulares.add(cl1);
		CuentaCorriente c1 = new CuentaCorriente(10001, titulares, -234);
	}
	@Test (expected = ArrayTitularesException.class)
	public void testTitulares() throws ArrayTitularesException, MontoDepositoException, MontoException, ExceptionNumeroDeDocumentoNoValido, ExceptionCuitNoValido{
		Cliente cl1 = new PersonaFisica(nombre, cuit, domicilio, telefono, habilitado, tipoDeDocumento, numeroDeDocumento, profesion, "casado", "conyugue");
		ArrayList<Cliente> titulares = new ArrayList<Cliente>();
		CuentaCorriente c1 = new CuentaCorriente(10001, titulares, 234);
		
	}
}
