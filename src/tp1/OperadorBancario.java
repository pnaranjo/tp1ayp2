package tp1;

import java.util.HashSet;
import java.util.Iterator;

public class OperadorBancario /*implements Iterable<Cuenta> */{

    public static HashSet<Cuenta> portfolioDeCuentas;

    public static HashSet<Cliente> portfolioDeClientes;

    public OperadorBancario() {
        portfolioDeCuentas = new HashSet<Cuenta>();
        portfolioDeClientes = new HashSet<Cliente>();
    }
}
/*
    @Override
    public Iterator<Cuenta> iterator() {
        Iterator it = new IteratorCuenta();
    }

    protected class IteratorCuenta implements Iterator<Cuenta> {
        protected int posicionHash;
        
        public IteratorCuenta(){
            posicionHash = 0;
        }
        @Override
        public boolean hasNext() {
            boolean result;
        
            if(posicionHash < portfolioDeCuentas.size()){
                result = true;
            } else{ result = false;}
            return result;
            }

        @Override
        public Cuenta next() {
            posicionHash++;
            return Cuenta;
        }

    }
}
*/