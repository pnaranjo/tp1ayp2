package tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clases.*;
import excepciones.ExceptionCuitNoEncontrado;
import excepciones.ExceptionCuitNoValido;
import excepciones.ExceptionNumeroDeDocumentoNoValido;
import excepciones.MontoException;


public class PruebaGestorDeClientes {
	
	
	static Banco banco;
	
    @BeforeClass public static void initialize() throws MontoException {
    	 banco = new Banco();
     }

	GestorDeClientes gestorDeClientes = new GestorDeClientes();
	
	String codigoPostal1 = "T4000IJN";
	String direccion = "Buenos Aires 672";
	String localidad = "San Miguel de Tucumán";
	String provincia = "Tucumán";
	int telefono = 4256379;
	String fechaDelContratoSocial = "14 de noviembre de 1953";
	long cuit = 30708468483L;
	long cuit3 = 30846846574L;
	Domicilio domicilio1 = new Domicilio(direccion, codigoPostal1, localidad, provincia);	
	String otrosDatos = "Certificado en trámite";
	String fechaDelContratoSocial2 = "15/5/2013";
	PersonaJuridica asociacionDeOrtopedia;
	PersonaJuridica asociacion2;
	PersonaJuridica asociacion3;
	Domicilio domicilio2 = new Domicilio("Calle 11 1351", "1900", "La Plata", "Buenos Aires");
	PersonaFisica susanita;
	
	public PruebaGestorDeClientes() throws ExceptionCuitNoValido, ExceptionNumeroDeDocumentoNoValido{
		asociacionDeOrtopedia = new PersonaJuridica("Asociación de Ortopedia y Traumatología del Este de Río Negro y Carmen de Patagones", 
				cuit, domicilio1, telefono, true, fechaDelContratoSocial);
		asociacion2 = new PersonaJuridica("Asociación de Piscicultura", 
				cuit, domicilio1, telefono, false, fechaDelContratoSocial2, otrosDatos);
		asociacion3 = new PersonaJuridica("APA", cuit3, domicilio2, telefono, fechaDelContratoSocial2);
		 susanita = new PersonaFisica("Susana Rodríguez", 27332457539L, domicilio2, 4217804, "Libreta Cívica", 9456234, "Radióloga");

	}

	/*
	 * pre: el cliente es válido
	 * post: el cliente es dado de alta en el sistema
	 */
	@Test
	public void testAltaClienteValido() throws ExceptionCuitNoValido, MontoException {
   	 banco = new Banco();

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
	}
	
	/*
	 * Post: excepción porque el CUIT supera al máximo válido
	 */
	@Test (expected = ExceptionCuitNoValido.class)
	public void testClienteConCuitDemasiadoAltoException() throws ExceptionCuitNoValido {
		asociacion3 = new PersonaJuridica("Asociacion Inválida", 40225446579L, domicilio2, telefono, fechaDelContratoSocial2);
	}
	
	/*
	 * Post: excepción porque el CUIT es inferior al mínimo válido
	 */
	@Test (expected = ExceptionCuitNoValido.class)
	public void testClienteConCuitDemasiadoBajoException() throws ExceptionCuitNoValido {
		asociacion3 = new PersonaJuridica("Otra Asociacion Inválida", 925446579L, domicilio2, telefono, fechaDelContratoSocial2);
	}
	
	/*
	 * Pre: no hay ningún cliente dado de Alta
	 * post: esClienteExistente() devuelve false para cualquier consulta
	 */
	@Test 
	public void testEsClienteExistenteFalse() throws ExceptionCuitNoValido, ExceptionCuitNoEncontrado {
		Assert.assertFalse(gestorDeClientes.esClienteExistente(asociacion2));
	}
	
	
	/*
	 *  El cliente es dado de alta en el sistema y luego dado de baja
	 *  Post: asociacionDeOrtopedia.isEnabled() == false
	 * 
	 */
	@Test
	public void testAltaYBajaCliente() throws ExceptionCuitNoValido, MontoException {
	   	 banco = new Banco();

		gestorDeClientes.alta(asociacionDeOrtopedia);
		gestorDeClientes.baja(asociacionDeOrtopedia);
		Assert.assertFalse(asociacionDeOrtopedia.isEnabled());
	}
	
	/*
	 *  Test buscarConCuit() un cliente existente, devuelve ese cliente.
	 * 
	 */
	@Test
	public void testBuscarClienteExistenteConCuit() throws ExceptionCuitNoValido, ExceptionCuitNoEncontrado, MontoException {
	   	 banco = new Banco();

		gestorDeClientes.alta(asociacionDeOrtopedia);
		Assert.assertEquals(asociacionDeOrtopedia, gestorDeClientes.buscarConCuit(cuit));
	}
	
	/*
	 *  Test buscarConCuit() un cliente inexistente, lanza Exception.
	 * 
	 */
	@Test (expected = ExceptionCuitNoEncontrado.class)
	public void testBuscarClienteNoExistenteConCuit() throws ExceptionCuitNoValido, ExceptionCuitNoEncontrado {
		gestorDeClientes.buscarConCuit(cuit);
	}
	
	

	/*
	 * pre: el cliente es válido
	 * post: el cliente es dado de alta en el sistema
	 */
	@Test
	public void testStringToStringPersonaFisica() throws ExceptionCuitNoValido {
		gestorDeClientes.alta(susanita);
		String s = susanita.toString();
		Assert.assertEquals("Nombre y Apellido: Susana Rodríguez CUIT: 27332457539 Domicilio: Dirección: Calle 11 1351 Código Postal: 1900 Localidad: La Plata Provincia: Buenos Aires Telefono: 4217804 Cliente activo. Tipo de documento: Libreta Cívica Número de documento: 9456234 Profesión: Radióloga Estado civil: Soltero/a Cónyugue: ", s);
	}
	
	/*
	 * Probar el toString para PersonaJuridica
	 */
	@Test
	public void testStringToStringPersonaJuridica() throws ExceptionCuitNoValido, MontoException {
	   	 banco = new Banco();

		gestorDeClientes.alta(asociacion3);
		String s = asociacion3.toString();
		Assert.assertEquals("Razón Social: APA CUIT: 30846846574 Domicilio: Dirección: Calle 11 1351 Código Postal: 1900 Localidad: La Plata Provincia: Buenos Aires Telefono: 4256379 Cliente habilitado. Fecha del contrato social: 15/5/2013", s);
	}
	
	/*
	 * testear cambio de domicilio
	 */
	@Test
	public void testSetDomicilioPersonaJuridica() throws ExceptionCuitNoValido {
		gestorDeClientes.alta(asociacion3);
		//Cambiar Domicilio
		asociacion3.setDomicilio(domicilio1);
		Assert.assertEquals(domicilio1, asociacion3.getDomicilio());
		}
	
	/*
	 * testear cambio de telefono
	 */
	@Test
	public void testSetTelefonoPersonaJuridica() throws ExceptionCuitNoValido, MontoException {
	   	 banco = new Banco();

		gestorDeClientes.alta(asociacion3);
		//Cambiar Teléfono
		asociacion3.setTelefono(48223456);
		Assert.assertEquals(48223456, asociacion3.getTelefono());
		}
	
	/*
	 * testear agregar otros datos
	 */
	@Test
	public void testSetOtrosDatosPersonaJuridica() throws ExceptionCuitNoValido, MontoException {
	   	 banco = new Banco();

		gestorDeClientes.alta(asociacion3);
		//Agregar otros datos
		String datosNuevos = "Sitio web de la Asociación Psicoanalítica Argentina: https://www.apa.org.ar/";
		asociacion3.setOtrosDatos(datosNuevos);
		Assert.assertEquals(datosNuevos, asociacion3.getOtrosDatos());
		}
	
	/*
	 * testear desactivar() 
	 */
	@Test
	public void testDesactivarPersonaJuridica() throws ExceptionCuitNoValido {
		gestorDeClientes.alta(asociacion3);
		//Desactivar
		asociacion3.desactivar();
		Assert.assertFalse(asociacion3.isEnabled());
		}
	
	/*
	 * testear activar()
	 */
	@Test 
	public void testactivar() throws ExceptionCuitNoValido {
		asociacion2 = new PersonaJuridica("Asociación de Piscicultura", 
				cuit, domicilio1, telefono, false, fechaDelContratoSocial2, otrosDatos);
		gestorDeClientes.alta(asociacion2);
		Assert.assertFalse(asociacion2.isEnabled());
		asociacion2.activar();
		Assert.assertTrue(asociacion2.isEnabled());
	}
	
}
