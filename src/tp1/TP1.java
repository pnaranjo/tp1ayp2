package tp1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TP1 {

    public static void main(String[] args) {
        /*Transaccion T1 = new Transaccion("debito", 500, "adelanto", "primera observacion");
        Transaccion T2 = new Transaccion("credito", 1500, "adelanto", "primera observacion");
        
        Cuenta c1 = new CajaDeAhorro();
        System.out.println(T1.toString());
        System.out.println(T2.toString());*/
        Domicilio domicilio = new Domicilio("bla", "bla2", "bla3", "bla4");
        OperadorBancario galicia = new OperadorBancario();
        HashSet<Cliente> titulares = null;
        
    Cliente c = new PersonaFisica("rodri", 1234, domicilio, 42424421, "asd", 13423, "desarrollador");
    Cliente c1 = new PersonaFisica("rodri2", 12341, domicilio, 42424421, "asd", 13423, "desarrollador");
    Cliente c2 = new PersonaFisica("rodri3", 12342, domicilio, 42424421, "asd", 13423, "desarrollador");

        titulares.add(c);
        /*titulares.add(c1);
        titulares.add(c2);
        /*
        Cuenta cuenta1 = new CuentaCorriente(titulares, 1230);
        galicia.portfolioDeCuentas.add(cuenta1);
        galicia.portfolioDeCuentas.add(cuenta1);
        */
    }
    
}
