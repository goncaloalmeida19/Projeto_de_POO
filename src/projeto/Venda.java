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
        if(i != null)
            i.incrementarQuantidade();
        else{
            carrinho.add(new Item(produto, 1));
        }
    }

    public boolean removeCarrinho(Produto produto) {
        Item i = contemProduto(produto);
        if(i != null){
            carrinho.remove(i);
            return true;
        }else return false;
    }

    public double precoFinal(){
        return 0.0;
    }
}
