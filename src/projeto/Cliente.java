package projeto;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    protected String nome;
    protected String morada;
    protected String email;
    protected int telefone;
    protected Data Data;
    private List<Venda> vendas;

    public Cliente(String nome, String morada, String email, int telefone, Data data) {
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telefone = telefone;
        this.Data = data;
        this.vendas = new ArrayList<>();
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

    public int getTelefone() {
        return telefone;
    }

    public Data getData() {
        return Data;
    }

    public List<Venda> getVendas() {
        return vendas;
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

    protected void setData(Data data) {
        Data = data;
    }

    /**
     * Adicionar à lista de vendas uma nova venda
     * @param v venda feita pelo cliente a que esta chamada de método está associada
     */
    public void addVenda(Venda v){
        vendas.add(v);
    }

    public abstract double precoDeEnvio(double preco);

    @Override
    public abstract String toString();

    public abstract void setters(String altera, int op, Data d);
}
