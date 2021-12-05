package projeto;

import java.io.Serializable;

/**
 * Classe que contém um item.
 * Contém um produto e uma quantidade.
 */
public class Item implements Serializable {
    private final Produto produto;
    private int quantidade;

    /**
     * Construtor da classe constructor
     * @param produto Produto de um item
     * @param quantidade Quantidade de um item
     */
    public Item(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    /**
     * Método para obter o produto de um item
     * @return Produto de um item
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * Método para obter a quantidade de um item
     * @return Quantidade de um item
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Método para incrementar a quantidade de um item
     * @param quantidade Quantidade a incrementar a um item
     */
    public void incrementarQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

    /**
     * Método para decrementar a quantidade de um item
     * @param quantidade Quantidade a decrementar a um item
     */
    public void decrementarQuantidade(int quantidade) {
        this.quantidade -= quantidade;
    }
}
