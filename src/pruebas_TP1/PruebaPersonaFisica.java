package pruebas_TP1;

import org.junit.Assert;
import org.junit.Test;

import tp1.*;

public class PruebaPersonaFisica {
	
	
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
	
	/*
	 * Una PersonaFisica creada sin CONYUGUE ni ESTADO_CIVIL es "Soltero/a" por defecto.
	 */
	@Test
	public void personaFisicaSinConyugueEsSolteroPorDefault() {
		
		PersonaFisica persona1 = new PersonaFisica(nombre, cuit, domicilio1, telefono, tipoDeDocumento, numeroDeDocumento, profesion);
		Assert.assertEquals(persona1.getEstadoCivil(), "Soltero/a");		
	}


	@Test
	public void personaHabilitadaCasada() {
		PersonaFisica persona2 = new PersonaFisica(nombre, cuit, domicilio1, telefono, habilitado ,tipoDeDocumento, numeroDeDocumento, profesion, "Casado", "Romina Albornoz");
		Assert.assertTrue(persona2.isEnabled());		
	}
	
	@Test
	public void personaNoHabilitadaCasada() {
		habilitado = false;
		PersonaFisica persona2 = new PersonaFisica(nombre, cuit, domicilio1, telefono, habilitado ,tipoDeDocumento, numeroDeDocumento, profesion, "Casado", "Romina Albornoz");
		Assert.assertFalse(persona2.isEnabled());		
	}

	
	@Test
	public void personaHabilitadaViuda() throws Exception {
		PersonaFisica persona3 = new PersonaFisica(nombre, cuit, domicilio1, telefono, habilitado ,tipoDeDocumento, numeroDeDocumento, profesion, "Viudo");
		Assert.assertTrue(persona3.isEnabled());		
	}
	
	@Test
	public void personaNoHabilitadaViuda() throws Exception {
		boolean habilitado = false;
		PersonaFisica persona3 = new PersonaFisica(nombre, cuit, domicilio1, telefono, habilitado ,tipoDeDocumento, numeroDeDocumento, profesion, "Viudo");
		Assert.assertFalse(persona3.isEnabled());		
	}
		
	
	/*
	 * Tira una Excepción 
	 */
	@Test(expected = Exception.class)
	public void excepcionPersonaCasadaSinConyugue() throws Exception {
		
		new PersonaFisica(nombre, cuit, domicilio1, telefono, habilitado ,tipoDeDocumento, numeroDeDocumento, profesion, "Casado");
	}
	
	@Test
	public void pruebaToStringConPersonaFisicaCasado() {
		
		PersonaFisica persona2 = new PersonaFisica(nombre, cuit, domicilio1, telefono, habilitado ,tipoDeDocumento, numeroDeDocumento, profesion, "Casado", "Romina Albornoz");
		String resultado = persona2.toString();
		Assert.assertEquals(resultado, 
"Nombre y Apellido: Juan Perez CUIT: 20228833449 Domicilio: Dirección: Las Heras 2532 Código Postal: 1426 Localidad: Capital Provincia: CABA Telefono: 48329944 Cliente habilitado. Tipo de documento: PASAPORTE Número de documento: 22883344 Profesión: Horticultor Estado civil: Casado Cónyugue: Romina Albornoz"
);
		}
	
	@Test
	public void pruebaStringConPersonaNoHabilitadaViuda() throws Exception {
		boolean habilitado = false;
		PersonaFisica persona3 = new PersonaFisica(nombre, cuit, domicilio1, telefono, habilitado ,tipoDeDocumento, numeroDeDocumento, profesion, "Viudo");
		String resultado = persona3.toString();
		System.out.println(resultado);
		Assert.assertEquals(resultado, 
"Nombre y Apellido: Juan Perez CUIT: 20228833449 Domicilio: Dirección: Las Heras 2532 Código Postal: 1426 Localidad: Capital Provincia: CABA Telefono: 48329944 Cliente NO habilitado Tipo de documento: PASAPORTE Número de documento: 22883344 Profesión: Horticultor Estado civil: Viudo");
	
	}

}