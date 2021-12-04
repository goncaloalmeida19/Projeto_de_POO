package projeto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que contém um produto.
 * Carateriza-se pelo identificador, nome, preço unitário,
 * lista de promoções datadas e pelo stock inicial de um
 * produto.
 */
public abstract class Produto implements Serializable {
    protected final String identificador;
    protected final String nome;
    protected final double precoUni;
    private final List<Promocao> promocoes;
    private final int stockInicial;

    /**
     * Construtor da classe Produto
     * @param identificador Identificador de um produto
     * @param nome Nome de um produto
     * @param precoUni Preço unitário de um produto
     * @param stockInicial Stock inicial de um produto
     */
    public Produto(String identificador, String nome, double precoUni, int stockInicial) {
        this.identificador = identificador;
        this.nome = nome;
        this.precoUni = precoUni;
        this.promocoes = new ArrayList<>();
        this.stockInicial = stockInicial;
    }

    /**
     * Método para obter o identificador de um produto
     * @return Identificador de um produto
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Método para obter o nome de um produto
     * @return Nome de um produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método para obter o preço unitário de um produto
     * @return Preço unitário de um produto
     */
    public double getPrecoUni() {
        return precoUni;
    }

    /**
     * Método que adiciona uma promoção à lista de promoções de um produto
     * @param p Promoção a adicionar à lista de promoções de um produto
     */
    public void addPromocoes(Promocao p){
        promocoes.add(p);
    }

    /**
     * Método para obter a promoção aplicada a um produto na data passada como parâmetro
     * @param d Data
     * @return Promoção aplicada a um produto na data ou null, caso não haja uma promoção aplicada a um produto na data
     */
    public Promocao obterPromocao(Data d){
        for (Promocao p: promocoes){
            if(d.compareTo(p.getInicio()) >= 0 && d.compareTo(p.getFim()) <= 0){
                return p;
            }
        }
        return null;
    }

    /**
     * Método para obter o preço de um produto tendo em conta a quantidade e promoção aplicada no mesmo
     * @param quantidade Quantidade de um produto
     * @param d Data
     * @return o preço unitário multiplicado pela quantidade caso não haja uma promoção aplicada a um produto na data
     * ou preço unitário multiplicado pela quantidade menos o desconto aplicado pela promoção no produto, caso contrário
     */
    public double obterPreco(int quantidade, Data d){
        Promocao promo = obterPromocao(d);
        if(promo == null) return precoUni * quantidade;
        double desconto = promo.desconto(quantidade, precoUni);
        return precoUni * quantidade - desconto;
    }

    /**
     * Método para verificar a igualdade entre dois produtos
     * @param p Produto a comparar
     * @return true, se o identificador dos produtos for igual, ou false, caso contrário
     */
    public boolean igual(Produto p){
        return identificador.equals(p.identificador);
    }
    
    /**
     * Método para procurar um produto numa compra
     * @param c Compra
     * @return Quantidade de um produto na compra
     */
    private int encontraNaCompra(Compra c){
        for(Item i: c.getCarrinho())
            if(i.getProduto().igual(this))
                return i.getQuantidade();
        return 0;
    }

    /**
     * Método para obter o stock de um produto numa data
     * @param d Data
     * @param compras Lista de compras realizadas
     * @return Stock de um produto numa data
     */
    public int obterStockAtual(Data d, List<Compra> compras){
        int stockAtual = stockInicial;
        for(Compra v: compras)
            if(d.compareTo(v.getData()) >= 0)
                stockAtual -= encontraNaCompra(v);
        return stockAtual;
    }

    /**
     * Método para devolver o preço de envio de um produto
     * @return Preço de envio de um produto
     */
    public abstract double precoDeEnvio();

    /**
     * Método toString da classe Produto
     * @return String com informações acerca do produto
     */
    @Override
    public abstract String toString();
}
