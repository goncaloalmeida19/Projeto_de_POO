package projeto;

public class Limpeza extends Produto{
    private final int toxicidade;

    public Limpeza(String identificador, String nome, double precoUni, int toxicidade, int stockInicial) {
        super(identificador, nome, precoUni, stockInicial);
        this.toxicidade = toxicidade;
    }

    public double precoDeEnvio(){
        return 0;
    }

    @Override
    public String toString() {
        return "\t" + nome + "(" + identificador + ")\n\tDescrição do produto:\n" + "\t\tGrau de toxidade: "
                + toxicidade + "\n\t\tPreço Unitário: " + precoUni;
    }
}
