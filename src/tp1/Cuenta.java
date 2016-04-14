	package tp1;

import java.util.ArrayList;

public abstract class Cuenta  {

	  
    private double saldo;
    private ArrayList<Transaccion> historial;   
    protected boolean enabled;
    
    public Cuenta(double saldo){
    	
    	this.saldo = saldo;
    	this.enabled = false;
    	historial = new ArrayList<Transaccion>();
    }
    

    public double getSaldo() {
        return saldo;
    }

    public ArrayList<Transaccion> getHistorial() {
        return historial;
    }

    public boolean isEnabled() {        
    	return enabled;
    }
    
    public void setEnable(){
        enabled = true;
    }
    public void setDisable(){
        enabled = false;
    }
    
    public void transferir(long cbu,double monto,String moneda){
        
    }
    
    public void acreditar(long cbu,double monto){
        
    }
    
    public void debitarCostos(double costos){
    	saldo -= costos;
    }
    
    public String estadoCuenta(){
    	String estado;
    	if(isEnabled() == true)
    		estado = "Habilitada";
    	else
    		estado = "Deshabilitada";
    	return estado;
    }
    
    public String toString(){      
         return "Saldo:".concat(String.valueOf(saldo)).concat("Estado:").concat(estadoCuenta());
    }
    /*
    @Override
    public int hashCode() {
        return (int) (long) cbu;
    }
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            System.out.println("Cuenta duplicada");
            return false;
        }
        final Cuenta other = (Cuenta) obj;
        if (this.cbu != other.cbu) {
            System.out.println("Cuenta duplicada");
            return false;
        }
        return true;
    }*/
    
    
}
