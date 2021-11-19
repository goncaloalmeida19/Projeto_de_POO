package projeto;

import java.util.ArrayList;
import java.util.List;

public class CadeiaSupermercados {
    List<Cliente> clientes;
    List<Produto> produtos;

    public CadeiaSupermercados() {
        this.clientes = new ArrayList<>();

        //APAGAR ISTO ANTES DE SUBMETER
        clientes.add(new Regulares("nome", "morada", "email", 123456789, new Data(1, 1, 1)));
        clientes.add(new Frequentes("n", "m", "e", 987654321, new Data(2, 2, 2)));

        this.produtos = new ArrayList<>();

        //APAGAR ISTO ANTES DE SUBMETER
        produtos.add(new Alimentares("identifcador", "nome", 1.1, 2, 3.1, 7));
        produtos.add(new Limpeza("identif", "no", 1.1, 2, 2));
        produtos.add(new Mobiliario("i", "n", 0.1, 2, new Dimensao(1,1,1), 1));
        produtos.get(0).addPromocoes(new Pague3Leve4(new Data(3, 4, 4), new Data(7, 4, 4)));
        produtos.get(2).addPromocoes(new PagueMenos(new Data(3, 4, 4), new Data(9, 4, 4)));
    }

    public Cliente contemEmail(String email){
        for(Cliente c: clientes){
            if(c.getEmail().equals(email)){
                return c;
            }
        }
        return null;
    }

    public void confirmaCompra(Venda venda, Cliente cliente){
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
