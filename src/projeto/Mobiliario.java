package projeto;

public class Mobiliario extends Produto{
    private final int peso;
    private final Dimensao dim;

    public Mobiliario(String identificador, String nome, double precoUni, Promocao promo, int peso, Dimensao dim, int stockInicial) {
        super(identificador, nome, precoUni, stockInicial);
        this.peso = peso;
        this.dim = dim;
    }

    public int getPeso() {
        return peso;
    }

    public Dimensao getDim() {
        return dim;
    }

    public double precoDeEnvio(){
        if(peso > 15) return 10;
        else return 0;
    }
}
