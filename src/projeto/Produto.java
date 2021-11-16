package projeto;

public abstract class Produto {
    private final String identificador;
    private final String nome;
    private final int preco;
    private final Promocao promo;

    public Produto(String identificador, String nome, int preco, Promocao promo) {
        this.identificador = identificador;
        this.nome = nome;
        this.preco = preco;
        this.promo = promo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public int getPreco() {
        return preco;
    }

    public Promocao getPromo() {
        return promo;
    }
}
