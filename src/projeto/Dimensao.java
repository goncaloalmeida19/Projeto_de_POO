package projeto;

public class Dimensao {
    private final double altura;
    private final double largura;
    private final double profundidade;

    public Dimensao(double altura, double largura, double profundidade) {
        this.altura = altura;
        this.largura = largura;
        this.profundidade = profundidade;
    }

    @Override
    public String toString() {
        return "Altura: " + altura +
                "m / Largura: " + largura +
                "m / Profundidade: " + profundidade + "m";
    }
}
