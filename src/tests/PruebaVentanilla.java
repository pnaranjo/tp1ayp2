package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import clases.CajaDeAhorroEnPesos;
import clases.GestorDeClientes;
import clases.GestorDeCuentas;
import clases.PersonaFisica;
import clases.Ventanilla;

public class PruebaVentanilla {
	
	GestorDeClientes gestorClientes;
	GestorDeCuentas gestorCuentas;
	Ventanilla ventanilla;
	
	@Test
	public void testDepositoCajaAhorroEnPesos(){
		//Domicilio domicilio = new Domicilio("Av. Siempre Viva 123", "1676", "Sprinfield", "Kansas");
		//PersonaFisica persona1 = new PersonaFisica("Jebus", 20100000015L, domicilio, 4433222, true, "DNI", 31932422, "Carpintero", "soltero");
		//gestorClientes.alta(persona1);
		ArrayList<PersonaFisica> titular = null;
		
		CajaDeAhorroEnPesos caPesos = new CajaDeAhorroEnPesos(100, titular, 0.5, CajaDeAhorroEnPesos.costoDeMantenimientoPesos);
		gestorCuentas.abrirCajaDeAhorroEnPesos(caPesos);
		ventanilla.depositoEnEfectivo(caPesos.getCbu(), 100, "PESOS");
		Assert.assertEquals(200, caPesos.getSaldo());
	}
	
	@Test
	public void testDepositoCajaAhorroEnDolares(){
		
		
	}
	
	@Test
	public void testDepositoCuentaCorriente(){
		
		
	}

}
