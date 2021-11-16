package projeto;

public class Limpeza extends Produto{
    private final int toxicidade;

    public Limpeza(String identificador, String nome, double precoUni, int toxicidade) {
        super(identificador, nome, precoUni);
        this.toxicidade = toxicidade;
    }

    public int getToxicidade() {
        return toxicidade;
    }

    public double precoDeEnvio(){
        return 0;
    }
}
