package tests;

import org.junit.Test;
import org.junit.Assert;

import clases.*;


public class PruebaDomicilio {
	
	String codigoPostal1 = "1426";
	String direccion = "Las Heras 2532";
	String localidad = "Capital";
	String provincia = "CABA";

	@Test
	public void testCodigoPostal() {
		
		Domicilio domicilio1 = new Domicilio("Las Heras 2532", codigoPostal1, "Capital", "CABA");		
		String getCP = domicilio1.getCodigoPostal();
		Assert.assertEquals(codigoPostal1, getCP);
		}

	@Test
	public void testDireccion() {
		
		Domicilio domicilio1 = new Domicilio(direccion, codigoPostal1, localidad, provincia);		
		String getDir = domicilio1.getDireccion();		
		Assert.assertEquals(direccion, getDir);
		}
	
	@Test
	public void testLocalidad() {
		
		Domicilio domicilio1 = new Domicilio(direccion, codigoPostal1, localidad, provincia);		
		String resultado = domicilio1.getLocalidad();		
		Assert.assertEquals(localidad, resultado);
		}
	
	@Test
	public void testProvincia() {
		
		Domicilio domicilio1 = new Domicilio(direccion, codigoPostal1, localidad, provincia);		
		String resultado = domicilio1.getProvincia();		
		Assert.assertEquals(provincia, resultado);
		}
	
	@Test
	public void pruebaSetDireccionCambiaLaDireccion() {
		
		Domicilio domicilio1 = new Domicilio(direccion, codigoPostal1, localidad, provincia);
		String direccionNueva = "Av. de Mayo 555";
		domicilio1.setDireccion(direccionNueva);
		String resultado = domicilio1.getDireccion();		
		Assert.assertEquals(direccionNueva, resultado);
		}
	
	@Test
	public void pruebaSetCodigoPostal() {
		
		Domicilio domicilio1 = new Domicilio(direccion, codigoPostal1, localidad, provincia);
		String cpNuevo = "C1425ASO";
		domicilio1.setCodigoPostal(cpNuevo);
		Assert.assertEquals(cpNuevo, domicilio1.getCodigoPostal());
		}
	
	@Test
	public void pruebaSetLocalidad() {
		
		Domicilio domicilio1 = new Domicilio(direccion, codigoPostal1, localidad, provincia);
		String locNueva = "Capital Federal";
		domicilio1.setLocalidad(locNueva);
		Assert.assertEquals(locNueva, domicilio1.getLocalidad());
		}
	
	@Test
	public void pruebaSetProvincia() {
		
		Domicilio domicilio1 = new Domicilio(direccion, codigoPostal1, localidad, provincia);
		String provNueva = "Neuquen";
		domicilio1.setProvincia(provNueva);
		Assert.assertEquals(provNueva, domicilio1.getProvincia());
		}
	
	@Test
	public void pruebaToString() {
		
		Domicilio domicilio1 = new Domicilio(direccion, codigoPostal1, localidad, provincia);
		String resultado = domicilio1.toString();
		Assert.assertEquals(resultado, "Dirección: Las Heras 2532 Código Postal: 1426 Localidad: Capital Provincia: CABA");
		}

	
	
}
