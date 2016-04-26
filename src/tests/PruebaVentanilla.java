package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import clases.Banco;
import clases.CajaDeAhorroEnPesos;
import clases.Domicilio;
import clases.GestorDeClientes;
import clases.GestorDeCuentas;
import clases.PersonaFisica;
import clases.Ventanilla;

public class PruebaVentanilla {
	
	GestorDeClientes gestorClientes = new GestorDeClientes();
	GestorDeCuentas gestorCuentas = new GestorDeCuentas();
	Ventanilla ventanilla = new Ventanilla();
	Banco banco = new Banco();
	ArrayList<PersonaFisica> titular = new ArrayList<>();
	Domicilio domicilio = new Domicilio("Av. Siempre Viva 123", "1676", "Caseros", "Caseros City");
	
	
	@Test
	public void testDepositoCajaAhorroEnPesos() throws Exception{
		
		PersonaFisica persona1 = new PersonaFisica("Jebus", 20100000015L, domicilio, 4433222, true, "DNI", 31932422, "Carpintero", "soltero");
		titular.add(persona1);
		gestorClientes.alta(persona1);
		
		CajaDeAhorroEnPesos caPesos = new CajaDeAhorroEnPesos(100, titular, 0.5, CajaDeAhorroEnPesos.costoDeMantenimientoPesos);
		gestorCuentas.abrirCajaDeAhorroEnPesos(caPesos);
		ventanilla.depositoEnEfectivo(caPesos.getCbu(), 100, "PESOS");
		Assert.assertEquals(200, caPesos.getSaldo(), 0.0);
	}
	
	@Test
	public void testDepositoCajaAhorroEnDolares(){
		
		
	}
	
	@Test
	public void testDepositoCuentaCorriente(){
		
		
	}

}
