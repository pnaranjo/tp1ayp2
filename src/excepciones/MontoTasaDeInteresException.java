package excepciones;

public class MontoTasaDeInteresException extends Exception {
 /**
	 * 
	 */
	private static final long serialVersionUID = -8936026794402857509L;
public MontoTasaDeInteresException(String s){
	 super(s);
 }
 public MontoTasaDeInteresException(){
	 super("La tasa de interes ingresada no es valida.Debe ser mayor a cero");
 }
}
