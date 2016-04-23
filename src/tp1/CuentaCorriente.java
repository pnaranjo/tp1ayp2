package tp1;

import java.util.HashSet;
import java.util.Set;

public class CuentaCorriente extends CuentaComun {
    private final Set titulares;
    private double montoSobreGiro, montoParaAbrirCuenta = 10000;
    static final double comision = 0.03;
    static long cbu;
            
    public CuentaCorriente(double saldo, HashSet<Cliente> titulares,double montoSobreGiro1) {
        super(saldo);
        this.titulares = new HashSet<Cliente>(titulares);         
        this.montoSobreGiro = montoSobreGiro1;
        enabled = false;
    }
    
    public CuentaCorriente(double saldo, HashSet<Cliente> titulares,double montoSobreGiro,double montoDeposito) {
    	super(saldo);
        if(montoDeposito < montoParaAbrirCuenta){
            throw new Error(" El monto necesario para abrir cuenta debe ser mayor o igual a 10.000");
        }
        this.titulares = new HashSet<Cliente>(titulares);                 
        this.montoSobreGiro = montoSobreGiro;
        cbu = super.getCbu();
        enabled = true;
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
    
    
}

