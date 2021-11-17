package projeto;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    private final String nome;
    private final String morada;
    private final String email;
    private final int telefone;
    private final int Data;
    private List<Venda> vendas;

    public Cliente(String nome, String morada, String email, int telefone, int data) {
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

    public int getData() {
        return Data;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    /**
     * Adicionar à lista de vendas uma nova venda
     * @param v venda feita pelo cliente a que esta chamada de método está associada
     */
    public void addVenda(Venda v){
        vendas.add(v);
    }

    public abstract double precoDeEnvio(double preco);
}
