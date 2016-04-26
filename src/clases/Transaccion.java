package clases;

import java.util.Calendar;


import excepciones.MontoException;
import excepciones.TransaccionException;

class Transaccion {
    private String fechaYHora;
    private String tipoMovimiento;
    private double monto;
    private String motivo;
    private String observaciones;

    public Transaccion(String tipoMovimiento, double monto, String motivo) throws TransaccionException, MontoException {
    	if(tipoMovimiento == null || tipoMovimiento != "debitar" || tipoMovimiento != "acreditar"){
        	throw new TransaccionException("El tipo de movimiento ingresado es incorrecto");
        }
        if(motivo == null){
        	throw new TransaccionException("Debe ingresar un motivo para generar la transaccion");
        }
        if( monto <= 0){
        	throw new MontoException();
        }
    	fechaYHora = getTime();
        this.tipoMovimiento = tipoMovimiento;
        this.monto = monto;
        this.motivo = motivo;
        observaciones = "";
    }

    public Transaccion(String tipoMovimiento, double monto, String motivo, String observaciones) throws TransaccionException, MontoException {
        if(tipoMovimiento == null || tipoMovimiento != "debitar" || tipoMovimiento != "acreditar"){
        	throw new TransaccionException("El tipo de movimiento ingresado es incorrecto");
        }
        if(motivo == null){
        	throw new TransaccionException("Debe ingresar un motivo para generar la transaccion");
        }
        if( monto <= 0){
        	throw new MontoException();
        }
        if(observaciones == null){
        	throw new TransaccionException("Debe ingresar una observacion para generar la transaccion");
        }
        
    	fechaYHora = getTime();
        this.tipoMovimiento = tipoMovimiento;
        this.monto = monto;
        this.motivo = motivo;
        this.observaciones = observaciones;
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
    
    
    /* Optativo*/
    public String agregarObservacion(String observacion){
        return  observaciones = observaciones.concat("\n").concat(observacion);
    }
    

    @Override
    public String toString() {
        return  fechaYHora + "\ntipoMovimiento:" + tipoMovimiento + "\nmonto:" + monto + "\nmotivo:" + motivo + "\nobservaciones:" + observaciones +"\n\n";
    }
}
