package tests;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import excepciones.ArrayTitularesException;
import excepciones.ExceptionCuitNoValido;
import excepciones.ExceptionNumeroDeDocumentoNoValido;
import excepciones.MontoDepositoException;
import excepciones.MontoException;
import clases.Cliente;
import clases.CuentaComun;
import clases.CuentaCorriente;
import clases.Domicilio;
import clases.Banco;
import clases.PersonaFisica;

public class PruebaCuentaCorriente  {
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
	public void testCrearCuentaCorriente() throws ExceptionCuitNoValido, MontoDepositoException, MontoException, ArrayTitularesException, ExceptionNumeroDeDocumentoNoValido {
		PersonaFisica cl1 = new PersonaFisica(nombre, cuit, domicilio1, telefono, habilitado, tipoDeDocumento, numeroDeDocumento, profesion, "casado", "conyugue");
		ArrayList<Cliente> titulares = new ArrayList<Cliente>();
		titulares.add(cl1);
		CuentaCorriente c1 = new CuentaCorriente(10000, titulares, -3);
		Banco op1 = new Banco();
		op1.portfolioDeCuentas.put(c1.getCbu(),c1);
		Assert.assertEquals(1,op1.portfolioDeCuentas.size());	
		}
	
	@Test (expected = MontoDepositoException.class)
	public void testMontoDeposito() throws ExceptionCuitNoValido, MontoDepositoException, MontoException, ArrayTitularesException, ExceptionNumeroDeDocumentoNoValido {
		Cliente cl1 = new PersonaFisica(nombre, cuit, domicilio1, telefono, habilitado, tipoDeDocumento, numeroDeDocumento, profesion, "casado", "conyugue");
		ArrayList<Cliente> titulares = new ArrayList<Cliente>();
		titulares.add(cl1);
		CuentaCorriente c1 = new CuentaCorriente(2000, titulares, -3);
	}
	
	@Test (expected = MontoException.class)
	public void testMonto() throws ExceptionCuitNoValido, MontoDepositoException, MontoException, ArrayTitularesException, ExceptionNumeroDeDocumentoNoValido {
		Cliente cl1 = new PersonaFisica(nombre, cuit, domicilio1, telefono, habilitado, tipoDeDocumento, numeroDeDocumento, profesion, "casado", "conyugue");
		ArrayList<Cliente> titulares = new ArrayList<Cliente>();
		titulares.add(cl1);
		CuentaCorriente c1 = new CuentaCorriente(10000, titulares, 234);
	}
	
	@Test (expected = MontoException.class)
	public void testTitulares() throws ExceptionCuitNoValido, MontoDepositoException, MontoException, ArrayTitularesException, ExceptionNumeroDeDocumentoNoValido {
		Cliente cl1 = new PersonaFisica(nombre, cuit, domicilio1, telefono, habilitado, tipoDeDocumento, numeroDeDocumento, profesion, "casado", "conyugue");
		ArrayList<Cliente> titulares = new ArrayList<Cliente>();
		
		CuentaCorriente c1 = new CuentaCorriente(10000, titulares, 234);
	}
	
	

}
