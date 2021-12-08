package projeto;

/**
 * Classe que representa um produto de limpeza.
 * Contém o seu grau de toxicidade,
 * além dos atributos que um produto já possui.
 */
public class Limpeza extends Produto{
    private final int toxicidade;

    /**
     * Construtor da classe Limpeza
     * @param identificador Identificador de um produto
     * @param nome Nome de um produto
     * @param precoUni Preço unitário de um produto
     * @param toxicidade Grau de toxidade de um produto de limpeza
     * @param stockInicial Stock inicial de um produto
     */
    public Limpeza(String identificador, String nome, double precoUni, int toxicidade, int stockInicial) {
        super(identificador, nome, precoUni, stockInicial);
        if(toxicidade < 0 || toxicidade > 10){
            System.out.println("Grau de toxicidade inválida.");
            throw new NumberFormatException();
        }
        this.toxicidade = toxicidade;
    }


    /**
     * Método toString da classe Limpeza
     * @return Nome e identificador de um produto acompanhado pela sua descrição definida pela categoria de produto (Limpeza)
     */
    @Override
    public String toString() {
        return super.toString() + "\n\t\tGrau de toxidade: "
                + toxicidade + "\n\t\tPreço Unitário: " + precoUni;
    }
}
