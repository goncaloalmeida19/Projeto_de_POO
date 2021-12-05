package projeto;

import java.io.Serializable;

/**
 * Classe que representa uma promoção.
 * Contém a data inicial e final de uma promoção.
 */
public abstract class Promocao implements Serializable {
    private final Data inicio;
    private final Data fim;

    /**
     * Construtor da classe Promocao
     * @param inicio Data inicial de uma promoção
     * @param fim Data final de uma promoção
     */
    public Promocao(Data inicio, Data fim) {
        if(fim.compareTo(inicio) < 0){
            System.out.println("Data final da promoção anterior à inicial.");
            throw new NumberFormatException();
        }
        this.inicio = inicio;
        this.fim = fim;
    }

    /**
     * Método para obter a data inicial de uma promoção
     * @return Data inicial de uma promoção
     */
    public Data getInicio() {
        return inicio;
    }

    /**
     * Método para obter a data final de uma promoção
     * @return Data final de uma promoção
     */
    public Data getFim() {
        return fim;
    }

    /**
     * Método para obter o desconto que uma promoção aplica a um produto
     * @param quantidade Quantidade de um produto
     * @param precoUni Preço unitário de um produto
     * @return Desconto que uma promoção aplica a um produto
     */
    public abstract double desconto(int quantidade, double precoUni);

    /**
     * Método toString da classe Promocao
     * @return String com informações acerca de uma promoção
     */
    @Override
    public abstract String toString();
}
