package pruebas_TP1;

import org.junit.Assert;
import tp1.*;
import org.junit.Test;

import Excepciones.ExceptionCuitNoEncontrado;
import Excepciones.ExceptionCuitNoValido;

public class PruebaGestorDeClientes {
	
	OperadorBancario banco = new OperadorBancario();
	GestorDeClientes gestorDeClientes = new GestorDeClientes();
	
	String codigoPostal1 = "T4000IJN";
	String direccion = "Buenos Aires 672";
	String localidad = "San Miguel de Tucumán";
	String provincia = "Tucumán";
	int telefono = 4256379;
	String fechaDelContratoSocial = "14 de noviembre de 1953";
	long cuit = 30708468483L;
	Domicilio domicilio1 = new Domicilio(direccion, codigoPostal1, localidad, provincia);	
	String otrosDatos = "Certificado en trámite";
	String fechaDelContratoSocial2 = "15/5/2013";
	PersonaJuridica asociacionDeOrtopedia;
	PersonaJuridica asociacion2;
	
	public PruebaGestorDeClientes() throws ExceptionCuitNoValido{
		asociacionDeOrtopedia = new PersonaJuridica("Asociación de Ortopedia y Traumatología del Este de Río Negro y Carmen de Patagones", 
				cuit, domicilio1, telefono, true, fechaDelContratoSocial);
		asociacion2 = new PersonaJuridica("Asociación de Piscicultura", 
				cuit, domicilio1, telefono, false, fechaDelContratoSocial2, otrosDatos);
	}

	/*
	 * pre: el cliente es válido
	 * post: el cliente es dado de alta en el sistema
	 */
	@Test
	public void testAltaClienteValido() throws ExceptionCuitNoValido {
		gestorDeClientes.alta(asociacionDeOrtopedia);
		Assert.assertTrue(gestorDeClientes.esClienteExistente(asociacionDeOrtopedia));
	}
	
	/*
	 * Post: excepción porque los dos clientes tienen el mismo CUIT
	 */
	@Test (expected = ExceptionCuitNoValido.class)
	public void testDosClientesConMismoCuitException() throws ExceptionCuitNoValido {
		gestorDeClientes.alta(asociacionDeOrtopedia);
		gestorDeClientes.alta(asociacion2);
//		Assert.assertTrue(gestorDeClientes.esClienteExistente(asociacionDeOrtopedia) && gestorDeClientes.esClienteExistente(asociacion2));
	}
	
	/*
	 * Pre: no hay ningún cliente dado de Alta
	 * post: esClienteExistente() devuelve false para cualquier consulta
	 */
	@Test 
	public void testEsClienteExistenteFalse() throws ExceptionCuitNoValido, ExceptionCuitNoEncontrado {
		Assert.assertFalse(gestorDeClientes.esClienteExistente(asociacion2));
	}
	
	
	
	

	
	
	
	
	

}
