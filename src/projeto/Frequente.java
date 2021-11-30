package projeto;

public class Frequente extends Cliente{
    public Frequente(String nome, String morada, String email, int telefone, Data data) {
        super(nome, morada, email, telefone, data);
    }

    public double precoDeEnvio(double preco){
        if(preco > 40) return 0;
        else return 15;
    }
}
