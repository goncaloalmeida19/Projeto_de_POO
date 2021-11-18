package projeto;

import java.util.ArrayList;
import java.util.List;

public class CadeiaSupermercados {
    List<Cliente> clientes;
    List<Produto> produtos;

    public CadeiaSupermercados() {
        this.clientes = new ArrayList<>();
        this.produtos = new ArrayList<>();
    }

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

    public Produto obterProduto(String nomeProduto){
        for(Produto p: produtos){
            if(p.getNome().equalsIgnoreCase(nomeProduto)){
                return p;
            }
        }
        return null;
    }

    public List<Venda> obterVendas(){
        List<Venda> vendas = new ArrayList<>();
        for(Cliente c: clientes){
            vendas.addAll(c.getVendas());
        }
        return vendas;
    }
}
