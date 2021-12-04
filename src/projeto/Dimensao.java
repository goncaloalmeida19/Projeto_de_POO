package projeto;


import java.io.Serializable;

/**
 * Classe que contém a dimensão de um produto de mobiliário.
 * Carateriza-se com a altura, largura e profundidade do
 * produto de mobiliário, visto que só é usada como atributo
 * na classe correspondente a um produto de mobiliário.
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
