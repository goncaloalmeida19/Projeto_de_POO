package projeto;

public class Limpeza extends Produto{
    private final int toxicidade;

    public Limpeza(String identificador, String nome, double precoUni, int toxicidade, int stockInicial) {
        super(identificador, nome, precoUni, stockInicial);
        this.toxicidade = toxicidade;
    }

    public int getToxicidade() {
        return toxicidade;
    }

    public double precoDeEnvio(){
        return 0;
    }
}
