package pruebas_TP1;

import org.junit.Assert;
import tp1.*;
import org.junit.Test;

public class PruebaPersonaJuridica {

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
	
	PersonaJuridica asociacionDeOrtopedia = new PersonaJuridica("Asociación de Ortopedia y Traumatología del Este de Río Negro y Carmen de Patagones", 
			cuit, domicilio1, telefono, true, fechaDelContratoSocial);
	
	PersonaJuridica asociacion2 = new PersonaJuridica("Asociación de Piscicultura", 
			cuit, domicilio1, telefono, false, fechaDelContratoSocial2, otrosDatos);
	
	@Test
	public void testAsociacionHabilitada() {
		Assert.assertTrue(asociacionDeOrtopedia.isEnabled());
	}
	
	@Test
	public void testFechaContratoSocial() {
		
		String resultado =  asociacionDeOrtopedia.getFechaDelContratoSocial();
		Assert.assertEquals(fechaDelContratoSocial, resultado);
	}
	
	@Test
	public void testSetOtrosDatos() {
		
		String otrosDatosADO = "Tiene sede en Tucumán";
		asociacionDeOrtopedia.setOtrosDatos(otrosDatosADO);
		String resultado =  asociacionDeOrtopedia.getOtrosDatos();
		Assert.assertEquals(otrosDatosADO, resultado);
	}
	
	@Test
	public void testDeshabilitar() {
		asociacionDeOrtopedia.deshabilitar();
		Assert.assertFalse(asociacionDeOrtopedia.isEnabled());
	}
	
	@Test
	public void testNoHabilitado() {
		Assert.assertFalse(asociacion2.isEnabled());
	}
	
	@Test
	public void testHabilitar() {
		asociacion2.habilitar();
		Assert.assertTrue(asociacion2.isEnabled());
	}
	
	@Test
	public void testOtrosDatos() {
		String resultado =  asociacion2.getOtrosDatos();
		Assert.assertEquals(otrosDatos, resultado);
	}
	
	
	
	

}
