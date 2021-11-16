package projeto;

public class Alimentares extends Produto{
    private final int calorias;
    private final double gordura;

    public Alimentares(String identificador, String nome, int precoUni, Promocao promo, int calorias, double gordura) {
        super(identificador, nome, precoUni, promo);
        this.calorias = calorias;
        this.gordura = gordura;
    }

    public int getCalorias() {
        return calorias;
    }

    public double getGordura() {
        return gordura;
    }
}
