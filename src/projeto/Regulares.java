package projeto;

public class Regulares extends Cliente{
    public Regulares(String nome, String morada, String email, int telefone, Data data) {
        super(nome, morada, email, telefone, data);
    }

    public double precoDeEnvio(double preco){
       return 20;
    }
}
