package projeto;

/**
 * Classe que representa um produto de mobiliário.
 * Contém o peso e dimensão do produto
 * além dos atributos que um produto já possui.
 */
public class Mobiliario extends Produto{
    private final int peso;
    private final Dimensao dim;

    /**
     * Construtor da classe Mobiliario
     * @param identificador Identificador de um produto
     * @param nome Nome de um produto
     * @param precoUni Preço unitário de um produto
     * @param peso Peso de um produto
     * @param dim Dimensão de um produto
     * @param stockInicial Stock inicial de um produto
     */
    public Mobiliario(String identificador, String nome, double precoUni, int peso, Dimensao dim, int stockInicial) {
        super(identificador, nome, precoUni, stockInicial);
        if(peso < 0){
            System.out.println("Peso inválido.");
            throw new NumberFormatException();
        }
        this.peso = peso;
        this.dim = dim;
    }

    /**
     * Método para devolver o preço de envio de um produto de mobiliário
     * @return 0, caso o peso do produto não exceda o valor 15, e 10, caso o peso do produto exceda o valor 15
     */
    public double precoDeEnvio(){
        if(peso > 15) return 10;
        else return 0;
    }

    /**
     * Método toString da classe Mobiliario
     * @return Nome e identificador de um produto acompanhado pela sua descrição definida pela categoria de produto (Mobiliário)
     */
    @Override
    public String toString() {
        return super.toString() + "\n\t\tPeso: "
                + peso + "kg\n" + "\t\tDimensão: " + dim + "\n\t\tPreço Unitário: " + precoUni;
    }
}
