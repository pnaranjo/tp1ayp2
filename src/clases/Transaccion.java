package clases;

import java.util.Calendar;


import excepciones.MontoException;
import excepciones.TransaccionException;

public class Transaccion {
    private String fechaYHora;
    private String tipoMovimiento;
    private double monto;
    private String motivo;
    private String observaciones;

    public Transaccion(String tipoMovimiento, double monto, String motivo) throws TransaccionException, MontoException {
    	setTipoMovimiento(tipoMovimiento);
    	setMotivo(motivo);
    	setMonto(monto);        
    	fechaYHora = getTime();
        getTime();
        observaciones = "";
    }
    
    
    public Transaccion(String tipoMovimiento, double monto, String motivo, String observaciones) throws TransaccionException, MontoException {
    	setTipoMovimiento(tipoMovimiento);
    	setMotivo(motivo);
    	setMonto(monto);        
    	fechaYHora = getTime();
        getTime();
        setObservaciones(observaciones);
        
    }
    
    public String getTime() {
        Calendar c = Calendar.getInstance();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH));
        String annio = Integer.toString(c.get(Calendar.YEAR));
        String hora = Integer.toString(c.get(Calendar.HOUR));
        String minutos = Integer.toString(c.get(Calendar.MINUTE));
        String segundos = Integer.toString(c.get(Calendar.SECOND));
        return fechaYHora = dia+"/"+mes+"/"+annio+"   "+hora+":"+minutos+";"+segundos;
    }
    
    public String getTipoMovimiento() {
        return tipoMovimiento;
    }
    
    public double getMonto() {
        return monto;
    }
    
    public String getMotivo() {
        return motivo;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setTipoMovimiento(String tipoMovimiento) throws TransaccionException{
		String tipoMov =  tipoMovimiento;
    	if(tipoMovimiento == null && !tipoMov.equalsIgnoreCase("debitar")  && !tipoMov.equalsIgnoreCase("acreditar")){
        	throw new TransaccionException("El tipo de movimiento ingresado es incorrecto");
        } 
		this.tipoMovimiento = tipoMovimiento;
	}
    
    public void setMotivo(String motivo) throws TransaccionException{
		if(motivo == null){
        	throw new TransaccionException("Debe ingresar un motivo para generar la transaccion");
        }
		this.motivo = motivo;
	}
    
    public void setMonto(double monto) throws MontoException{
		if( monto < 0){
        	throw new MontoException();
        }
		this.monto = monto;
	}
    public void setObservaciones(String observaciones) throws TransaccionException{
    	if(observaciones == null){
        	throw new TransaccionException("Debe ingresar una observacion para generar la transaccion");
        }
        this.observaciones = observaciones;
    }
    public String agregarObservacion(String observacion){
        return  observaciones = observaciones.concat("\n").concat(observacion);
    }
    @Override
    public String toString() {
        return  fechaYHora + "\ntipoMovimiento:" + tipoMovimiento + "\nmonto:" + monto + "\nmotivo:" + motivo + "\nobservaciones:" + observaciones +"\n\n";
    }
}
