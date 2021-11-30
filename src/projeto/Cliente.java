package projeto;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    private String nome;
    private String morada;
    private String email;
    private int telefone;
    private Data dataNascimento;
    private final List<Compra> compras;

    public Cliente(String nome, String morada, String email, int telefone, Data dataNascimento) {
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.compras = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getMorada() {
        return morada;
    }

    public String getEmail() {
        return email;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * Adicionar à lista de compras uma nova compra
     * @param c compra feita pelo cliente a que esta chamada de método está associada
     */
    public void addCompra(Compra c){
        compras.add(c);
    }

    public abstract double precoDeEnvio(double preco);

    @Override
    public String toString() {
        return  "\tNome: " + nome +
                "\n\tMorada: " + morada +
                "\n\tEmail: "+ email +
                "\n\tTelefone: " + telefone +
                "\n\tData: " + dataNascimento + '\n';
    }
}
