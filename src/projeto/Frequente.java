package projeto;

/**
 * Classe que representa um cliente frequente.
 * Contém os atributos que um cliente já possui,
 * sendo que a função precoDeEnvio devolve um preço específico
 * para este tipo de cliente.
 */
public class Frequente extends Cliente{
    /**
     * Construtor da classe Frequente
     * @param nome Nome de um cliente
     * @param morada Morada de um cliente
     * @param email Email de um cliente
     * @param telefone Telefone de um cliente
     * @param data Data de nascimento de um cliente
     */
    public Frequente(String nome, String morada, String email, int telefone, Data data) {
        super(nome, morada, email, telefone, data);
    }

    /**
     * Método para obter o preço de envio da compra de um cliente frequente
     * @param preco Preço da compra (s/ portes)
     * @return Preço de envio da compra de um cliente frequente
     */
    public double precoDeEnvio(double preco){
        if(preco > 40) return 0;
        else return 15;
    }
}
