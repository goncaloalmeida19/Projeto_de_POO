package projeto;


import java.util.ArrayList;
import java.util.List;

public class Compra {
    private final Data data;
    private final List<Item> carrinho;

    public Compra(Data data) {
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

    /**
     * Verifica se o stock atual é suficiente para a quantidade pedida do produto
     * @param stockAtual stock atual do produto
     * @param quantidade quantidade pedida do produto
     * @return -2 se o stock atual for suficiente, -1 se este for 0, ou retorna o mesmo se for
     * inferior à quantidade pedida
     */
    private int stockAtualSuficiente(int stockAtual, int quantidade){
        if(stockAtual == 0) return -1;
        if(stockAtual < quantidade) return stockAtual;
        return -2;
    }

    /**
     * Adicionar um ‘item’ ao carrinho
     * @param produto produto a adicionar
     * @param quantidade quantidade do produto a adicionar
     * @param compras lista de compras
     * @return -2 se correr tudo bem, -1 se não houver stock, ou retorna o stock atual se este for inferior
     * à quantidade pedida
     */
    public int addCarrinho(Produto produto, int quantidade, List<Compra> compras) {
        Item i = contemProduto(produto);
        int stockAtual = produto.obterStockAtual(data, compras);
        if(i == null){
            //item ainda não existe no carrinho
            stockAtual = stockAtualSuficiente(stockAtual, quantidade);
            if(stockAtual > -2) return stockAtual;
            carrinho.add(new Item(produto, quantidade));
        } else{
            //item já existe no carrinho
            stockAtual = stockAtualSuficiente(stockAtual - i.getQuantidade(), quantidade);
            if(stockAtual > -2) return stockAtual;
            i.incrementarQuantidade(quantidade);
        }
        return -2;
    }

    public int removerCarrinho(Produto produto, int quantidade) {
        Item i = contemProduto(produto);
        if(i != null){
            int itemQuantidade = i.getQuantidade();
            if(itemQuantidade - quantidade > 0) i.decrementarQuantidade(quantidade);
            else if(itemQuantidade - quantidade <= 0) {
                carrinho.remove(i);
                return -1;
            }
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

    public void clear(){
        carrinho.clear();
    }

    @Override
    public String toString() {
        if(carrinho.size() == 0)  return  "Carrinho vazio.";
        else{
            String produtos = "";
            for(Item item: carrinho){
                Produto p = item.getProduto();
                produtos = produtos.concat("\n\tProduto: " + p.getNome() + " Quantidade: " + item.getQuantidade()
                        + " Valor (s/ Promoção): " + String.format("%.2f",p.getPrecoUni() * item.getQuantidade())+ "€");
            }
            return produtos;
        }
    }
}
