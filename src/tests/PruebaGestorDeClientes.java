package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clases.*;
import excepciones.ExceptionCuitNoEncontrado;
import excepciones.ExceptionCuitNoValido;
import excepciones.ExceptionNumeroDeDocumentoNoValido;

public class PruebaGestorDeClientes {
	
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
	long cuit = 20228833449L;
	long cuit2 = 30228833449L;//HabrÃ­a que validar el cuit???
	int telefono = 48329944;
	boolean habilitado = true;
	String tipoDeDocumento = "PASAPORTE";
	int numeroDeDocumento = 22883344;
	String profesion = "Horticultor";
	String fechaContratoSocial = "15/5/2013";	
	String otrosDatos = "Otros Datos";
	
	
	

	/*
	 * pre: el cliente es válido
	 * post: el cliente es dado de alta en el sistema
	 */
    
	@Test
	public void testAltaClienteValido() throws ExceptionCuitNoValido, ExceptionNumeroDeDocumentoNoValido {
		PersonaFisica pf1 = new PersonaFisica(nombre, cuit, domicilio, telefono, tipoDeDocumento, numeroDeDocumento, profesion);
		gestorClientes.alta(pf1);
		Assert.assertTrue(gestorClientes.esClienteExistente(pf1));
	}
	
	/*
	 * Post: excepción porque los dos clientes tienen el mismo CUIT
	 */
	@Test (expected = ExceptionCuitNoValido.class)
	public void testDosClientesConMismoCuitException() throws ExceptionCuitNoValido {
		PersonaJuridica pj1 = new PersonaJuridica(nombre, cuit, domicilio, telefono, fechaContratoSocial);
		gestorClientes.alta(pj1);
		gestorClientes.alta(pj1);
	}
	
	/*
	 * Post: excepción porque el CUIT supera al máximo válido
	 */
	@Test (expected = ExceptionCuitNoValido.class)
	public void testClienteConCuitDemasiadoAltoException() throws ExceptionCuitNoValido {
		PersonaJuridica asociacion3 = new PersonaJuridica("Asociacion Inválida", 40225446579L, domicilio, telefono, fechaContratoSocial);
	}
	
	/*
	 * Post: excepción porque el CUIT es inferior al mínimo válido
	 */
	@Test (expected = ExceptionCuitNoValido.class)
	public void testClienteConCuitDemasiadoBajoException() throws ExceptionCuitNoValido {
		PersonaJuridica asociacion3 = new PersonaJuridica("Otra Asociacion Inválida", 925446579L, domicilio, telefono, fechaContratoSocial);
	}
	
	/*
	 * Pre: no hay ningún cliente dado de Alta
	 * post: esClienteExistente() devuelve false para cualquier consulta
	 */
	
	@Test 
	public void testEsClienteExistenteFalse() throws Exception {
		Cliente pj4 = new PersonaFisica(nombre, 20228833899L, domicilio, telefono, habilitado, tipoDeDocumento, numeroDeDocumento, profesion, "Viudo");
		Assert.assertFalse(gestorClientes.esClienteExistente(pj4));
	}
	
	
	/*
	 *  El cliente es dado de alta en el sistema y luego dado de baja
	 *  Post: asociacionDeOrtopedia.isEnabled() == false
	 * 
	 */
	@Test
	public void testActivarYDesactivarPersonaFisica() throws ExceptionCuitNoValido, ExceptionNumeroDeDocumentoNoValido, ExceptionCuitNoEncontrado {
		Cliente pj4 = new PersonaJuridica(nombre, 30228833229L, domicilio, telefono, habilitado, fechaContratoSocial, otrosDatos);
		gestorClientes.alta(pj4);
		Assert.assertTrue(pj4.isEnabled());
		gestorClientes.baja(pj4);
		Assert.assertFalse(pj4.isEnabled());
	}
	
	/*
	 *  Test buscarConCuit() un cliente existente, devuelve ese cliente.
	 * 
	 */
	@Test
	public void testBuscarClienteExistenteConCuit() throws ExceptionCuitNoValido, ExceptionCuitNoEncontrado, ExceptionNumeroDeDocumentoNoValido {
		Cliente pj4 = new PersonaFisica(nombre, 20228833450L, domicilio, telefono, fechaContratoSocial, numeroDeDocumento, fechaContratoSocial);
		gestorClientes.alta(pj4);
		Assert.assertEquals(pj4, gestorClientes.buscarConCuit(20228833450L));
	}
	
	/*
	 *  Test buscarConCuit() un cliente inexistente, lanza Exception.
	 * 
	 */
	@Test (expected = ExceptionCuitNoEncontrado.class)
	public void testBuscarClienteNoExistenteConCuit() throws ExceptionCuitNoValido, ExceptionCuitNoEncontrado {
		Long cuit3 = 20228883449L;
		gestorClientes.buscarConCuit(cuit3);
	}
	
	/*
	 * testear activar()
	 */
	@Test 
	public void testActivar() throws ExceptionCuitNoValido {
		Cliente pj4 = new PersonaJuridica(nombre, cuit2, domicilio, telefono, fechaContratoSocial);
		gestorClientes.alta(pj4 );
		Assert.assertTrue(pj4.isEnabled());
	}
	
}/*@Test
	public void testStringToStringPersonaFisica() throws ExceptionCuitNoValido, ExceptionNumeroDeDocumentoNoValido {
		Cliente susanita = new PersonaFisica(nombre, 20228833450L, domicilio, telefono, fechaContratoSocial, numeroDeDocumento, fechaContratoSocial);
		gestorClientes.alta(susanita);
		String s = susanita.toString();
		Assert.assertEquals("Nombre y Apellido: Susana Rodríguez CUIT: 27332457539 Domicilio: Dirección: Calle 11 1351 Código Postal: 1900 Localidad: La Plata Provincia: Buenos Aires Telefono: 4217804 Cliente activo. Tipo de documento: Libreta Cívica Número de documento: 9456234 Profesión: Radióloga Estado civil: Soltero/a Cónyugue: ", s);
	}*/
/*@Test
	public void testSetDomicilioPersonaJuridica() throws ExceptionCuitNoValido {
		gestorDeClientes.alta(asociacion3);
		//Cambiar Domicilio
		asociacion3.setDomicilio(domicilio1);
		Assert.assertEquals(domicilio1, asociacion3.getDomicilio());
		}*/

/*
 * Probar el toString para PersonaJuridica
 *//*
@Test
public void testStringToStringPersonaJuridica() throws ExceptionCuitNoValido {
	gestorDeClientes.alta(asociacion3);
	String s = asociacion3.toString();
	Assert.assertEquals("Razón Social: APA CUIT: 30846846574 Domicilio: Dirección: Calle 11 1351 Código Postal: 1900 Localidad: La Plata Provincia: Buenos Aires Telefono: 4256379 Cliente habilitado. Fecha del contrato social: 15/5/2013", s);
}*/
/*
 * testear cambio de telefono
 *//*
@Test
public void testSetTelefonoPersonaJuridica() throws ExceptionCuitNoValido {
	gestorDeClientes.alta(asociacion3);
	//Cambiar Teléfono
	asociacion3.setTelefono(48223456);
	Assert.assertEquals(48223456, asociacion3.getTelefono());
	}
/*
 * testear agregar otros datos
 */
/*
@Test
public void testSetOtrosDatosPersonaJuridica() throws ExceptionCuitNoValido {
	gestorDeClientes.alta(asociacion3);
	//Agregar otros datos
	String datosNuevos = "Sitio web de la Asociación Psicoanalítica Argentina: https://www.apa.org.ar/";
	asociacion3.setOtrosDatos(datosNuevos);
	Assert.assertEquals(datosNuevos, asociacion3.getOtrosDatos());
	}
*/
