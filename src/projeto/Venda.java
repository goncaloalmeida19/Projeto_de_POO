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
    
    public void addCarrinho(Produto produto) {
        Item i = contemProduto(produto);
        if(i != null) {
            i.incrementarQuantidade();
        }
        else carrinho.add(new Item(produto, 1));
    }

    public boolean removeCarrinho(Produto produto) {
        Item i = contemProduto(produto);
        if(i != null){
            i.decrementarQuantidade();
            return true;
        }else return false;
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
        double preco = 0;
        for(Item i: carrinho){
            preco += i.getProduto().obterPreco(i.getQuantidade(), data);
        }
        return preco;
    }

    @Override
    public String toString() {
        String produtos = "";
        for(Item item: carrinho){
            produtos += "\n\tProduto: " + item.getProduto() + " Quantidade: " + item.getQuantidade();
        }
        return produtos;
    }
}
