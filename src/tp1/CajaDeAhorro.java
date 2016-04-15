package tp1;

import java.util.ArrayList;


public abstract class CajaDeAhorro extends CuentaComun {
    private final ArrayList<PersonaFisica> titulares;
    private final double tasaDeInteres;
	
    public CajaDeAhorro(double saldo, ArrayList<PersonaFisica> titulares, double tasaDeInteres) {
        super(saldo);
    	this.tasaDeInteres = tasaDeInteres;
    	this.titulares = titulares;
    }
        
    public double convertirPesosADolares(double montoEnPesos){
       return montoEnPesos*OperadorBancario.tipoDeCambioVigente;   
    }
        
    public double convertirDolaresAPesos(double montoEnDolares){
       return montoEnDolares/OperadorBancario.tipoDeCambioVigente;
    }

    public ArrayList<PersonaFisica> getTitulares(){
    	return this.titulares;
    }
    
    public double getTasaDeInteres(){
    	return this.tasaDeInteres;
    }
    
    public Transaccion debitar( String tipoDeMovimiento, double monto, String motivo,  String observaciones){
    	/*falta verificar que el saldo no quede negativo*/
    	this.saldo =- monto;
    	Transaccion transaccion = new Transaccion(tipoDeMovimiento, monto, motivo, observaciones);
    	this.historial.add(transaccion);
		return transaccion; 
    }
    public Transaccion debitar( String tipoDeMovimiento, double monto, String motivo){
    	/*falta verificar que el saldo no quede negativo*/
    	this.saldo =- monto;
    	Transaccion transaccion = new Transaccion(tipoDeMovimiento, monto, motivo);
    	this.historial.add(transaccion);
		return transaccion; 
    }
    
    
}
