package projeto;


import java.io.Serializable;

/**
 * Classe que representa a dimensão de um produto de mobiliário.
 * Contém a sua altura, largura e profundidade.
 */
public class Dimensao implements Serializable {
    private final double altura;
    private final double largura;
    private final double profundidade;

    /**
     * Construtor da classe Dimensao
     * @param altura Altura do produto de mobiliário
     * @param largura Largura do produto de mobiliário
     * @param profundidade Profundidade do produto de mobiliário
     */
    public Dimensao(double altura, double largura, double profundidade) {
        this.altura = altura;
        this.largura = largura;
        this.profundidade = profundidade;
    }

    /**
     * Método toString da classe Dimensao
     * @return Altura, largura e profundidade de um produto de mobiliário
     */
    @Override
    public String toString() {
        return "Altura: " + altura +
                "m / Largura: " + largura +
                "m / Profundidade: " + profundidade + "m";
    }
}
