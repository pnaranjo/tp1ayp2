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
	
	


}
