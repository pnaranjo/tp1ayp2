package tests;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import clases.Banco;
import clases.Domicilio;
import clases.GestorDeClientes;
import clases.GestorDeCuentas;
import clases.PersonaFisica;
import clases.Transaccion;
import excepciones.MontoException;
import excepciones.TransaccionException;

public class PruebaTransaccion {
	static Banco banco;
	static GestorDeClientes gestorClientes;
	static GestorDeCuentas gestorCuentas;
	static ArrayList<PersonaFisica> titular; 
	static Domicilio domicilio;
	static Transaccion transaccion; 	
	static Transaccion transaccion2;
	@BeforeClass
	public static void initialize() throws Exception{
		banco = new Banco();	
		gestorClientes = new GestorDeClientes();
		gestorCuentas = new GestorDeCuentas();
		titular = new ArrayList<>();
		domicilio = new Domicilio("Av. Siempre Viva 123", "1676", "Caseros", "Caseros City");
		transaccion = new Transaccion("debitar", 200, "Extraccion por ventanilla");
		
	}
	
	String nombre = "Juan Perez";
	long cuit = 20228833449L; //HabrÃ­a que validar el cuit???
	int telefono = 48329944;
	boolean habilitado = true;
	String tipoDeDocumento = "PASAPORTE";
	int numeroDeDocumento = 22883344;
	String profesion = "Horticultor";
	
	
	/* Pre:Se agrega una transaccion al historial de transaccion que se encuentra vacio*/
	/* Post:Se confirma que el tama�an del historial sea uno.*/
	@Test
	public void testAgregarTransaccionAHistorial(){
		ArrayList<Transaccion> historial = new ArrayList<Transaccion>();
		historial.add(transaccion);
		Assert.assertEquals(1, historial.size());
	}
	
	/* Pre:Se crea transaccion con saldo negativo*/
	/* Post:Lanzara MontoException*/
	@Test(expected = MontoException.class)
	public void testCrearTransaccionConMontoNegativoError() throws TransaccionException, MontoException{
		transaccion2 = new Transaccion("debitar", -2, "Extraccion por ventanilla");
	}
	
	/* Pre:Se crea transaccion con movimiento no permitido*/
	/* Post:Lanzara TransaccionException*/
	@Test(expected = TransaccionException.class)
	public void testCrearTransaccionConMovimientoError() throws TransaccionException, MontoException{
		Transaccion transaccion3 = new Transaccion("Debitar", 200, "Extraccion por ventanilla");
	}
	
	/* Pre:Se crea transaccion con motivo = null*/
	/* Post:Lanzara TransaccionException*/
	@Test(expected = TransaccionException.class)
	public void testCrearTransaccionConMotivoError() throws TransaccionException, MontoException{
		String motivo = null;
		transaccion2 = new Transaccion("debitar", 200,motivo );
	}
	
	/* Pre:Se crea transaccion con saldo negativo*/
	/* Post:Lanzara MontoException*/
	@Test(expected = TransaccionException.class)
	public void testCrearTransaccionConObservacionError() throws TransaccionException, MontoException{
		String observacion = null;
		transaccion2 = new Transaccion("debitar", 200,"Deposito en pesos",observacion);
	}
	
	
	
	
	
}
