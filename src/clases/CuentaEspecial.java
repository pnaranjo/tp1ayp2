package clases;

import excepciones.DebitarException;
import excepciones.MontoException;
import excepciones.TransaccionException;

public class CuentaEspecial extends Cuenta {
		
		public CuentaEspecial() throws MontoException, TransaccionException{
			super(0.0);
			setTipoCuenta("CuentaEspecial");
			setTipoMoneda("Pesos");	
			transaccion = new Transaccion("Se ha creado una cuenta especial", saldo,"Por disposicion del banco " );
			historial.add(transaccion);
		}
		
		public String acreditar(double monto, String motivo) throws TransaccionException, MontoException{		
			if(monto <= 0)
				throw new MontoException();
			saldo += monto;
			transaccion = new Transaccion("acreditar", monto, motivo);
			historial.add(transaccion);
			return transaccion.toString();
		}
		
		
		public String acreditar(double monto, String motivo,String observacion) throws TransaccionException, MontoException{
			if(monto <= 0)
				throw new MontoException();
			saldo += monto;
			transaccion = new Transaccion("acreditar", monto, motivo,observacion);
			historial.add(transaccion);
			return transaccion.toString();
		}
		public String debitar(double monto, String motivo) throws TransaccionException, MontoException, DebitarException{		
			if(monto <= 0)
				throw new MontoException();
			if(saldo < monto){
				throw new DebitarException("La operaci�n no se ha podido realizar, saldo insuficiente");
			}
			saldo -= monto;
			transaccion = new Transaccion("debitar", monto, motivo);
			historial.add(transaccion);
			return transaccion.toString();
		}
		public String debitar(double monto, String motivo,String observacion) throws TransaccionException, MontoException, DebitarException{
			if(monto <= 0)
				throw new MontoException();
			if(saldo < monto){
				throw new DebitarException("La operaci�n no se ha podido realizar, saldo insuficiente");
			}
			saldo -= monto;
			transaccion = new Transaccion("debitar", monto, motivo,observacion);
			historial.add(transaccion);
			return transaccion.toString();
		}
}

