package clases;

import java.util.ArrayList;

public class CuentaCorriente extends CuentaComun {
    private final ArrayList<Cliente> titulares;
    private double montoSobreGiro, montoParaAbrirCuenta = 10000;
    static final double comision = 0.03;
    static long cbu;
            
    public CuentaCorriente(double saldo, ArrayList<Cliente> titulares,double montoSobreGiro1) {
        super(saldo);
        this.titulares = new ArrayList<Cliente>(titulares);         
        this.montoSobreGiro = montoSobreGiro1;
        enabled = false;
    }
    
    public CuentaCorriente(double saldo, ArrayList<Cliente> titulares,double montoSobreGiro,double montoDeposito) {
    	super(saldo);
        if(montoDeposito < montoParaAbrirCuenta){
            throw new Error(" El monto necesario para abrir cuenta debe ser mayor o igual a 10.000");
        }
        this.titulares = new ArrayList<Cliente>(titulares);                 
        this.montoSobreGiro = montoSobreGiro;
        cbu = super.getCbu();
        enabled = true;
    }
    
    
    public ArrayList<Cliente> getTitulares() {
		return titulares;
	}
    
    public double cobrarComision(double monto){
        return comision * monto;
    }

    public double getMontoSobreGiro() {
        return montoSobreGiro;
    }
    
    public double setMontoParaAbrirCuenta(double monto){
        return montoParaAbrirCuenta = monto;
    }

	@Override
	public Transaccion debitar(String tipoDeMovimiento, double monto, String motivo){
		return null;
		
		
		// TODO Auto-generated method stub
	
		//TODO debitar debería chequear que el monto sea Válido: menor que el Monto de Sobregiro.
		    	    	
		//TODO debitar debería decrementar el saldo
		    	
		//TODO debitar debería generar una Transaccion y agregarla al hostorial
		
	}

	
    
    
}

