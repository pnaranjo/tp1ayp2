package tp1;

public class CuentaEspecial extends Cuenta {
		private final String tipoDeMoneda;
		
		public CuentaEspecial(String tipoDeMoneda){
			this.tipoDeMoneda = tipoDeMoneda;
		}
		
		public String getTipoDeMoneda(){
			return tipoDeMoneda;
		}
}
