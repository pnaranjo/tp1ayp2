package tests;


import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

import excepciones.ArrayTitularesException;
import excepciones.ExceptionCuitNoValido;
import excepciones.ExceptionNumeroDeDocumentoNoValido;
import excepciones.MontoDepositoException;
import excepciones.MontoException;
import excepciones.TransaccionException;
import clases.Cliente;
import clases.Cuenta;
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
	public void testCrearCuentaCorriente() throws ExceptionCuitNoValido, MontoDepositoException, MontoException, ArrayTitularesException, ExceptionNumeroDeDocumentoNoValido, TransaccionException {		
		PersonaFisica cl1 = new PersonaFisica(nombre, cuit, domicilio1, telefono, habilitado, tipoDeDocumento, numeroDeDocumento, profesion, "casado", "conyugue");
		ArrayList<Cliente> titulares = new ArrayList<Cliente>();
		titulares.add(cl1);
		CuentaCorriente c1 = new CuentaCorriente(10001, titulares,3000);
		Banco.portfolioDeCuentas.put(c1.getCbu(), c1);
		Assert.assertEquals(1,Banco.portfolioDeCuentas.size());	
		}
	//El montonto de deposito no puede ser menor a 10 mil pesos.
	@Test (expected = MontoDepositoException.class)
	public void testMontoDeposito() throws ExceptionCuitNoValido, MontoDepositoException, MontoException, ArrayTitularesException, ExceptionNumeroDeDocumentoNoValido {
		Cliente cl1 = new PersonaFisica(nombre, cuit, domicilio1, telefono, habilitado, tipoDeDocumento, numeroDeDocumento, profesion, "casado", "conyugue");
		ArrayList<Cliente> titulares = new ArrayList<Cliente>();
		titulares.add(cl1);
		CuentaCorriente c1 = new CuentaCorriente(2000, titulares, 1000);
	}
	@Test (expected = MontoException.class)
	public void testMontoSobregiro() throws ExceptionCuitNoValido, MontoDepositoException, MontoException, ArrayTitularesException, ExceptionNumeroDeDocumentoNoValido {
		Cliente cl1 = new PersonaFisica(nombre, cuit, domicilio1, telefono, habilitado, tipoDeDocumento, numeroDeDocumento, profesion, "casado", "conyugue");
		ArrayList<Cliente> titulares = new ArrayList<Cliente>();
		titulares.add(cl1);
		CuentaCorriente c1 = new CuentaCorriente(10001, titulares, -234);
	}
	@Test (expected = ArrayTitularesException.class)
	public void testTitulares() throws ArrayTitularesException, MontoDepositoException, MontoException{
		ArrayList<Cliente> titulares = new ArrayList<Cliente>();
		CuentaCorriente c1 = new CuentaCorriente(10001, titulares, 234);
		
	}
}
