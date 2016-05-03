package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clases.Banco;
import clases.CajaDeAhorroEnPesos;
import clases.Cliente;
import clases.CuentaEspecial;
import clases.Domicilio;
import clases.GestorDeClientes;
import clases.GestorDeCuentas;
import clases.PersonaFisica;
import clases.ProcesadorBatch;
import clases.Ventanilla;
import excepciones.ArrayTitularesException;
import excepciones.ExceptionCuitNoEncontrado;
import excepciones.MontoException;
import excepciones.MontoTasaDeInteresException;
import excepciones.SaldoNegativoException;
import excepciones.TransaccionException;

public class PruebaProcesadorBatch {
	
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
		PersonaFisica persona1 = new PersonaFisica("Jebus", 20100000015L, domicilio, 4433222, true, "DNI", 31932422, "Carpintero", "soltero");
		titular.add(persona1);
		
		
	}
	
	//Ver txt creado mantenimientos
	@Test
	public void probarQueCobraCosto() throws TransaccionException, MontoException, ExceptionCuitNoEncontrado, SaldoNegativoException, ArrayTitularesException, MontoTasaDeInteresException{
		CajaDeAhorroEnPesos caPesos = new CajaDeAhorroEnPesos(100, titular, 0.5);
		gestorCuentas.abrirCajaDeAhorroEnPesos(caPesos);
		ProcesadorBatch proc =  new ProcesadorBatch();
		proc.correBatchParaTest();
		Assert.assertEquals(caPesos.getSaldo(), 70.5,0.1);
	}
	
	//Ver txt creado errores
	@Test
	public void probarQueNoCobraCostoPorFaltaDeFondos() throws TransaccionException, MontoException, ExceptionCuitNoEncontrado, SaldoNegativoException, ArrayTitularesException, MontoTasaDeInteresException{
		CajaDeAhorroEnPesos caPesos = new CajaDeAhorroEnPesos(1.5, titular, 0.5);
		gestorCuentas.abrirCajaDeAhorroEnPesos(caPesos);
		ProcesadorBatch proc =  new ProcesadorBatch();
		proc.correBatchParaTest();
		Assert.assertEquals(caPesos.getSaldo(), 1.5,0.1);
	}
	
}
