	package tp1;

import java.util.ArrayList;

public abstract class Cuenta  {

    //private final long cbu = 0;
    protected long cbu = 0;  //estan bien los protected???
    private double saldo;
    private ArrayList<Transaccion> historial;   
    protected boolean enabled;
    
    public Cuenta(double saldo){
    	
    	this.saldo = saldo;
    	this.enabled = false;
    	historial = new ArrayList<Transaccion>();
    }
    public long getCbu() {
        return cbu;
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
    
    public String toString(){
        
         Integer datoCbu = (int)(long) cbu;
         Integer datoSaldo = (int)(double) cbu;
         return "CBU:".concat(String.valueOf(cbu)).concat("Saldo:").concat(String.valueOf(saldo));
    }
    
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
    }
    
    
}
