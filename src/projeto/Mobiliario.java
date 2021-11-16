package projeto;

public class Mobiliario extends Produto{
    private final int peso;
    private final Dimensao dim;

    public Mobiliario(String identificador, String nome, int precoUni, Promocao promo, int peso, Dimensao dim) {
        super(identificador, nome, precoUni, promo);
        this.peso = peso;
        this.dim = dim;
    }

    public int getPeso() {
        return peso;
    }

    public Dimensao getDim() {
        return dim;
    }
}
