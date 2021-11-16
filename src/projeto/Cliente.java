package projeto;

public abstract class Cliente {
    private final String nome;
    private final String morada;
    private final String email;
    private final int telefone;
    private final int Data;

    public Cliente(String nome, String morada, String email, int telefone, int data) {
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telefone = telefone;
        Data = data;
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

    public abstract double precoDeEnvio(double preco);
}
