package tests;

import org.junit.Assert;
import org.junit.Test;

import clases.*;
import excepciones.ExceptionCuitNoEncontrado;
import excepciones.ExceptionCuitNoValido;
import excepciones.ExceptionNumeroDeDocumentoNoValido;
import excepciones.MontoException;

public class PruebaBanco {
	
	static Banco galicia;

	@Test
	public void bancoCreadoSinParametrosTieneSaldoDeMantenimiento0() throws MontoException {
		
		galicia = new Banco();
		Assert.assertEquals(0, galicia.getSaldoMantenimiento(), 0.01);
				
	}
	

	@Test
	public void bancoCreadoSinParametrosTieneSaldoRetenciones0() throws MontoException {
		
		galicia = new Banco();
		Assert.assertEquals(0, galicia.getSaldoRetenciones(), 0.01);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void bancoCreadoSinParametrosTieneCostoDeMantenimientoDolares2() throws MontoException {
		
		galicia = new Banco();
		Assert.assertEquals(2, galicia.getCostoDeMantenimientoDolares(), 0.01);		
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void bancoCreadoSinParametrosTieneCostoDeMantenimientoPesos30() throws MontoException {
		
		galicia = new Banco();
		Assert.assertEquals(30, galicia.getCostoDeMantenimientoPesos(), 0.01);		
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void bancoCreadoSinParametrosTieneTipoDeCambioVigente15() throws MontoException {
		
		galicia = new Banco();
		Assert.assertEquals(15, galicia.getTipoDeCambioVigente(), 0.01);		
	}
	
	@Test (expected = MontoException.class)
	public void setCostoDeMantenimientoDolares0GeneraExcepcion() throws MontoException {
		
		galicia = new Banco();
		galicia.setCostoDeMantenimientoDolares(0);		
	}
	
	@Test (expected = MontoException.class)
	public void setCostoDeMantenimientoPesos0GeneraExcepcion() throws MontoException {
		
		galicia = new Banco();
		galicia.setCostoDeMantenimientoPesos(0);		
	}
	
	@Test (expected = MontoException.class)
	public void setTipoDeCambioVigente0GeneraExcepcion() throws MontoException {
		
		galicia = new Banco();
		galicia.setTipoDeCambioVigente(0.0);
	}


	
	@SuppressWarnings("static-access")
	@Test
	public void setCostoDeMantenimientoDolares() throws MontoException {
		
		galicia = new Banco();
		galicia.setCostoDeMantenimientoDolares(3.5);
		Assert.assertEquals(3.5, galicia.getCostoDeMantenimientoDolares(), 0.01);		
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void setCostoDeMantenimientoPesos() throws MontoException {
		
		galicia = new Banco();
		galicia.setCostoDeMantenimientoPesos(13.5);
		Assert.assertEquals(13.5, galicia.getCostoDeMantenimientoPesos(), 0.01);		
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void setCostoDeManten() throws MontoException {
		
		galicia = new Banco();
		galicia.setTipoDeCambioVigente(14.94);
		Assert.assertEquals(14.94, galicia.getTipoDeCambioVigente(), 0.01);		
	}
	
	

	
	
	
	
	
	


}
