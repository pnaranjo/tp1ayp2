package excepciones;

public class MontoTasaDeInteresException extends Exception {
 public MontoTasaDeInteresException(String s){
	 super(s);
 }
 public MontoTasaDeInteresException(){
	 super("La tasa de interes ingresada no es valida.Debe ser mayor a cero");
 }
}
