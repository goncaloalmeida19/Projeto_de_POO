package projeto;

/**
 * Classe que representa uma promoção do tipo Pague3Leve4.
 * Contém os atributos de uma promoção.
 */
public class Pague3Leve4 extends Promocao{
    /**
     * Construtor da classe Pague3Leve4
     * @param inicio Data inicial de uma promoção
     * @param fim Data final de uma promoção
     */
    public Pague3Leve4(Data inicio, Data fim) {
        super(inicio, fim);
    }

    /**
     * Método para obter o desconto que uma promoção aplica a um produto
     * @param quantidade Quantidade de um produto
     * @param precoUni Preço unitário de um produto
     * @return  Desconto que a promoção Pague3Leve4 aplica a um produto
     */
    public double desconto(int quantidade, double precoUni){
        return precoUni * (int)(quantidade / 4);
    }

    /**
     * Método toString da classe Pague3Leve4
     * @return Tipo de promoção (Pague3Leve4)
     */
    @Override
    public String toString() {
        return "Pague3Leve4";
    }
}
