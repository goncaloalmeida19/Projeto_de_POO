package projeto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um cliente.
 * Contém o seu nome, morada, email, telefone,
 * data de nascimento e lista de compras realizadas.
 */
public abstract class Cliente implements Serializable {
    private final String nome;
    private final String morada;
    private final String email;
    private final int telefone;
    private final Data dataNascimento;
    private final List<Compra> compras;

    /**
     * Construtor da classe Cliente
     * @param nome Nome de um cliente
     * @param morada Morada de um cliente
     * @param email Email de um cliente
     * @param telefone Telefone de um cliente
     * @param dataNascimento Data de nascimento de um cliente
     */
    public Cliente(String nome, String morada, String email, int telefone, Data dataNascimento) {
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.compras = new ArrayList<>();

        if(telefone < 0){
            System.out.println("Número de telefone inválido.");
            throw new NumberFormatException();
        }
    }

    /**
     * Método para obter o nome de um cliente
     * @return Nome de um cliente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método para obter a morada de um cliente
     * @return Morada de um cliente
     */
    public String getMorada() {
        return morada;
    }

    /**
     * Método para obter o email de um cliente
     * @return Email de um cliente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método para obter a data de nascimento de um cliente
     * @return Data de nascimento de um cliente
     */
    public Data getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Método para obter a lista de compras realizadas de um cliente
     * @return Lista de compras realizadas de um cliente
     */
    public List<Compra> getCompras() {
        return compras;
    }

    /**
     * Método para adicionar à lista de compras realizadas uma nova compra
     * @param c Compra a ser adicionada à lista de compras realizadas
     */
    public void addCompra(Compra c){
        compras.add(c);
    }

    /**
     * Método para obter lista de compras realizadas até a uma data
     * @param data Data a comparar
     * @return Lista de compras realizadas até a uma data
     */
    public List<Compra> obterComprasAteData(Data data){
        List<Compra> comprasAteData = new ArrayList<>();
        for(Compra c: compras){
            if(c.getData().compareTo(data) <= 0){
                comprasAteData.add(c);
            }
        }
        return comprasAteData;
    }

    /**
     * Método para obter o preço de envio da compra de um cliente
     * @param preco Preço da compra (s/ portes)
     * @return Preço de envio da compra de um cliente
     */
    public abstract double precoDeEnvio(double preco);

    /**
     * Método toString da classe Cliente
     * @return String com informações acerca de um cliente
     */
    @Override
    public String toString() {
        return  "\tNome: " + nome +
                "\n\tMorada: " + morada +
                "\n\tEmail: "+ email +
                "\n\tTelefone: " + telefone +
                "\n\tData: " + dataNascimento + '\n';
    }
}
