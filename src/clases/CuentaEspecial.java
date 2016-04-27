package clases;

import excepciones.DebitarException;
import excepciones.MontoException;
import excepciones.TransaccionException;

public class CuentaEspecial extends Cuenta {
		
		public CuentaEspecial(){			
			setTipoCuenta("CuentaEspecial");
			setTipoMoneda("Pesos");			
			this.habilitada = true;
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

