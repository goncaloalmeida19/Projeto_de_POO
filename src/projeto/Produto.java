package projeto;

public abstract class Produto {
    private final String identificador;
    private final String nome;
    private final int precoUni;
    private final Promocao promo;

    public Produto(String identificador, String nome, int precoUni, Promocao promo) {
        this.identificador = identificador;
        this.nome = nome;
        this.precoUni = precoUni;
        this.promo = promo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public int getPrecoUni() {
        return precoUni;
    }

    public Promocao getPromo() {
        return promo;
    }
}
