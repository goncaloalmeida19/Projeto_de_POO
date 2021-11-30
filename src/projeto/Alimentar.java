package projeto;

public class Alimentar extends Produto{
    private final int calorias;
    private final double gordura;

    public Alimentar(String identificador, String nome, double precoUni, int calorias, double gordura, int stockInicial) {
        super(identificador, nome, precoUni, stockInicial);
        this.calorias = calorias;
        this.gordura = gordura;
    }

    public double precoDeEnvio(){
        return 0;
    }

    @Override
    public String toString() {
        return "\t" + nome + "(" + identificador + ")\n\tDescrição do produto:\n" + "\t\tCalorias: "
                + calorias + "kcal\n" + "\t\tGordura: " + gordura + "kg\n\t\tPreço Unitário: " + precoUni;
    }
}
