package tests;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clases.Banco;
import clases.CajaDeAhorroEnDolares;
import clases.CajaDeAhorroEnPesos;
import clases.Cliente;
import clases.CuentaCorriente;
import clases.Domicilio;
import clases.GestorDeClientes;
import clases.GestorDeCuentas;
import clases.PersonaFisica;
import clases.PersonaJuridica;
import clases.Ventanilla;
import excepciones.ExceptionCbuNoEncontrado;

public class PruebaGestorDeCuentas {
	
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
	public void probarQueSeAbreUnaCajaDeAhorroEnPesos() throws Exception{
		PersonaFisica persona1 = new PersonaFisica("Jebus", 20100000015L, domicilio, 4433222, true, "DNI", 31932422, "Carpintero", "soltero");
		titular.add(persona1);
		//gestorClientes.alta(persona1);
		
		CajaDeAhorroEnPesos caPesos = new CajaDeAhorroEnPesos(100, titular, 0.5);
		gestorCuentas.abrirCajaDeAhorroEnPesos(caPesos);
		Assert.assertEquals(caPesos, Banco.getPortfolioDeCuentas().get(caPesos.getCbu()));
	}
	
	@Test
	public void probarQueSeAbreUnaCajaDeAhorroEnDolares() throws Exception{
		PersonaFisica persona1 = new PersonaFisica("Jebus", 20100000015L, domicilio, 4433222, true, "DNI", 31932422, "Carpintero", "soltero");
		titular.add(persona1);
		//gestorClientes.alta(persona1);
		
		CajaDeAhorroEnDolares caDolares = new CajaDeAhorroEnDolares(100, titular, 0.5);
		gestorCuentas.abrirCajaDeAhorroEnDolares(caDolares);
		Assert.assertEquals(caDolares, Banco.getPortfolioDeCuentas().get(caDolares.getCbu()));
	}
	
	@Test
	public void probarQueSeAbreUnaCuentaCorriente() throws Exception{
		PersonaJuridica personaJuridica1 = new PersonaJuridica("Jebus", 30100000015L, domicilio, 4433222, true, "DNI");
		titulares.add(personaJuridica1);
		gestorClientes.alta(personaJuridica1);
		
		CuentaCorriente cCorriente = new CuentaCorriente(10000,titulares,1000);
		gestorCuentas.abrirCuentaCorriente(cCorriente);
		Assert.assertEquals(cCorriente, Banco.getPortfolioDeCuentas().get(cCorriente.getCbu()));
	}
	
	@Test
	public void probarQueSeAbreUnaCuentaCorrienteConMasDeUnCliente() throws Exception{
		PersonaJuridica personaJuridica1 = new PersonaJuridica("Jebus", 30100000015L, domicilio, 4433222, true, "DNI");
		PersonaJuridica personaJuridica2 = new PersonaJuridica("Arnoldo", 30100000025L, domicilio, 4433222, true, "DNI");
		titulares.add(personaJuridica1);
		titulares.add(personaJuridica2);
		//gestorClientes.alta(personaJuridica1);
		gestorClientes.alta(personaJuridica2);
		
		CuentaCorriente cCorriente = new CuentaCorriente(10000,titulares,1000);
		gestorCuentas.abrirCuentaCorriente(cCorriente);
		Assert.assertEquals(cCorriente, Banco.getPortfolioDeCuentas().get(cCorriente.getCbu()));
	}
	
	@Test
	public void probarQueDeshabilitoUnacuenta() throws Exception{
		PersonaFisica persona1 = new PersonaFisica("Jebus", 20100000015L, domicilio, 4433222, true, "DNI", 31932422, "Carpintero", "soltero");
		titular.add(persona1);
		//gestorClientes.alta(persona1);
		
		CajaDeAhorroEnPesos caPesos = new CajaDeAhorroEnPesos(100, titular, 0.5);
		gestorCuentas.abrirCajaDeAhorroEnPesos(caPesos);
		gestorCuentas.inhablitarCuenta(caPesos.getCbu());
		CajaDeAhorroEnPesos ca = (CajaDeAhorroEnPesos) Banco.getPortfolioDeCuentas().get(caPesos.getCbu());
		Assert.assertEquals(false, ca.isEnabled());
	}
	
	@Test
	public void probarQueHabilitoUnacuentaDeshabilitada() throws Exception{
		PersonaFisica persona1 = new PersonaFisica("Jebus", 20100000015L, domicilio, 4433222, true, "DNI", 31932422, "Carpintero", "soltero");
		titular.add(persona1);
		gestorClientes.alta(persona1);
		
		CajaDeAhorroEnPesos caPesos = new CajaDeAhorroEnPesos(100, titular, 0.5);
		gestorCuentas.abrirCajaDeAhorroEnPesos(caPesos);
		gestorCuentas.inhablitarCuenta(caPesos.getCbu());
		gestorCuentas.hablitarCuenta(caPesos.getCbu());
		CajaDeAhorroEnPesos ca = (CajaDeAhorroEnPesos) Banco.getPortfolioDeCuentas().get(caPesos.getCbu());
		Assert.assertEquals(true, ca.isEnabled());
	}
	
	@Test
	public void probarQueNoEncuentraUnacuenta() throws Exception{
		PersonaFisica persona1 = new PersonaFisica("Jebus", 20100000015L, domicilio, 4433222, true, "DNI", 31932422, "Carpintero", "soltero");
		titular.add(persona1);
		//gestorClientes.alta(persona1);
		
		CajaDeAhorroEnPesos caPesos = new CajaDeAhorroEnPesos(100, titular, 0.5);
		gestorCuentas.abrirCajaDeAhorroEnPesos(caPesos);
		long cbu = 22222;
		Assert.assertEquals(false, Banco.getPortfolioDeCuentas().containsKey(cbu));
	}
	
	@Test (expected = ExceptionCbuNoEncontrado.class)
	public void probarQueNoEncuentraUnaCuentaEnHabilitar() throws Exception{
		PersonaFisica persona1 = new PersonaFisica("Jebus", 20100000015L, domicilio, 4433222, true, "DNI", 31932422, "Carpintero", "soltero");
		titular.add(persona1);
		//gestorClientes.alta(persona1);
		
		CajaDeAhorroEnPesos caPesos = new CajaDeAhorroEnPesos(100, titular, 0.5);
		gestorCuentas.abrirCajaDeAhorroEnPesos(caPesos);
		long cbu = 22222;
		gestorCuentas.hablitarCuenta(cbu);
	}
	
	@Test (expected = ExceptionCbuNoEncontrado.class)
	public void probarQueNoEncuentraUnaCuentaEnDeshabilitar() throws Exception{
		PersonaFisica persona1 = new PersonaFisica("Jebus", 20100000015L, domicilio, 4433222, true, "DNI", 31932422, "Carpintero", "soltero");
		titular.add(persona1);
		//gestorClientes.alta(persona1);
		
		CajaDeAhorroEnPesos caPesos = new CajaDeAhorroEnPesos(100, titular, 0.5);
		gestorCuentas.abrirCajaDeAhorroEnPesos(caPesos);
		long cbu = 22222;
		gestorCuentas.inhablitarCuenta(cbu);
	}
	
	
	
	

}
