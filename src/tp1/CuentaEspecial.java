package tp1;

public class CuentaEspecial extends Cuenta {
		private final String tipoDeMoneda;
		
		public CuentaEspecial(double saldo, String tipoDeMoneda){
			super(saldo);
			this.tipoDeMoneda = tipoDeMoneda;
		}
		
		public String getTipoDeMoneda(){
			return tipoDeMoneda;
		}
}