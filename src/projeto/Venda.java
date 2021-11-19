package projeto;


import java.util.ArrayList;
import java.util.List;

public class Venda {
    private final Data data;
    private final List<Item> carrinho;

    public Venda(Data data) {
        this.data = data;
        carrinho = new ArrayList<>();
    }

    public Data getData() {
        return data;
    }

    public List<Item> getCarrinho() {
        return carrinho;
    }
    
    private Item contemProduto(Produto p){
        for(Item item: carrinho){
            if(item.getProduto().igual(p)){
                return item;
            }
        }
        return null;
    }
    
    public int addCarrinho(Produto produto, int quantidade, List<Venda> vendas) {
        Item i = contemProduto(produto);
        int stockAtual = produto.obterStockAtual(data, vendas);
        if(stockAtual == 0) return -1;

        if(i != null) {
            if(stockAtual < quantidade) {
                i.incrementarQuantidade(stockAtual);
                return stockAtual;
            }
            else i.incrementarQuantidade(quantidade);
        }
        else {
            if(stockAtual < quantidade) {
                carrinho.add(new Item(produto, stockAtual));
                return stockAtual;
            }
            else carrinho.add(new Item(produto, quantidade));
        }
        return -2;
    }

    public int removeCarrinho(Produto produto, int quantidade) {
        Item i = contemProduto(produto);
        if(i != null){
            int itemQuantidade = i.getQuantidade();
            if(itemQuantidade - quantidade > 0) i.decrementarQuantidade(quantidade);
            else if(itemQuantidade - quantidade == 0) carrinho.remove(i);
            else return -1;
            return 1;
        }else return 0;
    }

    public int obterQuantidade(Produto p){
        for(Item i: carrinho){
            if(i.getProduto().igual(p)){
                return i.getQuantidade();
            }
        }
        return 0;
    }

    public double precoDeEnvioTotal(Cliente cliente, double preco) {
        double envio = cliente.precoDeEnvio(preco);

        // Se for um produto do tipo mobiliário irá somar 15 euros, ao preço final, por produto
        for(Item i: carrinho){
            envio += (i.getProduto().precoDeEnvio() * i.getQuantidade());
        }

        return envio;
    }

    public double precoSemEnvio(){
        double preco = 0.0;
        for(Item i: carrinho){
            preco += i.getProduto().obterPreco(i.getQuantidade(), data);
        }
        return preco;
    }

    @Override
    public String toString() {
        if(carrinho.size() == 0)  return  "Carrinho vazio.";
        else{
            String produtos = "";
            for(Item item: carrinho){
                Produto p = item.getProduto();
                produtos = produtos.concat("\n\tProduto: " + p.getNome() + " Quantidade: " + item.getQuantidade()
                        + " Valor: " + String.format("%.2f",p.getPrecoUni() * item.getQuantidade())+ "€");
            }
            return produtos;
        }
    }
}
