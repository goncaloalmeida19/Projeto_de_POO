package projeto;

public class Item {
    private final Produto produto;
    private int quantidade;

    public Item(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void incrementarQuantidade() {
        this.quantidade++;
    }

    public void decrementarQuantidade() {
        this.quantidade--;
    }
}
