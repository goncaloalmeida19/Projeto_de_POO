package projeto;

import java.util.ArrayList;
import java.util.List;

public abstract class Produto {
    private final String identificador;
    private final String nome;
    private final double precoUni;
    private final List<Promocao> promocoes;
    private final int stockInicial;

    public Produto(String identificador, String nome, double precoUni, int stockInicial) {
        this.identificador = identificador;
        this.nome = nome;
        this.precoUni = precoUni;
        this.promocoes = new ArrayList<>();
        this.stockInicial = stockInicial;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoUni() {
        return precoUni;
    }
    
    public void addPromocoes(Promocao p){
        promocoes.add(p);
    }
    
    public List<Promocao> getPromocoes(){
        return promocoes;
    }

    public Promocao obterPromocao(Data d){
        for (Promocao p: promocoes){
            if(d.compareTo(p.getInicio()) >= 0 && d.compareTo(p.getFim()) <= 0){
                return p;
            }
        }
        return null;
    }

    public double obterPreco(int quantidade, Data d){
        Promocao promo = obterPromocao(d);
        if(promo == null) return precoUni * quantidade;
        double desconto = promo.desconto(quantidade, precoUni);
        return precoUni * quantidade - desconto;
    }

    public boolean igual(Produto p){
        return identificador.equals(p.identificador);
    }
    
    /**
     * Procura um produto numa venda
     * @param v venda
     * @return quantidade desse produto na venda
     */
    private int encontraNaVenda(Compra v){
        for(Item i: v.getCarrinho())
            if(i.getProduto().igual(this))
                return i.getQuantidade();
        return 0;
    }
    
    public int obterStockAtual(Data d, List<Compra> compras){
        int stockAtual = stockInicial;
        for(Compra v: compras)
            if(d.compareTo(v.getData()) >= 0)
                stockAtual -= encontraNaVenda(v);
        return stockAtual;
    }

    public abstract double precoDeEnvio();

    @Override
    public String toString() {
        return "\t" + nome + " a " + precoUni;
    }
}
