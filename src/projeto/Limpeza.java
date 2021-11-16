package projeto;

public class Limpeza extends Produto{
    private final int toxicidade;

    public Limpeza(String identificador, String nome, int precoUni, Promocao promo, int toxicidade) {
        super(identificador, nome, precoUni, promo);
        this.toxicidade = toxicidade;
    }

    public int getToxicidade() {
        return toxicidade;
    }
}
