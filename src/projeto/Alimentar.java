package projeto;

/**
 * Classe que representa um produto alimentar.
 * Contém o número de calorias/100g e a percentagem de gordura,
 * além dos atributos que um produto já contém.
 */
public class Alimentar extends Produto{
    private final int calorias;
    private final double gordura;

    /**
     * Construtor da classe Alimentar
     * @param identificador Identificador de um produto
     * @param nome Nome de um produto
     * @param precoUni Preço unitário de um produto
     * @param calorias Calorias de um produto alimentar
     * @param gordura Quantidade de gordura de um produto alimentar
     * @param stockInicial Stock inicial de um produto
     */
    public Alimentar(String identificador, String nome, double precoUni, int calorias, double gordura, int stockInicial){
        super(identificador, nome, precoUni, stockInicial);
        if(calorias < 0){
            System.out.println("Número de calorias inválido.");
            throw new NumberFormatException();
        }
        if(gordura < 0 || gordura > 100){
            System.out.println("Percetagem de gordura inválida.");
            throw new NumberFormatException();
        }
        this.calorias = calorias;
        this.gordura = gordura;
    }

    /**
     * Método para devolver o preço de envio de um produto alimentar
     * @return 0, pois o envio de um produto alimentar não acresce o preço de envio de uma compra
     */
    public double precoDeEnvio(){
        return 0;
    }

    /**
     * Método toString da classe Alimentar
     * @return Nome e identificador de um produto acompanhado pela sua descrição definida pela categoria de produto (Alimentar)
     */
    @Override
    public String toString() {
        return "\t" + nome + "(" + identificador + ")\n\tDescrição do produto:\n\t\tCalorias: "
                + calorias + "/100g\n" + "\t\tGordura: " + gordura + "%\n\t\tPreço Unitário: " + precoUni;
    }
}
