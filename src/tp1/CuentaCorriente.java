package tp1;

import java.util.ArrayList;


public class CuentaCorriente extends Cuenta {
    private final ArrayList<Cliente> titulares;
    private double montoSobreGiro = 0;    
    static final double comision = 0.03;
    
    
public CuentaCorriente(double saldo,ArrayList<Cliente> titulares, double montoSobreGiro,double debitoParaAbrirCuenta) {
		super(saldo);
		if(debitoParaAbrirCuenta < 10000){
			throw new Error("Debito insuficiente para abrir cuenta,debe ser mayor o igual a 10 mil pesos.");
		}
		this.titulares = titulares;
		this.montoSobreGiro = montoSobreGiro;
		saldo = debitoParaAbrirCuenta;
		enabled = true;
		
	}

	
	public ArrayList<Cliente> getTitulares(){
	return this.titulares;
}
    
    public double cobrarComision(double monto){
        return comision * monto;
    }

    public double getMontoSobreGiro() {
        return montoSobreGiro;
    }        
    
}

