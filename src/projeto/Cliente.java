package projeto;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    protected String nome;
    protected String morada;
    protected String email;
    protected int telefone;
    protected Data dataNascimento;
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

    protected void setNome(String nome) {
        this.nome = nome;
    }

    protected void setMorada(String morada) {
        this.morada = morada;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    protected void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * Adicionar à lista de vendas uma nova venda
     * @param v venda feita pelo cliente a que esta chamada de método está associada
     */
    public void addVenda(Compra v){
        compras.add(v);
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

    public void setters(String altera, int op){
        switch (op) {
            case 1 -> setNome(altera);
            case 2 -> setMorada(altera);
            case 3 -> setEmail(altera);
            case 4 -> setTelefone(Integer.parseInt(altera));
        }
    }
}
