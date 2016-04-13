package tp1;

import java.util.ArrayList;

public abstract class CajaDeAhorro extends CuentaComun {
    private final ArrayList<PersonaFisica> titulares;
    private final double tasaDeInteres;
	private final double tipoDeCambioVigente;
	
    public CajaDeAhorro(ArrayList<PersonaFisica> titulares, double tasaDeInteres, double tipoDeCambioVigente) {
    	this.tasaDeInteres = tasaDeInteres;
    	this.tipoDeCambioVigente = tipoDeCambioVigente;
    	this.titulares = titulares;
    }
        
    public double convertirPesosADolares(double montoEnPesos){
       return montoEnPesos*this.tipoDeCambioVigente;   
    }
        
    public double convertirDolaresAPesos(double montoEnDolares){
       return montoEnDolares/this.tipoDeCambioVigente;
    }

    public ArrayList<PersonaFisica> getTitulares(){
    	return this.titulares;
    }
    
    public double getTasaDeInteres(){
    	return this.tasaDeInteres;
    }
    
}
