package projeto;

public class Mobiliario extends Produto{
    private final int peso;
    private final Dimensao dim;

    public Mobiliario(String identificador, String nome, double precoUni, int peso, Dimensao dim, int stockInicial) {
        super(identificador, nome, precoUni, stockInicial);
        this.peso = peso;
        this.dim = dim;
    }

    public double precoDeEnvio(){
        if(peso > 15) return 10;
        else return 0;
    }

    @Override
    public String toString() {
        return "\t" + nome + "(" + identificador + ")\n\tDescrição do produto:\n\t\tPeso: "
                + peso + "kg\n" + "\t\tDimensão: " + dim + "\n\t\tPreço Unitário: " + precoUni;
    }
}
