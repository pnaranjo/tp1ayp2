package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clases.Banco;
import clases.CajaDeAhorroEnDolares;
import clases.CajaDeAhorroEnPesos;
import clases.Cliente;
import clases.Domicilio;
import clases.GestorDeClientes;
import clases.GestorDeCuentas;
import clases.PersonaFisica;
import clases.Ventanilla;

public class PruebaVentanilla {
	
	static Banco banco;
	static Ventanilla ventanilla;
	static GestorDeClientes gestorClientes;
	static GestorDeCuentas gestorCuentas;
	static ArrayList<PersonaFisica> titular; 
	static ArrayList<Cliente> titulares; 
	static Domicilio domicilio;
		
	@BeforeClass
	public static void initialize() throws Exception{
		banco = new Banco();	
		gestorClientes = new GestorDeClientes();
		gestorCuentas = new GestorDeCuentas();
		ventanilla = new Ventanilla();
		titular = new ArrayList<>();
		titulares = new ArrayList<>();
		domicilio = new Domicilio("Av. Siempre Viva 123", "1676", "Caseros", "Caseros City");
	}
	
	
	@Test
	public void testDepositoCajaAhorroEnPesos() throws Exception{
		
		PersonaFisica persona1 = new PersonaFisica("Jebus", 20100000015L, domicilio, 4433222, true, "DNI", 31932422, "Carpintero", "soltero");
		titular.add(persona1);
		gestorClientes.alta(persona1);
		
		CajaDeAhorroEnPesos caPesos = new CajaDeAhorroEnPesos(100, titular, 0.5);
		gestorCuentas.abrirCajaDeAhorroEnPesos(caPesos);
		ventanilla.depositoEnEfectivo(caPesos.getCbu(), 100, "PESOS");
		Assert.assertEquals(200, caPesos.getSaldo(), 0.0);
	}
	
	@Test
	public void testDepositoCajaAhorroEnDolares() throws Exception{
		PersonaFisica persona1 = new PersonaFisica("Jebus", 20100000125L, domicilio, 4433222, true, "DNI", 31932422, "Carpintero", "soltero");
		titular.add(persona1);
		gestorClientes.alta(persona1);
		
		CajaDeAhorroEnDolares caDolares = new CajaDeAhorroEnDolares(150, titular, 1);
		gestorCuentas.abrirCajaDeAhorroEnDolares(caDolares);
		ventanilla.depositoEnEfectivo(caDolares.getCbu(), 10.5, "DOLARES");
		Assert.assertEquals(160.5, caDolares.getSaldo(), 0.0);
	}
	
	@Test
	public void testDepositoCuentaCorriente(){
		
		
	}

}
