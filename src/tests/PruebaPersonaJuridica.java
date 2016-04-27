package tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clases.*;
import excepciones.ExceptionCuitNoValido;


public class PruebaPersonaJuridica {
	
	static Banco banco;
	
	@BeforeClass
	public static void initialize() throws Exception{
	banco = new Banco();
	}
	
	
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
	
	public PruebaPersonaJuridica() throws ExceptionCuitNoValido{
		asociacionDeOrtopedia = new PersonaJuridica("Asociación de Ortopedia y Traumatología del Este de Río Negro y Carmen de Patagones", 
				cuit, domicilio1, telefono, true, fechaDelContratoSocial);
		
		asociacion2 = new PersonaJuridica("Asociación de Piscicultura", 
				cuit, domicilio1, telefono, false, fechaDelContratoSocial2, otrosDatos);
		
	}

	/*
	 * Probar un CUIT con valor 0
	 * post: debe arrojar ExceptionCuitNoValido
	 */
	@Test (expected = ExceptionCuitNoValido.class )
	public void testCuit0ArrojaExceptionCuitInvalido() throws ExceptionCuitNoValido {
		asociacion2 = new PersonaJuridica("Asociación Inválida", 0, domicilio1, telefono, false, fechaDelContratoSocial2, otrosDatos);
	}
	
	/*
	 * Invariante: el CUIT consta de un total de once (11) cifras
	 * Probar un CUIT con menos de 11 dígitos.
	 * post: debe arrojar ExceptionCuitNoValido
	 */
	@Test (expected = ExceptionCuitNoValido.class )
	public void testCuitNegativoArrojaExceptionCuitInvalido() throws ExceptionCuitNoValido {
		asociacion2 = new PersonaJuridica("Asociación Inválida", 302229, domicilio1, telefono, false, fechaDelContratoSocial2, otrosDatos);
	}
	
	/*
	 * Probar un CUIT de Persona Física en una Persona Jurídica
	 * post: Si el número de Cuit no corresponde, debe arrojar ExceptionCuitNoValido
	 */
	@Test (expected = ExceptionCuitNoValido.class )
	public void testCuitPersonaFisicaEnPersonaJuridicaArrojaExceptionCuitInvalido() throws ExceptionCuitNoValido {
		long cuitPersonaFisica = 20225333439L;
		asociacion2 = new PersonaJuridica("Asociación Inválida", cuitPersonaFisica, domicilio1, telefono, false, fechaDelContratoSocial2, otrosDatos);
	}
	
		
	/*
	 * Asociación HABILITADA creada con datos válidos.
	 * post: isEnabled() debe devolver true
	 */
	@Test
	public void testAsociacionHabilitada() {
		
		Assert.assertTrue(asociacionDeOrtopedia.isEnabled());
	}
	
	/*
	 * Asociación creada con datos válidos.
	 * post: constatar la fecha del contrato social
	 */
	@Test
	public void testFechaContratoSocial() {
		
		String resultado =  asociacionDeOrtopedia.getFechaDelContratoSocial();
		Assert.assertEquals(fechaDelContratoSocial, resultado);
	}
	
	@Test
	public void testDesactivar() {
		asociacionDeOrtopedia.desactivar();
		Assert.assertFalse(asociacionDeOrtopedia.isEnabled());
	}
	
	@Test
	public void testNoHabilitado() {
		Assert.assertFalse(asociacion2.isEnabled());
	}
	
	@Test
	public void testactivar() {
		asociacion2.activar();
		Assert.assertTrue(asociacion2.isEnabled());
	}
	
	/*
	 * Asociación creada sin Otros datos.
	 * post: constatar que los cargue y devuelva.
	 */	
	@Test
	public void testSetOtrosDatos() {
		
		String otrosDatosADO = "Tiene sede en Tucumán";
		asociacionDeOrtopedia.setOtrosDatos(otrosDatosADO);
		String resultado =  asociacionDeOrtopedia.getOtrosDatos();
		Assert.assertEquals(otrosDatosADO, resultado);
	}
	
	/*
	 * Asociación creada con Otros datos.
	 * post: constatar que los devuelva.
	 */	
	@Test
	public void testGetOtrosDatos() {
		String resultado =  asociacion2.getOtrosDatos();
		Assert.assertEquals(otrosDatos, resultado);
	}
	
	/*
	 * Asociación creada sin Otros datos.
	 * post: constatar que devuelva Null
	 */	
	@Test
	public void testAsociacionCreadaSinOtrosDatosGetOtrosDatosDevuelveNull() {
		String resultado =  asociacionDeOrtopedia.getOtrosDatos();
		
		Assert.assertEquals(null, resultado);
	}

}
