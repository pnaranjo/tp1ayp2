package clases;

public class CuentaEspecial extends Cuenta {
		//private final String tipoDeMoneda;
		
		public CuentaEspecial(double saldo, String tipoDeMoneda){
			super(saldo);
			this.tipoDeCuenta="CuentaEspecial";
			//this.tipoDeMoneda = tipoDeMoneda; RC: Para mi esto no es necesario
		}
		/* RC: Para mi esto no es necesario
		public String getTipoDeMoneda(){
			return tipoDeMoneda;
		}*/

		
		
}

