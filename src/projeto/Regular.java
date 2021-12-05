package projeto;

/**
 * Classe que representa um cliente regular.
 * Contém os atributos que um cliente já possui,
 * sendo que a função precoDeEnvio devolve um preço específico
 * para este tipo de cliente.
 */
public class Regular extends Cliente{
    /**
     * Construtor da classe Regular
     * @param nome Nome de um cliente
     * @param morada Morada de um cliente
     * @param email Email de um cliente
     * @param telefone Telefone de um cliente
     * @param data Data de nascimento de um cliente
     */
    public Regular(String nome, String morada, String email, int telefone, Data data) {
        super(nome, morada, email, telefone, data);
    }

    /**
     * Método para obter o preço de envio da compra de um cliente regular
     * @param preco Preço da compra (s/ portes)
     * @return 20, pois este é sempre o preço de envio da compra de um cliente regular
     */
    public double precoDeEnvio(double preco){
       return 20;
    }
}
