package projeto;

import java.util.ArrayList;
import java.util.List;

public class CadeiaSupermercados {
    List<Cliente> clientes = new ArrayList<>();

    public Cliente contemEmail(String email){
        for(Cliente c: clientes){
            if(c.getEmail().equals(email)){
                return c;
            }
        }
        return null;
    }

    public void confirmaVenda(Venda venda, Cliente cliente){
        cliente.addVenda(venda);
    }
}
