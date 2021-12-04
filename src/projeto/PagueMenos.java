package projeto;

/**
 * Classe que contém um produto PagueMenos.
 * Carateriza-se com os atributos que uma promoção.
 */
public class PagueMenos extends Promocao{
    /**
     * Construtor da classe PagueMenos
     * @param inicio Data inicial de uma promoção
     * @param fim Data final de uma promoção
     */
    public PagueMenos(Data inicio, Data fim) {
        super(inicio, fim);
    }

    /**
     * Método para obter o desconto que uma promoção aplica a um produto
     * @param quantidade Quantidade de um produto
     * @param precoUni Preço unitário de um produto
     * @return
     */
    public double desconto(int quantidade, double precoUni){
        double desc = 0;
        for (int i = 0; i < quantidade; i++) {
            if(i*0.05 < 0.5) //verificar se o desconto é inferior a 50%
                desc += precoUni * i*0.05;
            else
                desc += precoUni * 0.5;
        }
        return desc;
    }

    /**
     * Método toString da classe PagueMenos
     * @return Tipo de promoção (PagueMenos)
     */
    @Override
    public String toString() {
        return "PagueMenos";
    }
}
