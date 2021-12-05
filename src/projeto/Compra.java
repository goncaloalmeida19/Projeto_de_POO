package projeto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma compra.
 * Contém a data da compra realizada, o carrinho da mesma,
 * e ainda os preços finais com e sem portes.
 */
public class Compra implements Serializable {
    private final Data data;
    private final List<Item> carrinho;
    private double precoSemEnvioFinal;
    private double precoComEnvioFinal;

    /**
     * Construtor da classe Compra
     * @param data Data da compra realizada
     */
    public Compra(Data data) {
        this.data = data;
        carrinho = new ArrayList<>();
    }

    /**
     * Método para obter a data de uma compra
     * @return Data de uma compra
     */
    public Data getData() {
        return data;
    }

    /**
     * Método para obter o carrinho de uma compra
     * @return Carrinho de uma compra
     */
    public List<Item> getCarrinho() {
        return carrinho;
    }

    /**
     * Método para obter o preco sem envio final da compra
     * @return preco sem envio final da compra
     */
    public double getPrecoSemEnvioFinal() {
        return precoSemEnvioFinal;
    }

    /**
     * Método para obter o preco com envio final da compra
     * @return preco com envio final da compra
     */
    public double getPrecoComEnvioFinal() {
        return precoComEnvioFinal;
    }


    /**
     * Método para definir o preco sem envio final da compra
     * @param precoSemEnvioFinal preço a definir
     */
    public void setPrecoSemEnvioFinal(double precoSemEnvioFinal) {
        this.precoSemEnvioFinal = precoSemEnvioFinal;
    }

    /**
     * Método para definir o preco com envio final da compra
     * @param precoComEnvioFinal preço a definir
     */
    public void setPrecoComEnvioFinal(double precoComEnvioFinal) {
        this.precoComEnvioFinal = precoComEnvioFinal;
    }

    /**
     * Método para verificar se um produto está no carrinho
     * @param p Produto a verificar
     * @return item, se houver, no carrinho, um 'item' cujo atributo produto seja o produto a verificar,
     * ou null, caso contrário
     */
    private Item contemProduto(Produto p){
        for(Item item: carrinho){
            if(item.getProduto().igual(p)){
                return item;
            }
        }
        return null;
    }

    /**
     * Método para verificar se o stock atual é suficiente para a quantidade pedida do produto
     * @param stockAtual Stock atual do produto
     * @param quantidade Quantidade pedida do produto
     * @return -1, se o stock atual for suficiente, ou retorna-o se for
     * inferior à quantidade pedida
     */
    private int stockAtualSuficiente(int stockAtual, int quantidade){
        if(stockAtual < quantidade) return stockAtual;
        return -1;
    }

    /**
     * Método para adicionar um ‘item’ ao carrinho
     * @param produto Produto a adicionar
     * @param quantidade Quantidade do produto a adicionar
     * @param compras Lista de compras realizadas por todos os clientes
     * @return -1, se correr tudo bem ou stock atual, se este for inferior
     * à quantidade pedida
     */
    public int adicionarCarrinho(Produto produto, int quantidade, List<Compra> compras) {
        Item i = contemProduto(produto);
        int stockAtual = produto.obterStockAtual(data, compras);
        if(i == null){
            //item ainda não existe no carrinho
            stockAtual = stockAtualSuficiente(stockAtual, quantidade);
            if(stockAtual > -1) return stockAtual;
            carrinho.add(new Item(produto, quantidade));
        } else{
            //item já existe no carrinho
            stockAtual = stockAtualSuficiente(stockAtual - i.getQuantidade(), quantidade);
            if(stockAtual > -1) return stockAtual;
            i.incrementarQuantidade(quantidade);
        }
        return -1;
    }

    /**
     * Método para remover um ‘item’ ao carrinho
     * @param produto Produto cuja quantidade associada irá ser removida
     * @param quantidade Quantidade do produto a remover
     * @return -2, se correr tudo bem e o produto continuar no carrinho;
     * -1, se tudo correr bem e o produto for removido do carrinho;
     * 0, se o produto não estiver no carrinho;
     * ou a quantidade do produto no carrinho, se esta for inferior à quantidade pedida
     */
    public int removerCarrinho(Produto produto, int quantidade) {
        Item i = contemProduto(produto);
        if(i == null) return 0;

        int itemQuantidade = i.getQuantidade();
        if(quantidade > itemQuantidade) return itemQuantidade;
        if(quantidade == itemQuantidade){
            carrinho.remove(i);
            return -1;
        }
        i.decrementarQuantidade(quantidade);
        return -2;
    }

    /**
     * Método para obter a quantidade de um produto no carrinho
     * @param p Produto de que se quer a quantidade presente no carrinho
     * @return Quantidade de um produto no carrinho
     */
    public int obterQuantidade(Produto p){
        for(Item i: carrinho){
            if(i.getProduto().igual(p)){
                return i.getQuantidade();
            }
        }
        return 0;
    }

    /**
     * Método para obter o preço de envio, tendo em conta o tipo de produtos no carrinho e o cliente a realizar a compra
     * @param cliente Cliente a realizar a compra
     * @param preco Preço da compra (s/ portes)
     * @return Preço de envio da compra
     */
    public double precoDeEnvioTotal(Cliente cliente, double preco) {
        double envio = cliente.precoDeEnvio(preco);

        // Se for um produto do tipo mobiliário irá somar 15 euros, ao preço final, por produto
        for(Item i: carrinho){
            envio += (i.getProduto().precoDeEnvio() * i.getQuantidade());
        }

        return envio;
    }

    /**
     * Método para obter o preço de uma compra (s/ portes)
     * @return Preço de uma compra (s/ portes)
     */
    public double precoSemEnvio(){
        double preco = 0.0;
        for(Item i: carrinho){
            preco += i.getProduto().obterPreco(i.getQuantidade(), data);
        }
        return preco;
    }

    /**
     * Método para remover todos os 'itens' do carrinho
     */
    public void clear(){
        carrinho.clear();
    }

    /**
     * Método toString da classe Compra
     * @return String com informações acerca do carrinho e dos produtos presentes no mesmo
     */
    @Override
    public String toString() {
        if(carrinho.size() == 0)  return  "Carrinho vazio.";
        else{
            String produtos = "";
            for(Item item: carrinho){
                Produto p = item.getProduto();
                produtos = produtos.concat("\n\tProduto: " + p.getNome() + ", Quantidade: " + item.getQuantidade()
                        + ", Valor (s/ Promoção): " + String.format("%.2f",p.getPrecoUni() * item.getQuantidade())+ "€");
            }
            return produtos;
        }
    }
}
