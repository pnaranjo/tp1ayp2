package tests;

import java.math.BigDecimal;

import org.junit.Test;

import clases.CajaDeAhorroEnPesos;
import clases.Domicilio;
import clases.GestorDeClientes;
import clases.PersonaFisica;

public class PruebaVentanilla {
	
	GestorDeClientes gestorClientes;
	
	
	@Test
	public void testDepositoCajaAhorroEnPesos(){
		Domicilio domicilio = new Domicilio("Av. Siempre Viva 123", "1676", "Sprinfield", "Kansas");
		//PersonaFisica persona1 = new PersonaFisica("Jebus", 20100000015L, domicilio, 4433222, true, "DNI", 31932422, "Carpintero", "soltero");
		//gestorClientes.alta(persona1);
		//CajaDeAhorroEnPesos caPesos = new CajaDeAhorroEnPesos(100, titulares, 0.5, CajaDeAhorroEnPesos.costoDeMantenimientoPesos);
	}
	
	@Test
	public void testDepositoCajaAhorroEnDolares(){
		
		
	}
	
	@Test
	public void testDepositoCuentaCorriente(){
		
		
	}

}
