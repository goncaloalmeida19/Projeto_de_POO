package projeto;

import java.util.HashMap;
import java.util.Map;

public class Venda {
    private final Data data;
    private Map<Produto, Integer> carrinho;

    public Venda(Data data) {
        this.data = data;
        carrinho = new HashMap<>();
    }

    public Data getData() {
        return data;
    }

    public Map<Produto, Integer> getCarrinho() {
        return carrinho;
    }

    public void addCarrinho(Produto produto) {

    }

    public void removeCarrinho(Produto produto) {
        carrinho.remove(produto);
    }

    public double precoFinal(){
        return 0.0;
    }
}
