package projeto;


import java.util.ArrayList;
import java.util.List;

public class Venda {
    private final Data data;
    private List<Item> carrinho;

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

    public void addCarrinho(Produto produto) {
        boolean in = false;
        for(Item item: carrinho){
            if(item.getProduto().equals(produto)){
                item.incrementarQuantidade();
                in = true;
                break;
            }
        }
        if(!in){
            carrinho.add(new Item(produto, 1));
        }
    }

    public void removeCarrinho(Produto produto) {

    }

    public double precoFinal(){
        return 0.0;
    }
}
