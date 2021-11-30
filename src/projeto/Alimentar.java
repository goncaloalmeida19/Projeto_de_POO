package projeto;

public class Alimentar extends Produto{
    private final int calorias;
    private final double gordura;

    public Alimentar(String identificador, String nome, double precoUni, int calorias, double gordura, int stockInicial) {
        super(identificador, nome, precoUni, stockInicial);
        this.calorias = calorias;
        this.gordura = gordura;
    }

    public int getCalorias() {
        return calorias;
    }

    public double getGordura() {
        return gordura;
    }

    public double precoDeEnvio(){
        return 0;
    }
}
