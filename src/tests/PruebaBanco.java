package tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clases.*;
import excepciones.MontoException;

public class PruebaBanco {
	
	static Banco banco;

	@BeforeClass
	public static void initialize() throws Exception{
		banco = new Banco();
	}
	

	@Test
	public void bancoCreadoSinParametrosTieneSaldoDeMantenimiento0() throws MontoException {
		Assert.assertEquals(0, banco.getSaldoMantenimiento(), 0.01);
	}
	
	@Test
	public void bancoCreadoSinParametrosTieneSaldoRetenciones0() throws MontoException {
		Assert.assertEquals(0, banco.getSaldoRetenciones(), 0.01);
	}
	
	@Test
	public void bancoCreadoSinParametrosTieneCostoDeMantenimientoPesos30() throws MontoException {
		Assert.assertEquals(30, Banco.getCostoDeMantenimientoPesos(), 0.01);		
	}
	
	@Test (expected = MontoException.class)
	public void setCostoDeMantenimientoDolares0GeneraExcepcion() throws MontoException {
		banco.setCostoDeMantenimientoDolares(0);		
	}
	
	@Test (expected = MontoException.class)
	public void setCostoDeMantenimientoPesos0GeneraExcepcion() throws MontoException {
		banco.setCostoDeMantenimientoPesos(0);		
	}
	
	@Test (expected = MontoException.class)
	public void setTipoDeCambioVigente0GeneraExcepcion() throws MontoException {
		banco.setTipoDeCambioVigente(0.0);
	}

	@Test
	public void setCostoDeMantenimientoDolares() throws MontoException {
		banco.setCostoDeMantenimientoDolares(3.5);
		Assert.assertEquals(3.5, Banco.getCostoDeMantenimientoDolares(), 0.01);		
	}
	
	@Test
	public void setCostoDeMantenimientoPesos() throws MontoException {
		banco.setCostoDeMantenimientoPesos(13.5);
		Assert.assertEquals(13.5, Banco.getCostoDeMantenimientoPesos(), 0.01);		
	}
	
	@Test
	public void setCostoDeManten() throws MontoException {
		banco.setTipoDeCambioVigente(14.94);
		Assert.assertEquals(14.94, Banco.getTipoDeCambioVigente(), 0.01);		
	}
}
